package th.go.dxc.infra.connector_juristic_api.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import io.netty.handler.logging.LogLevel;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;
import th.go.dxc.infra.connector_juristic_api.config.DbdApiConfigurationProperties;
import th.go.dxc.infra.connector_juristic_api.model.request.RequesterDetails;
import th.go.dxc.infra.connector_juristic_api.model.response.JuristicRegistrationResponse;
import th.go.dxc.infra.connector_juristic_api.model.response.LoginResponse;
import th.go.dxc.share.exception.BadGatewayException;
import th.go.dxc.share.security.service.SecurityService;

@Slf4j
public class JuristicRegistrationServiceWebClientImpl implements JuristicRegistrationService {
	private final static String WEB_API_URL_LOGIN = "/ws/auth/validate";
	private final static String WEB_API_URL_PROFILE = "/ws/dbd/juristic/v7/general/profile";
	private final WebClient webClient;
	private final SecurityService securityService;
	private DbdApiConfigurationProperties properties;
	private String accessToken;
	private LocalDate tokenFetchedDate;

	@Autowired
	public JuristicRegistrationServiceWebClientImpl(WebClient.Builder webClientBuilder, SecurityService securityService,
			DbdApiConfigurationProperties properties) {
		super();
		this.securityService = securityService;
		this.properties = properties;
		HttpClient httpClient = HttpClient.create()
				.wiretap("reactor.netty.http.client.HttpClient", LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL);
		this.webClient = webClientBuilder
				.baseUrl(properties.getBaseUrl())
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.build();
	}
	
	/**
	 * คืนค่า token ที่สามารถใช้ได้ในวันปัจจุบัน
	 * หากหมดอายุ (ข้ามวัน) จะ request token ใหม่
	 */
	public String token(String userNin) {
		 // ใช้ token เดิม ถ้ายังเป็นวันเดียวกัน
		if (accessToken != null && tokenFetchedDate != null && tokenFetchedDate.isEqual(LocalDate.now())) {
			log.info("Using cached token");
			return accessToken;
		}
		
		// ขอ token ใหม่จาก API
		try {
			String responseToken = webClient.get()
				.uri(uriBuilder -> uriBuilder.path(WEB_API_URL_LOGIN)
					.queryParam("ConsumerSecret", properties.getConsumerSecret())
					.queryParam("AgentID", userNin) // ต้องเป็นเลขบัตรคนค้น
					.build())
				.header("Consumer-Key", properties.getConsumerKey())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.onStatus(HttpStatus::isError,
					response -> response.bodyToMono(String.class)
						.flatMap(body -> {
							log.error("Error response from token API: {}", body);
							return Mono.error(new BadGatewayException("API Error: " + body));
						})
				)
				.bodyToMono(String.class)
				.block(); // ทำให้เป็น synchronous
			
			 // แปลง response เป็น Java object
			LoginResponse loginObj = new Gson().fromJson(responseToken, LoginResponse.class);
			
			if (loginObj == null || loginObj.getResult() == null || loginObj.getResult().isEmpty()) {
				throw new IllegalStateException("Token response invalid or empty");
			}
			
			this.accessToken = loginObj.getResult();
			this.tokenFetchedDate = LocalDate.now();
			
			log.info("New token fetched at {}", tokenFetchedDate);
			return accessToken;
			
		} catch (Exception e) {
			log.error("Unexpected error while fetching token", e);
			throw new IllegalStateException("Failed to obtain token", e);
		}
	}

	@Override
	public JuristicRegistrationResponse findProfile(RequesterDetails requesterDetails) {
		JuristicRegistrationResponse response = null;
		String userNin = securityService.getCurrentUser().getUserNin();
		String token = token(userNin);
		
		// สร้าง Request Body ด้วย Map เพื่อความปลอดภัย
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("OrganizationJuristicID", requesterDetails.getOrganizationJuristicID());
		
		String responseBody = null;
		try {
			responseBody = callApiProfile(token, requestBody);
		} catch (ResponseStatusException ex) {
			// กรณี token หมดอายุ ให้ clear แล้วลองใหม่
			if (ex.getReason() != null && ex.getReason().contains("token expire")) {
				log.warn("Token expired, retrying with new token...");
				clearToken(); // ล้าง token ที่หมดอายุทิ้ง
				token = token(userNin); // ดึง token ใหม่
				responseBody = callApiProfile(token, requestBody); // retry ใหม่
			} else {
				throw ex; // ถ้าไม่ใช่ token expire ก็โยนต่อ
			}
		}
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			response = mapper.readValue(responseBody, JuristicRegistrationResponse.class);
			log.info("Success response api");
		} catch (JsonProcessingException e) {
			log.error("Error parsing responseBody", e);
		}
		return response;
		
	}
	
	private String callApiProfile(String token, Map<String, Object> body) {
		return webClient
				.post()
				.uri(WEB_API_URL_PROFILE)
				.header("Consumer-Key", properties.getConsumerKey())
				.header("Token", token)
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(body)
				.retrieve()
				.onStatus(HttpStatus::isError, res -> 
					res.bodyToMono(String.class).flatMap(error -> Mono.error(new ResponseStatusException(res.statusCode(), error)))
				)
				.bodyToMono(String.class)
				.block();
	}
	
	private void clearToken() {
		this.accessToken = null;
		this.tokenFetchedDate = null;
	}
}

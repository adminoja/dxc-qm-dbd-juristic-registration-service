package th.go.dxc.infra.connector_juristic_api.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

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

@Slf4j
public class JuristicRegistrationServiceWebClientImpl implements JuristicRegistrationService {
	private final static String WEB_API_URL_LOGIN = "/ws/auth/validate";
	private final static String WEB_API_URL_PROFILE = "/general/profile";
	private final WebClient webClient;
	private LoginResponse responseLogin;
	private DbdApiConfigurationProperties properties;

	@Autowired
	public JuristicRegistrationServiceWebClientImpl(WebClient.Builder webClientBuilder,
			DbdApiConfigurationProperties properties) {
		this.properties = properties;
		HttpClient httpClient = HttpClient.create().wiretap("reactor.netty.http.client.HttpClient", LogLevel.DEBUG,
				AdvancedByteBufFormat.TEXTUAL);
		this.webClient = webClientBuilder.baseUrl(properties.getBaseUrlValidate())
				.clientConnector(new ReactorClientHttpConnector(httpClient)).build();
//		this.Token();
	}

	@Override
	public void token(String userNin) {
		try {
			LoginResponse loginResponse = this.webClient.get()
					.uri(uriBuilder -> uriBuilder.path(WEB_API_URL_LOGIN)
							.queryParam("ConsumerSecret", properties.getConsumerSecret())
							.queryParam("AgentID", userNin) // ต้องเป็นเลขบัตรคนค้น
							.build())
					.accept(MediaType.APPLICATION_JSON).retrieve()
					.onStatus(HttpStatus::isError,
							response -> response.bodyToMono(String.class)
									.flatMap(body -> Mono.error(new BadGatewayException("API Error: " + body))))
					.bodyToMono(LoginResponse.class).block(); // ทำให้เป็น synchronous

			// ทำอะไรกับ loginResponse ต่อได้ตรงนี้
			log.info("Login success: {}", loginResponse);
			responseLogin = loginResponse;
		} catch (BadGatewayException e) {
			log.error("API returned error: {}", e.getMessage());
		} catch (Exception e) {
			log.error("Unexpected error", e);
		}
	}

	@Override
	public JuristicRegistrationResponse findProfile(RequesterDetails requesterDetails) {
		JuristicRegistrationResponse response = null;
		
		// ตรวจสอบว่า Access Token มีค่าหรือไม่
		if (responseLogin == null || responseLogin.getResult() == null) {
			throw new IllegalStateException("Access Token is missing. Please ensure you are logged in.");
		}
		// สร้าง Request Body ด้วย Map เพื่อความปลอดภัย
		Map<String, Object> requestBody = new HashMap<>();
		Map<String, Object> caseDetails = new HashMap<>();
		caseDetails.put("OrganizationJuristicID", requesterDetails.getOrganizationJuristicID());
		requestBody.put("caseList", Collections.singletonList(caseDetails));
		
		response = webClient
			.post()
			.uri(WEB_API_URL_PROFILE)
			.header("Consumer-Key", properties.getConsumerKey()) // Key
			.header("Token", responseLogin.getResult()) // Token
			.bodyValue(requestBody) // ใส่ body ที่จะส่ง
			.retrieve()
			.onStatus(HttpStatus::isError,
					clientResponse -> clientResponse.bodyToMono(String.class)
						.flatMap(errorResponseBody -> Mono.error(
							new ResponseStatusException(clientResponse.statusCode(), errorResponseBody))))
			.bodyToMono(JuristicRegistrationResponse.class)
			.doOnError(ResponseStatusException.class, error -> {
//				logClientReceive(error.getReason(), 0, error.getStatus());
				try {
					throw new BadGatewayException(error.getReason());
				} catch (BadGatewayException e) {
					e.printStackTrace();
				}
			})
			.doOnError(Exception.class, error -> {
				log.debug("error = {}", error);
//				logClientReceive(error.getMessage(), 0, HttpStatus.OK);
				try {
					throw new BadGatewayException(error.getMessage());
				} catch (BadGatewayException e) {
					e.printStackTrace();
				}
			})
			.block();
		
		return response;
	}

	// mock
//	@Override
//	public JuristicRegistrationResponse findProfile(RequesterDetails requesterDetails) {
//
//		try {
//			// โหลดไฟล์ JSON จาก resource
//			ClassPathResource resource = new ClassPathResource("mock-certificate-response.json");
//			String json = new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
//
//			// แปลง JSON เป็น CertificateResponse
//			Gson gson = new Gson();
//			JuristicRegistrationResponse response = gson.fromJson(json, JuristicRegistrationResponse.class);
//			
//			System.out.println("JuristicID = " + response);
//			// เช็กว่า organizationJuristicID ตรงกับที่ requester ส่งเข้ามา
//			if (response.getData() != null && requesterDetails != null && requesterDetails.getOrganizationJuristicID()
//					.equals(response.getData().getOrganization().getJuristicID())) {
//				return response;
//			} else {
//				// ถ้าไม่ตรง จะ return null หรือ throw exception ก็ได้
//				log.warn("organizationJuristicID ไม่ตรงกัน");
//				return null;
//			}
//
//		} catch (IOException e) {
//			log.error("เกิดข้อผิดพลาดในการอ่าน mock file", e);
//			return null;
//		}
//	}
}

package th.go.dxc.infra.connector_juristic_api.service;

import org.springframework.beans.factory.annotation.Autowired;
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
import th.go.dxc.infra.connector_juristic_api.model.response.LoginResponse;
import th.go.dxc.share.exception.BadGatewayException;

@Slf4j
public class JuristicRegistrationServiceWebClientImpl {
	private final static String WEB_API_URL_LOGIN = "/ws/auth/validate";
	private final static String WEB_API_URL_CERTIFICATE = "/document/profile/certificate";
	private final static String WEB_API_URL_PROFILE = "/general/profile";
	private final static String WEB_API_URL_LOOKUP = "/general/profile/lookup";
	private final WebClient webClient;
//	private String jsonBody;
//	Gson gson = new Gson();
	private LoginResponse responseLogin;
	private DbdApiConfigurationProperties properties;
	
	@Autowired
	public JuristicRegistrationServiceWebClientImpl(WebClient.Builder webClientBuilder, DbdApiConfigurationProperties properties) {
		this.properties = properties;
		HttpClient httpClient = HttpClient.create().wiretap("reactor.netty.http.client.HttpClient", LogLevel.DEBUG,
				AdvancedByteBufFormat.TEXTUAL);
		this.webClient = webClientBuilder
				.baseUrl(properties.getBaseUrlValidate())
				.clientConnector(new ReactorClientHttpConnector(httpClient)).build();
//		this.Token();
	}
	
	public void token() {
		try {
			LoginResponse loginResponse = this.webClient
				.get()
				.uri(uriBuilder ->
					uriBuilder.path(WEB_API_URL_LOGIN)
						.queryParam("ConsumerSecret", properties.getConsumerSecret())
						.queryParam("AgentID", "3100600620151") // ต้องเป็นเลขบัตรคนค้น
						.build()
				)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.onStatus(HttpStatus::isError, response ->
					response.bodyToMono(String.class).flatMap(body ->
						Mono.error(new BadGatewayException("API Error: " + body))
					)
				)
				.bodyToMono(LoginResponse.class)
				.block(); // ทำให้เป็น synchronous

			// ทำอะไรกับ loginResponse ต่อได้ตรงนี้
			log.info("Login success: {}", loginResponse);
			
			responseLogin = loginResponse;
		} catch (BadGatewayException e) {
			log.error("API returned error: {}", e.getMessage());
		} catch (Exception e) {
			log.error("Unexpected error", e);
		}
	}

	
}

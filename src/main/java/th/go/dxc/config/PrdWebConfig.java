package th.go.dxc.config;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
//import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
//import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
//import org.springframework.web.reactive.function.client.ClientRequest;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;
//import th.go.dxc.share.security.service.ServerLogInterceptor;

@Profile("prd")
@Configuration
@Slf4j
public class PrdWebConfig implements WebMvcConfigurer {
//	@Autowired
//	private OAuth2AuthorizedClientManager authorizedClientManager;

	public PrdWebConfig() {
		super();
		log.info("Init {}", this.getClass().getName());
	}
	
//	@Autowired
//	private ServerLogInterceptor serverLogInterceptor;

//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(serverLogInterceptor).addPathPatterns("/api/**");
//		WebMvcConfigurer.super.addInterceptors(registry);
//	}
	
//	@Bean
//	public WebClient webClient() {
//		return WebClient.builder().filter((request, next) -> {
//			OAuth2AuthorizedClient client = authorizedClientManager.authorize(OAuth2AuthorizeRequest
//					.withClientRegistrationId("your-client-registration-id").principal("your-principal").build());
//
//			return next.exchange(ClientRequest.from(request)
//					.headers(headers -> headers.setBearerAuth(client.getAccessToken().getTokenValue())).build());
//		}).build();
//	}

//	@Bean
//	public OAuth2AuthorizedClientManager authorizedClientManager(
//			ClientRegistrationRepository clientRegistrationRepository,
//			OAuth2AuthorizedClientRepository authorizedClientRepository) {
//
//		OAuth2AuthorizedClientProvider authorizedClientProvider = OAuth2AuthorizedClientProviderBuilder.builder()
//				.authorizationCode().refreshToken().clientCredentials().password().build();
//
//		DefaultOAuth2AuthorizedClientManager authorizedClientManager = new DefaultOAuth2AuthorizedClientManager(
//				clientRegistrationRepository, authorizedClientRepository);
//		authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
//
//		return authorizedClientManager;
//	}
}
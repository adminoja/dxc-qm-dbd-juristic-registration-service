package th.go.dxc.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import th.go.dxc.app.service.DbdJuristicRegistationService;
import th.go.dxc.app.service.DbdJuristicRegistationServiceJpaImpl;
import th.go.dxc.infra.connector_juristic_api.config.DbdApiConfigurationProperties;
import th.go.dxc.infra.connector_juristic_api.service.JuristicRegistrationService;
import th.go.dxc.infra.connector_juristic_api.service.JuristicRegistrationServiceWebClientImpl;
import th.go.dxc.share.commons.util.ObjectMapperService;
import th.go.dxc.share.security.service.SecurityService;
import th.go.dxc.share.security.service.SecurityServiceJwtImpl;

@Profile("prd")
@Configuration
@EnableScheduling
@EnableConfigurationProperties
@ConfigurationPropertiesScan(basePackages = {"th.go.dxc"})
@Slf4j
public class PrdAppConfig {
	public PrdAppConfig() {
		super();
		log.info("Init {}",this.getClass().getName());
	}

	@Bean
	public MapperFactory mapperFactory() {
		return new DefaultMapperFactory.Builder().build();
	}
	
	@Bean
	public SecurityService securityService() {
		return new SecurityServiceJwtImpl();
	}

	@Bean
	public JuristicRegistrationService juristicRegistrationService(WebClient.Builder webClientBuilder,
			DbdApiConfigurationProperties properties) {
		return new JuristicRegistrationServiceWebClientImpl(webClientBuilder, properties);
	}
	
	@Bean
	public DbdJuristicRegistationService dbdJuristicRegistationService(JuristicRegistrationService juristicRegistrationService,
			MapperFacade mapperFacade, ObjectMapperService mapperService, SecurityService securityService) {
		return new DbdJuristicRegistationServiceJpaImpl(juristicRegistrationService, mapperFacade, mapperService, securityService);
	}
}

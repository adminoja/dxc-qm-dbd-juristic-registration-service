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
import th.go.dxc.share.commons.util.ObjectMapperService;

@Profile("sit")
@Configuration
@EnableScheduling
@EnableConfigurationProperties
@ConfigurationPropertiesScan(basePackages = {"th.go.dxc"})
@Slf4j
public class SitAppConfig {
	public SitAppConfig() {
		super();
		log.info("Init {}",this.getClass().getName());
	}

	@Bean
	public MapperFactory mapperFactory() {
		return new DefaultMapperFactory.Builder().build();
	}
	
//	@Bean
//	public SecurityService securityService() {
//		return new SecurityServiceJwtImpl();
//	}
	
}

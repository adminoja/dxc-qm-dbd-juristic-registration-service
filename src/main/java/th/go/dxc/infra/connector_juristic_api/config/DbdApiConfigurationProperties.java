package th.go.dxc.infra.connector_juristic_api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "infra.connector.dbd-egov-api")
@Data
public class DbdApiConfigurationProperties {
	private String baseUrlValidate;
	private String baseUrl;
	private String consumerSecret;
	private String consumerKey;
}

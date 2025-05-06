package th.go.dxc.infra.connector_juristic_api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponse {
	private String ConsumerSecret;
	private String AgentID;
}

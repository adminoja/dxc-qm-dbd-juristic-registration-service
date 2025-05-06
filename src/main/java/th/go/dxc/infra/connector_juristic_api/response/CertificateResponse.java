package th.go.dxc.infra.connector_juristic_api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CertificateResponse {
	private Status status;
	private CertificateData data;

	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class Status {
		private String code;
		private String description;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class CertificateData {
		private String organizationJuristicID;
		private String fileName;
		private String fileSize;
		private Info info;
		private String mimeType;
		private String hashes;
		private String result;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class Info {
		private String name;
		private String numberOfPage;
	}
}

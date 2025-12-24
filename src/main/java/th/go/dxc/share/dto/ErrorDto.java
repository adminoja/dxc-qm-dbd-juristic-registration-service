package th.go.dxc.share.dto;

import java.time.OffsetDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description =  "ข้อมูลความผิดพลาด")
public class ErrorDto {
//	 "timestamp": "2021-04-15T03:21:35.303+00:00",
	@Schema(description = "วันเวลาที่เกิด")
	private OffsetDateTime timestamp;
//	  "status": 400,
	@Schema(description = "รหัสข้อผิดพลาด")
	private Integer status;
//	  "error": "Bad Request",
	@Schema(description = "ข้อผิดพลาด")
	private String error;
//	  "message": "IdCard is required.",
	@Schema(description = "รายละเอียดข้อผิดพลาด")
	private String message;
//	  "path": "/api/qm/v2/cifs/missing-persons"
	@Schema(description = "Path ที่เกิดข้อผิดพลาด")
	private String path;
}

package th.go.dxc.share.dto;

import java.time.OffsetDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description =  "ข้อมูลความผิดพลาด")
public class ErrorDto {
//	 "timestamp": "2021-04-15T03:21:35.303+00:00",
	@ApiModelProperty("วันเวลาที่เกิด")
	private OffsetDateTime timestamp;
//	  "status": 400,
	@ApiModelProperty("รหัสข้อผิดพลาด")
	private Integer status;
//	  "error": "Bad Request",
	@ApiModelProperty("ข้อผิดพลาด")
	private String error;
//	  "message": "IdCard is required.",
	@ApiModelProperty("รายละเอียดข้อผิดพลาด")
	private String message;
//	  "path": "/api/qm/v2/cifs/missing-persons"
	@ApiModelProperty("Path ที่เกิดข้อผิดพลาด")
	private String path;
}

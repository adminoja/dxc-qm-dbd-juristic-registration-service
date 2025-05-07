package th.go.dxc.app.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "ข้อมูลหนังสือรับรองนิติบุคคลในรูปแบบ PDF")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Certificate {
	@Schema(description = "สถานะการเรียกข้อมูลโดยดูจาก Code และ รายละเอียดที่ตอบกลับ")
	private Status status;
	@Schema(description = "ข้อมูลนิติบุคคล")
	private CertificateData data;
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class Status {
		@Schema(description = "รหัสการตอบกลับ")
		private String code;
		@Schema(description = "รายละเอียดการตอบกลับ")
		private String description;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class CertificateData {
		@Schema(description = "เลขทะเบียนนิติบุคคล 13 หลัก")
		private String organizationJuristicID;
		@Schema(description = "ชื่อแฟ้มข้อมูล")
		private String fileName;
		@Schema(description = "ขนาดแฟ้มข้อมูล (Byte)")
		private String fileSize;
		@Schema(description = "รายลเอียดในแฟ้มข้อมูล")
		private Info info;
		@Schema(description = "ประเภทแฟ้มข้อมูล")
		private String mimeType;
		@Schema(description = "แฮชไฟล์สำหรับตรวจสอบ")
		private String hashes;
		@Schema(description = "แฟ้มข้อมูลในรูปแบบ Base64")
		private String result;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class Info {
		@Schema(description = "ชื่อเอกสาร")	
		private String name;
		@Schema(description = "จำนวนหน้าทั้งหมดของเอกสาร")	
		private String numberOfPage;
	}
}

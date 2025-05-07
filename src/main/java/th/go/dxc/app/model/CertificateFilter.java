package th.go.dxc.app.model;

import javax.validation.constraints.NotBlank;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "เงื่อนไขการค้นหาข้อมูล")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CertificateFilter {
	@Parameter(description = "เลขทะเบียนนิติบุคคล 13 หลัก")
	@NotBlank
	private String OrganizationJuristicID;
}

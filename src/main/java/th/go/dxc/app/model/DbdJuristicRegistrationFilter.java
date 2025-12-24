package th.go.dxc.app.model;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(description = "เงื่อนไขการค้นหาข้อมูล")
@Data
public class DbdJuristicRegistrationFilter {
	@Parameter(description = "เลขทะเบียนนิติบุคคล 13 หลัก")
	@NotBlank
	private String organizationJuristicID;
}

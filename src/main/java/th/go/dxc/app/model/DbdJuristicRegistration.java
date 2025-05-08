package th.go.dxc.app.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "ข้อมูลนิติบุคคล")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DbdJuristicRegistration {
	@Schema(description = "สถานะการเรียกข้อมูลโดยดูจาก Code และ รายละเอียดที่ตอบกลับ")
	private Status status;
	private ProfileData data;

	@Data
	public static class Status {
		@Schema(description = "รหัสการตอบกลับ")
		private String code;
		@Schema(description = "รายละเอียดการตอบกลับ")
		private String description;
	}

	@Data
	public static class ProfileData {
		private OrganizationJuristicPerson organization;
	}

	@Data
	public static class OrganizationJuristicPerson {
		@Schema(description = "เลขทะเบียนนิติบุคคล 13 หลัก")
		private String juristicID;
		@Schema(description = "เลขทะเบียนนิติบุคคลเดิม")
		private String oldJuristicID;
		@Schema(description = "ชื่อนิติบุคคล (ภาษาไทย)")
		private String nameTH;
		@Schema(description = "ชื่อนิติบุคคล (ภาษาอังกฤษ)")
		private String nameEN;
		@Schema(description = "ประเภทนิติบุคคล")
		private String type;
		@Schema(description = "วันที่จดทะเบียนจัดตั้งของนิติบุคคล")
		private String registerDate;
		@Schema(description = "สถานะของนิติบุคคล")
		private String status;
		@Schema(description = "วัตถุประสงค์ของนิติบุคคล")
		private List<JuristicObjective> objectives;
		@Schema(description = "จำนวนข้อวัตถุประสงค์")
		private String objectiveItems;
		@Schema(description = "จำนวนแผ่น")
		private String objectivePages;
		@Schema(description = "ทุนจดทะเบียน (บาท)")
		private String registerCapital;
		@Schema(description = "ทุนเรียกชำระแล้ว")
		private String paidUpCapital;
		@Schema(description = "รายชื่อบุคคลที่เป็นกรรมการ/ผู้เป็นหุ้นส่วนของนิติบุคคล")
		private List<JuristicPerson> personList;
		@Schema(description = "ชื่อสาขาของนิติบุคคล")
		private String branchName;
		@Schema(description = "ที่ตั้งของสำนักงานนิติบุคคล")
		private AddressWrapper address;
		@Schema(description = "ข้อมูลอื่น ๆ ของนิติบุคคล")
		private List<JuristicPersonDescription> descriptions;
		@Schema(description = "รองรับการให้บริการ")
		private String digitalIDFlag;
	}

	@Data
	public static class JuristicObjective {
		@Schema(description = "R=วัตถุประสงค์ตอนจัดตั้ง F=วัตถุประสงค์ที่ยื่นงบการเงินปีล่าสุด")
		private String objective;
		@Schema(description = "วัตถุประสงค์ของนิติบุคคลอ้างอิงตามรหัส TSIC")
		private String code;
		@Schema(description = "วัตถุประสงค์ของนิติบุคคล (ภาษาไทย)")
		private String textTH;
		@Schema(description = "วัตถุประสงค์ของนิติบุคคล (ภาษาอังกฤษ)")
		private String textEN;
	}

	@Data
	public static class JuristicPerson {
		@Schema(description = "ลำดับ")
		private int sequence;
		@Schema(description = "ประเภทบุคคล (กรรมการ/ผู้เป็นหุ้นส่วน)")
		private String type;
		@Schema(description = "ข้อมูลบุคคลที่เป็นกรรมการ/ผู้เป็นหุ้นส่วนของนิติบุคคล")
		private JuristicPersonDetail person;
		@Schema(description = "เฉพาะกรณีห้างหุ้นส่วน ลงหุ้นด้วย เงินสด ทรัพย์สิน แรงงาน")
		private String investType;
		@Schema(description = "เฉพาะกรณีห้างหุ้นส่วน จำนวนเงินลงทุน")
		private String investAmount;
	}

	@Data
	public static class JuristicPersonDetail {
		@Schema(description = "ชื่อของบุคคล(ภาษาไทย)")
		private PersonName nameTH;
	}

	@Data
	public static class PersonName {
		@Schema(description = "คำนำหน้าชื่อของบุคคล(ภาษาไทย)")
		private String title;
		@Schema(description = "ชื่อตัวของบุคคล (ภาษาไทย)")
		private String firstName;
		@Schema(description = "ชื่อรองของบุคคล (ภาษาไทย)")
		private String middleName;
		@Schema(description = "ชื่อสกุลของบุคคล(ภาษาไทย)")
		private String lastName;
	}

	@Data
	public static class AddressWrapper {
		@Schema(description = "ประเภทที่อยู่")
		private Address address;
	}

	@Data
	public static class Address {
		@Schema(description = "ที่อยู่")
		private String address;
		@Schema(description = "ชื่อตึก/อาคาร")
		private String building;
		@Schema(description = "เลขที่ห้อง")
		private String roomNo;
		@Schema(description = "ชั้นที่")
		private String floor;
		@Schema(description = "เลขที่บ้านหรืออาคาร")
		private String addressNo;
		@Schema(description = "หมู่ที่")
		private String moo;
		@Schema(description = "แยก")
		private String yaek;
		@Schema(description = "ซอย")
		private String soi;
		@Schema(description = "ตรอก")
		private String trok;
		@Schema(description = "หมู่บ้าน")
		private String village;
		@Schema(description = "ถนน")
		private String road;
		@Schema(description = "ตำบล")
		private Division subDivision;
		@Schema(description = "อำเภอ")
		private Division city;
		@Schema(description = "จังหวัด")
		private Division province;
	}

	@Data
	public static class Division {
		@Schema(description = "รหัสของตำบล")
		private String code;
		@Schema(description = "ชื่อของตำบล (ภาษาไทย)")
		private String textTH;
		@Schema(description = "รหัสของอำเภอ")
		private String cityCode;
		@Schema(description = "ชื่อของอำเภอ (ภาษาไทย)")
		private String cityTextTH;
		@Schema(description = "รหัสของจังหวัด")
		private String provinceCode;
		@Schema(description = "ชื่อของจังหวัด (ภาษาไทย)")
		private String provinceTextTH;
	}

	@Data
	public static class JuristicPersonDescription {
		@Schema(description = "ลำดับของข้อมูล")
		private int sequence;
		@Schema(description = "อำนาจกรรมการ ข้อจำกัดอำนาจหุ้นส่วนผู้จัดการ รายการอื่นที่เห็นสมควรทราบ หมายเหตุการเปลี่ยนชื่อ หมายเหตุอื่น")
		private String type;
		@Schema(description = "รายละเอียดข้อมูล")
		private String detail;
	}
}

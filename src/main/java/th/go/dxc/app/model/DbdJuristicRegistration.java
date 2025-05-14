package th.go.dxc.app.model;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import th.go.dxc.infra.connector_juristic_api.model.response.JuristicRegistrationResponse.OrganizationJuristicPersonDescription;
import th.go.dxc.infra.connector_juristic_api.model.response.JuristicRegistrationResponse.OrganizationJuristicPersonList;

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
		private OrganizationJuristicPerson organizationJuristicPerson;
	}

	@Data
	public static class OrganizationJuristicPerson {
		@Schema(description = "เลขทะเบียนนิติบุคคล 13 หลัก")
		private String organizationJuristicID;
		@Schema(description = "เลขทะเบียนนิติบุคคลเดิม")
		private String organizationOldJuristicID;
		@Schema(description = "ชื่อนิติบุคคล (ภาษาไทย)")
		private String organizationJuristicNameTH;
		@Schema(description = "ชื่อนิติบุคคล (ภาษาอังกฤษ)")
		private String organizationJuristicNameEN;
		@Schema(description = "ประเภทนิติบุคคล")
		private String organizationJuristicType;
		@Schema(description = "วันที่จดทะเบียนจัดตั้งของนิติบุคคล")
		private String organizationJuristicRegisterDate;
		@Schema(description = "สถานะของนิติบุคคล")
		private String organizationJuristicStatus;
		@Schema(description = "วัตถุประสงค์ของนิติบุคคล")
		private List<OrganizationJuristicObjective> organizationJuristicObjective;
		@Schema(description = "จำนวนข้อวัตถุประสงค์")
		private String organizationJuristicObjectiveItems;
		@Schema(description = "จำนวนแผ่น")
		private String organizationJuristicObjectivePages;
		@Schema(description = "ทุนจดทะเบียน (บาท)")
		private String organizationJuristicRegisterCapital;
		@Schema(description = "ทุนเรียกชำระแล้ว")
		private String organizationJuristicPaidUpCapital;
		@Schema(description = "รายชื่อบุคคลที่เป็นกรรมการ/ผู้เป็นหุ้นส่วนของนิติบุคคล")
		private List<OrganizationJuristicPersonList> organizationJuristicPersonList;
		@Schema(description = "ชื่อสาขาของนิติบุคคล")
		private String organizationJuristicBranchName;
		@Schema(description = "ที่ตั้งของสำนักงานนิติบุคคล")
		private OrganizationJuristicAddress organizationJuristicAddress;
		@Schema(description = "ข้อมูลอื่น ๆ ของนิติบุคคล")
		private List<OrganizationJuristicPersonDescription> organizationJuristicPersonDescription;
		@Schema(description = "ประวัติการนำส่งงบการเงิน")
		private String financialSubmitRecord;
		@Schema(description = "รองรับการให้บริการ")
		private String digitalIDFlag;
	}

	@Data
	public static class OrganizationJuristicObjective {
		@Schema(description = "R=วัตถุประสงค์ตอนจัดตั้ง F=วัตถุประสงค์ที่ยื่นงบการเงินปีล่าสุด")
		private String juristicObjective;
		@Schema(description = "วัตถุประสงค์ของนิติบุคคลอ้างอิงตามรหัส TSIC")
		private String juristicObjectiveCode;
		@Schema(description = "วัตถุประสงค์ของนิติบุคคล (ภาษาไทย)")
		private String juristicObjectiveTextTH;
		@Schema(description = "วัตถุประสงค์ของนิติบุคคล (ภาษาอังกฤษ)")
		private String juristicObjectiveTextEN;
	}

	@Data
	public static class OrganizationJuristicPersonList {
		@Schema(description = "ลำดับ")
		private int juristicPersonSequence;
		@Schema(description = "ประเภทบุคคล (กรรมการ/ผู้เป็นหุ้นส่วน)")
		private String juristicPersonType;
		@Schema(description = "ข้อมูลบุคคลที่เป็นกรรมการ/ผู้เป็นหุ้นส่วนของนิติบุคคล")
		private JuristicPerson juristicPerson;
		@Schema(description = "เฉพาะกรณีห้างหุ้นส่วน ลงหุ้นด้วย เงินสด ทรัพย์สิน แรงงาน")
		private String juristicPersonInvestType;
		@Schema(description = "เฉพาะกรณีห้างหุ้นส่วน จำนวนเงินลงทุน")
		private String juristicPersonInvestAmount;
	}

	@Data
	public static class JuristicPerson {
		@Schema(description = "ชื่อของบุคคล(ภาษาไทย)")
		private PersonName personNameTH;
	}

	@Data
	public static class PersonName {
		@Schema(description = "คำนำหน้าชื่อของบุคคล(ภาษาไทย)")
		private String personNameTitleTextTH;
		@Schema(description = "ชื่อตัวของบุคคล (ภาษาไทย)")
		private String personFirstNameTH;
		@Schema(description = "ชื่อรองของบุคคล (ภาษาไทย)")
		private String personMiddleNameTH;
		@Schema(description = "ชื่อสกุลของบุคคล(ภาษาไทย)")
		private String personLastNameTH;
	}

	@Data
	public static class OrganizationJuristicAddress {
		@Schema(description = "ประเภทที่อยู่")
		private AddressType addressType;
	}

	@Data
	public static class AddressType {
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
		private CitySubDivision citySubDivision;
		@Schema(description = "อำเภอ")
		private City city;
		@Schema(description = "จังหวัด")
		private CountrySubDivision countrySubDivision;
	}
	
	@Data
	public static class CitySubDivision {
		@Schema(description = "รหัสของตำบล")
		private String citySubDivisionCode;
		@Schema(description = "ชื่อของตำบล (ภาษาไทย)")
		private String citySubDivisionTextTH;
	}
	
	@Data
	public static class City {
		@Schema(description = "รหัสของอำเภอ")
		private String cityCode;
		@Schema(description = "ชื่อของอำเภอ (ภาษาไทย)")
		private String cityTextTH;
	}
	
	@Data
	public static class CountrySubDivision {
		@Schema(description = "รหัสของจังหวัด")
		private String countrySubDivisionCode;
		@Schema(description = "ชื่อของจังหวัด (ภาษาไทย)")
		private String countrySubDivisionTextTH;
	}

	@Data
	public static class OrganizationJuristicPersonDescription {
		@Schema(description = "ลำดับของข้อมูล")
		private int organizationJuristicPersonDescriptionSequence;
		@Schema(description = "อำนาจกรรมการ ข้อจำกัดอำนาจหุ้นส่วนผู้จัดการ รายการอื่นที่เห็นสมควรทราบ หมายเหตุการเปลี่ยนชื่อ หมายเหตุอื่น")
		private String organizationJuristicPersonDescriptionType;
		@Schema(description = "รายละเอียดข้อมูล")
		private String organizationJuristicPersonDescriptionDetail;
	}
}

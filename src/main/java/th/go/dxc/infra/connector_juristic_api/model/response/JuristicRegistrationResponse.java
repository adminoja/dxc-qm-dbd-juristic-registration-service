package th.go.dxc.infra.connector_juristic_api.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JuristicRegistrationResponse {
	private Status status;
	private ProfileData data;

	@Data
	public static class Status {
		private String code;
		private String description;
	}

	@Data
	public static class ProfileData {
		@JsonProperty("cd:OrganizationJuristicPerson")
		private OrganizationJuristicPerson organization;
	}

	@Data
	public static class OrganizationJuristicPerson {
		@JsonProperty("cd:OrganizationJuristicID")
		private String juristicID;
		@JsonProperty("cd:OrganizationOldJuristicID")
		private String oldJuristicID;
		@JsonProperty("cd:OrganizationJuristicNameTH")
		private String nameTH;
		@JsonProperty("cd:OrganizationJuristicNameEN")
		private String nameEN;
		@JsonProperty("cd:OrganizationJuristicType")
		private String type;
		@JsonProperty("cd:OrganizationJuristicRegisterDate")
		private String registerDate;
		@JsonProperty("cd:OrganizationJuristicStatus")
		private String status;
		@JsonProperty("cd:OrganizationJuristicObjective")
		private List<JuristicObjective> objectives;
		@JsonProperty("cd:OrganizationJuristicObjectiveItems")
		private String objectiveItems;
		@JsonProperty("cd:OrganizationJuristicObjectivePages")
		private String objectivePages;
		@JsonProperty("cd:OrganizationJuristicRegisterCapital")
		private String registerCapital;
		@JsonProperty("cd:OrganizationJuristicPaidUpCapital")
		private String paidUpCapital;
		@JsonProperty("cd:OrganizationJuristicPersonList")
		private List<JuristicPerson> personList;
		@JsonProperty("cd:OrganizationJuristicBranchName")
		private String branchName;
		@JsonProperty("cd:OrganizationJuristicAddress")
		private Address address;
		@JsonProperty("cd:OrganizationJuristicPersonDescription")
		private List<JuristicPersonDescription> descriptions;
		@JsonProperty("td:DigitalIDFlag")
		private String digitalIDFlag;
	}

	@Data
	public static class JuristicObjective {
		@JsonProperty("td:JuristicObjective")
		private String objective;
		@JsonProperty("td:JuristicObjectiveCode")
		private String code;
		@JsonProperty("td:JuristicObjectiveTextTH")
		private String textTH;
		@JsonProperty("td:JuristicObjectiveTextEN")
		private String textEN;
	}

	@Data
	public static class JuristicPerson {
		@JsonProperty("td:JuristicPersonSequence")
		private int sequence;
		@JsonProperty("td:JuristicPersonType")
		private String type;
		@JsonProperty("td:JuristicPerson")
		private JuristicPersonDetail person;
		@JsonProperty("td:JuristicPersonInvestType")
		private String investType;
		@JsonProperty("td:JuristicPersonInvestAmount")
		private String investAmount;
	}

	@Data
	public static class JuristicPersonDetail {
		@JsonProperty("cd:PersonNameTH")
		private PersonName nameTH;
	}

	@Data
	public static class PersonName {
		@JsonProperty("cd:PersonNameTitleTextTH")
		private String title;
		@JsonProperty("cd:PersonFirstNameTH")
		private String firstName;
		@JsonProperty("cd:PersonMiddleNameTH")
		private String middleName;
		@JsonProperty("cd:PersonLastNameTH")
		private String lastName;
	}

	@Data
	public static class Address {
		@JsonProperty("cr:AddressType")
		private AddressType addressType;
	}

	@Data
	public static class AddressType {
		@JsonProperty("cd:Address")
		private String address;
		@JsonProperty("cd:Building")
		private String building;
		@JsonProperty("cd:RoomNo")
		private String roomNo;
		@JsonProperty("cd:Floor")
		private String floor;
		@JsonProperty("cd:AddressNo")
		private String addressNo;
		@JsonProperty("cd:Moo")
		private String moo;
		@JsonProperty("cd:Yaek")
		private String yaek;
		@JsonProperty("cd:Soi")
		private String soi;
		@JsonProperty("cd:Trok")
		private String trok;
		@JsonProperty("cd:Village")
		private String village;
		@JsonProperty("cd:Road")
		private String road;
		@JsonProperty("cd:CitySubDivision")
		private CitySubDivision citySub;
		@JsonProperty("cd:City")
		private City city;
		@JsonProperty("cd:CountrySubDivision")
		private CountrySubDivision countrySubDivision;
	}

	@Data
	public static class CitySubDivision {
		@JsonProperty("cr:CitySubDivisionCode")
		private String citySubCode;
		@JsonProperty("cr:CitySubDivisionTextTH")
		private String citySubTextTH;
	}
	
	@Data
	public static class City {
		@JsonProperty("cr:CityCode")
		private String cityCode;
		@JsonProperty("cr:CityTextTH")
		private String cityTextTH;
	}
	
	@Data
	public static class CountrySubDivision {
		@JsonProperty("cr:CountrySubDivisionCode")
		private String provinceCode;
		@JsonProperty("cr:CountrySubDivisionTextTH")
		private String provinceTextTH;
	}

	@Data
	public static class JuristicPersonDescription {
		@JsonProperty("cd:OrganizationJuristicPersonDescriptionSequence")
		private int sequence;
		@JsonProperty("cd:OrganizationJuristicPersonDescriptionType")
		private String type;
		@JsonProperty("cd:OrganizationJuristicPersonDescriptionDetail")
		private String detail;
	}
}

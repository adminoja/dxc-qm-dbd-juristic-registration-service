package th.go.dxc.infra.connector_juristic_api.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class JuristicRegistrationResponse {
	private Status status;
	private ProfileData data;

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
	public static class ProfileData {
		@JsonProperty("cd:OrganizationJuristicPerson")
		private OrganizationJuristicPerson organizationJuristicPerson;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class OrganizationJuristicPerson {
		@JsonProperty("cd:OrganizationJuristicID")
		private String organizationJuristicID;
		@JsonProperty("cd:OrganizationOldJuristicID")
		private String organizationOldJuristicID;
		@JsonProperty("cd:OrganizationJuristicNameTH")
		private String organizationJuristicNameTH;
		@JsonProperty("cd:OrganizationJuristicNameEN")
		private String organizationJuristicNameEN;
		@JsonProperty("cd:OrganizationJuristicType")
		private String organizationJuristicType;
		@JsonProperty("cd:OrganizationJuristicRegisterDate")
		private String organizationJuristicRegisterDate;
		@JsonProperty("cd:OrganizationJuristicStatus")
		private String organizationJuristicStatus;
		@JsonProperty("cd:OrganizationJuristicObjective")
		private List<OrganizationJuristicObjective> organizationJuristicObjective;
		@JsonProperty("cd:OrganizationJuristicObjectiveItems")
		private String organizationJuristicObjectiveItems;
		@JsonProperty("cd:OrganizationJuristicObjectivePages")
		private String organizationJuristicObjectivePages;
		@JsonProperty("cd:OrganizationJuristicRegisterCapital")
		private String organizationJuristicRegisterCapital;
		@JsonProperty("cd:OrganizationJuristicPaidUpCapital")
		private String organizationJuristicPaidUpCapital;
		@JsonProperty("cd:OrganizationJuristicPersonList")
		private List<OrganizationJuristicPersonList> organizationJuristicPersonList;
		@JsonProperty("cd:OrganizationJuristicBranchName")
		private String organizationJuristicBranchName;
		@JsonProperty("cd:OrganizationJuristicAddress")
		private OrganizationJuristicAddress organizationJuristicAddress;
		@JsonProperty("cd:OrganizationJuristicPersonDescription")
		private List<OrganizationJuristicPersonDescription> organizationJuristicPersonDescription;
		@JsonProperty("td:FinancialSubmitRecord")
		private String financialSubmitRecord;
		@JsonProperty("td:DigitalIDFlag")
		private String digitalIDFlag;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class OrganizationJuristicObjective {
		@JsonProperty("td:JuristicObjective")
		private String juristicObjective;
		@JsonProperty("td:JuristicObjectiveCode")
		private String juristicObjectiveCode;
		@JsonProperty("td:JuristicObjectiveTextTH")
		private String juristicObjectiveTextTH;
		@JsonProperty("td:JuristicObjectiveTextEN")
		private String juristicObjectiveTextEN;
	}

	@Data
	public static class OrganizationJuristicPersonList {
		@JsonProperty("td:JuristicPersonSequence")
		private int juristicPersonSequence;
		@JsonProperty("td:JuristicPersonType")
		private String juristicPersonType;
		@JsonProperty("td:JuristicPerson")
		private JuristicPerson juristicPerson;
		@JsonProperty("td:JuristicPersonInvestType")
		private String juristicPersonInvestType;
		@JsonProperty("td:JuristicPersonInvestAmount")
		private String juristicPersonInvestAmount;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class JuristicPerson {
		@JsonProperty("cd:PersonNameTH")
		private PersonNameTH personNameTH;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class PersonNameTH {
		@JsonProperty("cd:PersonNameTitleTextTH")
		private String personNameTitleTextTH;
		@JsonProperty("cd:PersonFirstNameTH")
		private String personFirstNameTH;
		@JsonProperty("cd:PersonMiddleNameTH")
		private String personMiddleNameTH;
		@JsonProperty("cd:PersonLastNameTH")
		private String personLastNameTH;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class OrganizationJuristicAddress {
		@JsonProperty("cr:AddressType")
		private AddressType addressType;
	}

	@AllArgsConstructor
	@NoArgsConstructor
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
		private CitySubDivision citySubDivision;
		@JsonProperty("cd:City")
		private City city;
		@JsonProperty("cd:CountrySubDivision")
		private CountrySubDivision countrySubDivision;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class CitySubDivision {
		@JsonProperty("cr:CitySubDivisionCode")
		private String citySubDivisionCode;
		@JsonProperty("cr:CitySubDivisionTextTH")
		private String citySubDivisionTextTH;
	}
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class City {
		@JsonProperty("cr:CityCode")
		private String cityCode;
		@JsonProperty("cr:CityTextTH")
		private String cityTextTH;
	}
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class CountrySubDivision {
		@JsonProperty("cr:CountrySubDivisionCode")
		private String countrySubDivisionCode;
		@JsonProperty("cr:CountrySubDivisionTextTH")
		private String countrySubDivisionTextTH;
	}

	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class OrganizationJuristicPersonDescription {
		@JsonProperty("cd:OrganizationJuristicPersonDescriptionSequence")
		private int organizationJuristicPersonDescriptionSequence;
		@JsonProperty("cd:OrganizationJuristicPersonDescriptionType")
		private String organizationJuristicPersonDescriptionType;
		@JsonProperty("cd:OrganizationJuristicPersonDescriptionDetail")
		private String organizationJuristicPersonDescriptionDetail;
	}
}

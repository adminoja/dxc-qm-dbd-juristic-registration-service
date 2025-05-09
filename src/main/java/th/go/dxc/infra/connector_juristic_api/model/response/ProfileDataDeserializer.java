//package th.go.dxc.infra.connector_juristic_api.model.response;
//
//import java.io.IOException;
//
//import com.fasterxml.jackson.core.JsonFactory;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.core.ObjectCodec;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import com.fasterxml.jackson.databind.JsonNode;
//
//import lombok.extern.slf4j.Slf4j;
//import th.go.dxc.infra.connector_juristic_api.model.response.JuristicRegistrationResponse.OrganizationJuristicPerson;
//import th.go.dxc.infra.connector_juristic_api.model.response.JuristicRegistrationResponse.ProfileData;
//
//@Slf4j
//public class ProfileDataDeserializer extends JsonDeserializer<ProfileData> {
//	
//	@Override
//	public ProfileData deserialize(JsonParser p, DeserializationContext ctxt)
//			throws IOException {
//		try {
//			ObjectCodec codec = p.getCodec();
//			JsonNode node = codec.readTree(p);
//
//			if (node.isTextual()) {
//				String json = node.asText();
//				JsonNode innerNode = codec.readTree(new JsonFactory().createParser(json));
//				ProfileData data = new ProfileData();
//				data.setOrganization(codec.treeToValue(innerNode.get("cd:OrganizationJuristicPerson"), OrganizationJuristicPerson.class));
//				return data;
//			}
//
//			return codec.treeToValue(node, ProfileData.class);
//
//		} catch (Exception e) {
//			log.warn("Failed to deserialize ProfileData: {}", e.getMessage());
//			return null; // หรือโยน custom exception เองแบบสั้น
//		}
//	}
//
//}

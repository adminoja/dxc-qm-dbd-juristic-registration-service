package th.go.dxc.infra.connector_juristic_api.model.response;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import th.go.dxc.infra.connector_juristic_api.model.response.JuristicRegistrationResponse.ProfileData;

public class ProfileDataDeserializer extends JsonDeserializer<ProfileData> {
	
	@Override
	public ProfileData deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
		JsonNode node = p.getCodec().readTree(p);

		// ถ้า data เป็น string "{}", ไม่ต้องแปลงอะไร
		if (node.isTextual() && "{}".equals(node.textValue())) {
			return null;
		}

		// ถ้า data เป็น object เปล่า {}
		if (node.isObject() && node.size() == 0) {
			return null;
		}

		// ปกติ แปลงเป็น ProfileData ได้เลย
		ObjectMapper mapper = (ObjectMapper) p.getCodec();
		return mapper.treeToValue(node, ProfileData.class);
	}
}

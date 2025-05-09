package th.go.dxc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnabledIfSystemProperty(named = "run-sit", matches = "true")
class DxcQmDbdJuristicRegistrationServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}

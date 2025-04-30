package th.go.dxc.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DbdJuristicRegistrationApi {
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello, world!";
	}
}

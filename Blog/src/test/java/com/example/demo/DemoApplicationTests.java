package com.example.demo;

import com.example.demo.domain.appUser.User;
import com.example.demo.domain.appUser.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import javax.management.InstanceNotFoundException;
import java.util.Objects;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DemoApplicationTests {

	RestTemplate restTemplate = new RestTemplate();

	@Autowired
	UserService userService;


	@Test
	@Order(1)
	@DisplayName("Test if the application is up and running")
	void testIfRunning() {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/user/", String.class);
		assert(Objects.requireNonNull(response.getBody()).equals("Hello World"));
		assert(response.getStatusCode().is2xxSuccessful());
	}

	@Test
	@Order(2)
	@DisplayName("Save a new user")
	void saveUsers() throws InstanceNotFoundException {
		User user = new User(null, "test", "test@test.ch", "test", null);
		user = userService.save(user);
		assert(userService.findById(user.getId()).isPresent());
	}
}

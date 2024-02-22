package maria.anikina.springsecurity.controller;

import lombok.AllArgsConstructor;
import maria.anikina.springsecurity.model.PersonEntity;
import maria.anikina.springsecurity.security.JwtGeneration;
import maria.anikina.springsecurity.service.PersonService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
	private final PersonService personService;
	private final JwtGeneration jwtGeneration;
	private final AuthenticationManager authenticationManager;

	@PostMapping("/registration")
	public Map<String, String> performRegistration(@RequestBody PersonEntity person) {
		personService.registerPerson(person);
		String token = jwtGeneration.generationToken(person.getUsername());
		return Map.of("jwt-token", token);
	}

	@PostMapping("/login")
	public Map<String, String> performLogin(@RequestBody PersonEntity person) {
		UsernamePasswordAuthenticationToken authInputToken =
				new UsernamePasswordAuthenticationToken(person.getUsername(),
						person.getPassword());
		authenticationManager.authenticate(authInputToken);
		String token = jwtGeneration.generationToken(person.getUsername());
		return Map.of("jwt-token", token);
	}
}

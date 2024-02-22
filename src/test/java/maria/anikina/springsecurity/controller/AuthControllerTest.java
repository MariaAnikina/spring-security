package maria.anikina.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import maria.anikina.springsecurity.config.SecurityConfig;
import maria.anikina.springsecurity.model.PersonEntity;
import maria.anikina.springsecurity.service.PersonService;
import maria.anikina.springsecurity.util.PersonValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@WebMvcTest(AuthController.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Import(SecurityConfig.class)
class AuthControllerTest {
	@MockBean
	private PersonValidator personValidator;
	@MockBean
	private PersonService personService;
	@Autowired
	private MockMvc mvc;
	private final PersonEntity person = new PersonEntity(1, "ADMIN", "123",
			"A.A.A.", 100000, "ROLE_ADMIN");


	@SneakyThrows
	@Test
	void checkLoginPage() {
		mvc.perform(get("/auth/login")
						.with(SecurityMockMvcRequestPostProcessors.user("ADMIN").roles("ADMIN"))
						.with(SecurityMockMvcRequestPostProcessors.csrf())
						.characterEncoding(StandardCharsets.UTF_8)
						.accept(MediaType.TEXT_HTML))
				.andExpect(view().name("auth/login"))
				.andExpect(status().isOk());
	}

	@SneakyThrows
	@Test
	void checkRegistrationPage() {
		mvc.perform(get("/auth/registration")
						.with(SecurityMockMvcRequestPostProcessors.csrf())
						.characterEncoding(StandardCharsets.UTF_8)
						.accept(MediaType.TEXT_HTML))
				.andExpect(view().name("auth/registration"))
				.andExpect(status().is(200));
	}

	@SneakyThrows
	@Test
	void checkPerformRegistration() {
		when(personService.registerPerson(any(PersonEntity.class))).thenReturn(person);

		mvc.perform(post("/auth/registration")
						.with(SecurityMockMvcRequestPostProcessors.csrf())
						.characterEncoding(StandardCharsets.UTF_8)
						.accept(MediaType.TEXT_HTML))
				.andExpect(view().name("redirect:/auth/login"))
				.andExpect(status().is(302));
	}
}
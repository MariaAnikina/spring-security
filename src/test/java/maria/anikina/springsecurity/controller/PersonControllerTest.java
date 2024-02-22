package maria.anikina.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import maria.anikina.springsecurity.config.SecurityConfig;
import maria.anikina.springsecurity.model.PersonEntity;
import maria.anikina.springsecurity.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonController.class)
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Import(SecurityConfig.class)
class PersonControllerTest {
	@MockBean
	private PersonService personService;
	@Autowired
	private MockMvc mvc;
	private final List<PersonEntity> persons = List.of(
			new PersonEntity(1, "ADMIN", "123", "A.A.A.", 100000, "ROLE_ADMIN"),
			new PersonEntity(1, "SUPERVISOR", "123", "A.B.A.", 200000, "ROLE_SUPERVISOR"));

	@SneakyThrows
	@Test
	void gettingAllEmployeesBySupervisor() {
		when(personService.getAllPerson()).thenReturn(persons);

		mvc.perform(get("/person")
						.with(SecurityMockMvcRequestPostProcessors.user("SUPERVISOR").roles("SUPERVISOR"))
						.characterEncoding(StandardCharsets.UTF_8)
						.accept(MediaType.TEXT_HTML))
				.andExpect(view().name("all-persons"))
				.andExpect(model().attribute("persons", persons))
				.andExpect(status().isOk());
	}

	@SneakyThrows
	@Test
	void gettingAllEmployeesByAdministrator() {
		when(personService.getAllPerson()).thenReturn(persons);

		mvc.perform(get("/person")
						.with(SecurityMockMvcRequestPostProcessors.user("SUPERVISOR").roles("ADMIN"))
						.characterEncoding(StandardCharsets.UTF_8)
						.accept(MediaType.TEXT_HTML))
				.andExpect(view().name("all-persons"))
				.andExpect(model().attribute("persons", persons))
				.andExpect(status().isOk());
	}

	@SneakyThrows
	@Test
	void gettingAllEmployeesByUser() {
		when(personService.getAllPerson()).thenReturn(persons);

		mvc.perform(get("/person")
						.with(SecurityMockMvcRequestPostProcessors.user("user").roles("USER"))
						.characterEncoding(StandardCharsets.UTF_8)
						.accept(MediaType.TEXT_HTML))
				.andExpect(status().is(403));
	}

	@SneakyThrows
	@Test
	void gettingAllEmployeesByUnregisteredUser() {
		when(personService.getAllPerson()).thenReturn(persons);

		mvc.perform(get("/person")
						.characterEncoding(StandardCharsets.UTF_8)
						.accept(MediaType.TEXT_HTML))
				.andExpect(status().is(302));
	}
}
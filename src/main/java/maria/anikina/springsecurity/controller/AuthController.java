package maria.anikina.springsecurity.controller;

import lombok.AllArgsConstructor;
import maria.anikina.springsecurity.model.PersonEntity;
import maria.anikina.springsecurity.service.PersonService;
import maria.anikina.springsecurity.util.PersonValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/auth")
public class AuthController {

	private final PersonValidator personValidator;
	private final PersonService personService;

	@GetMapping("/login")
	public String loginPage() {
		return "auth/login";
	}


	@GetMapping("/registration")
	public String registrationPage(@ModelAttribute("person") PersonEntity person) {
		return "auth/registration";
	}


	@PostMapping("/registration")
	public String performRegistration(@ModelAttribute("person") PersonEntity person,
	                                  BindingResult bindingResult) {
		personValidator.validate(person, bindingResult);
		if (bindingResult.hasErrors()) {
			return "/auth/registration";
		}
		personService.registerPerson(person);
		return "redirect:/news";
	}
}

package maria.anikina.springsecurity.controller;

import lombok.AllArgsConstructor;
import maria.anikina.springsecurity.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/person")
public class PersonController {
	private final PersonService service;

	@GetMapping()
	public String getAllPersons(Model model) {
		model.addAttribute("persons", service.getAllPerson());
		return "all-persons";
	}
}

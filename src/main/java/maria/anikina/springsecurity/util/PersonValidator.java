package maria.anikina.springsecurity.util;

import lombok.AllArgsConstructor;
import maria.anikina.springsecurity.model.PersonEntity;
import maria.anikina.springsecurity.service.PersonService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@AllArgsConstructor
@Component
public class PersonValidator implements Validator {

	private PersonService personService;

	@Override
	public boolean supports(Class<?> clazz) {
		return PersonEntity.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		PersonEntity person = (PersonEntity) o;
		if (personService.existPersonByUsername(person.getUsername())) {
			errors.rejectValue("username", "", "Человек с таким логином уже существует");
		}
	}
}

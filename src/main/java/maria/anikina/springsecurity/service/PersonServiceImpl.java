package maria.anikina.springsecurity.service;

import lombok.AllArgsConstructor;
import maria.anikina.springsecurity.exception.UserAlreadyExistsException;
import maria.anikina.springsecurity.model.PersonEntity;
import maria.anikina.springsecurity.repository.PersonRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {

	private final PersonRepository personRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	@Override
	public Boolean existPersonByUsername(String username) {
		return personRepository.findByUsername(username).isPresent();
	}

	@Override
	@Transactional
	public PersonEntity registerPerson(PersonEntity person) {
		if (personRepository.findByUsername(person.getUsername()).isPresent()) {
			throw new UserAlreadyExistsException("Пользователь с именем " + person.getUsername() + " уже существует");
		}
		String encodedPassword = passwordEncoder.encode(person.getPassword());
		person.setPassword(encodedPassword);
		return personRepository.save(person);
	}

	@Override
	public List<PersonEntity> getAllPerson() {
		return personRepository.findAll();
	}
}

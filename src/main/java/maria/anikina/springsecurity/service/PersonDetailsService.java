package maria.anikina.springsecurity.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import maria.anikina.springsecurity.model.PersonEntity;
import maria.anikina.springsecurity.repository.PersonRepository;
import maria.anikina.springsecurity.security.PersonDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Setter
@Getter
@AllArgsConstructor
public class PersonDetailsService implements UserDetailsService {
	private final PersonRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<PersonEntity> person = repository.findByUsername(username);
		if (person.isEmpty()) {
			throw new UsernameNotFoundException("Person not found");
		}
		return new PersonDetails(person.get());
	}
}

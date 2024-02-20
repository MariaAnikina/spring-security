package maria.anikina.springsecurity.service;

import maria.anikina.springsecurity.model.PersonEntity;

import java.util.List;

public interface PersonService {

	Boolean existPersonByUsername(String username);

	PersonEntity registerPerson(PersonEntity person);

	List<PersonEntity> getAllPerson();
}

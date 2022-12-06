package be.abis.exercise.repository;

import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.model.Person;

import java.io.IOException;
import java.util.List;

public interface PersonRepository {

	List<Person> getAllPersons();
	void addPerson(Person p) throws IOException, PersonAlreadyExistsException;

}

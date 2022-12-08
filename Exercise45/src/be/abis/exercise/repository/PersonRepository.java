package be.abis.exercise.repository;


import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Person;

import java.io.IOException;
import java.util.List;

public interface PersonRepository {

	List<Person> getAllPersons();
	Person findPersonById(int id) throws PersonNotFoundException;
	Person findPerson(String email, String password) throws PersonNotFoundException;
	void addPerson(Person p) throws IOException, PersonAlreadyExistsException;

}

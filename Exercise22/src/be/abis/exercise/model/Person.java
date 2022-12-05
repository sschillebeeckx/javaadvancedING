package be.abis.exercise.model;

import be.abis.exercise.exception.PersonShouldBeAdultException;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Person {

	private static int counter = 0;

	private int personNumber;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private String email;
	private String password;
	private Company company;

	public Person(){
		personNumber = ++counter;
	}

	public Person(String firstName, String lastName) {
		this();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Person(String firstName, String lastName, Company company) {
		this(firstName, lastName);
		this.company = company;
	}
	
	public Person(String firstName, String lastName, LocalDate birthDate, String email,
                  String password, Company company) {
		this(firstName,lastName,company);
		this.birthDate = birthDate;
		this.email = email;
		this.password = password;
	}

	public Person(String firstName, String lastName, LocalDate birthDate, String email,
                  String password) {
		this(firstName,lastName);
		this.birthDate = birthDate;
		this.email = email;
		this.password = password;
	}

	public int getPersonNumber() {
		return personNumber;
	}
	
	public void setPersonNumber(int personNumber) {
		this.personNumber=personNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String fName) {
		firstName = fName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lName) {
		lastName = lName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public static int getNumberOfPersons() {
		return counter;
	}

	@Override
	public String toString() {
		return "Person " + personNumber + ": " + this.firstName + " " + this.lastName;
	}

	public int calculateAge() throws PersonShouldBeAdultException {
		int age = Period.between(birthDate,LocalDate.now()).getYears();
		if (age<18) throw new PersonShouldBeAdultException("You are too young");
		return age;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Person person = (Person) o;
		return email.equals(person.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}
    
}
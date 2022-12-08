package be.abis.exercise.model;

import be.abis.exercise.exception.PersonShouldBeAdultException;
import be.abis.exercise.exception.SalaryTooLowException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.Period;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person {
	static Logger exceptionLogger = LogManager.getLogger("exceptionLogger");

	private static int counter = 0;

	private int personNumber;
	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	@EqualsAndHashCode.Include
	private  String email;
	private String password;
	private Company company;
	private double grossSalary;

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

	@Override
	public String toString() {
		return "Person " + personNumber + ": " + this.firstName + " " + this.lastName;
	}

	public int calculateAge() throws PersonShouldBeAdultException {
		int age = Period.between(birthDate,LocalDate.now()).getYears();
		if (age<18){
			exceptionLogger.error(firstName + " is too young");
			throw new PersonShouldBeAdultException("You are too young");
		}
		return age;
	}

	public double calculateNetSalary() throws SalaryTooLowException {
		double netSalary = 0.0;
		netSalary = grossSalary*(1.0-company.calculateTaxToPay()/100.0);
		if(netSalary<1500) {
			String message = firstName + ", " + netSalary + " is not enough";
			exceptionLogger.error(message);
			throw new SalaryTooLowException(message);
		}
		return netSalary;
	}
    
}
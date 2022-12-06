package be.abis.exercise.test;

import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.FilePersonRepository;
import be.abis.exercise.repository.PersonRepository;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class TestMain {

    public static void main(String[] args) {
        try {
            PersonRepository pr = new FilePersonRepository();
            List<Person> persons= pr.getAllPersons();

            System.out.println("All persons in file");
            persons.forEach(System.out::println);


            //Adding person
            Address a = new Address("Diestsevest","32 bus 4b","3000","Leuven","België","B");
            Company c = new Company("Abis",a);
            Person p1 = new Person("Emily","Mees", LocalDate.of(1990,02,5),"emees@abis.be","abis345",c);
            // checking toString via @Data
            System.out.println(p1.getCompany());
            pr.addPerson(p1);

            //Adding person without company
            Person p2 = new Person("John","Doe", LocalDate.of(1971,01,18),"john.doe@gmail.com","mypassword");
            pr.addPerson(p2);

            System.out.println("\nAll persons in file after adding");
            pr.getAllPersons().forEach(System.out::println);

            System.out.println("\nAdding existing person should throw exception");
            Person p3 = new Person("Sandy","Schillebeeckx", LocalDate.of(1978,04,10),"sschillebeeckx@abis.be","abis123");
            pr.addPerson(p3);


        } catch (IOException | PersonAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }


    }
}

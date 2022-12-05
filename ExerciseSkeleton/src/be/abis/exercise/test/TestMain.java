package be.abis.exercise.test;

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

            Address a = new Address("Diestsevest","32 bus 4b","3000","Leuven","België","B");
            Company c = new Company("Abis",a);
            Person p = new Person("Sandy","Schillebeeckx", LocalDate.of(1978,04,10),"sschillebeeckx@abis.be","abis123",c);

            pr.addPerson(p);

            System.out.println("\nAll persons in file after adding");
            pr.getAllPersons().forEach(System.out::println);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}

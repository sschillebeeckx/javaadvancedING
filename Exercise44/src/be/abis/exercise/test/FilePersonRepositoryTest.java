package be.abis.exercise.test;

import be.abis.exercise.exception.PersonAlreadyExistsException;
import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.FilePersonRepository;
import be.abis.exercise.repository.PersonRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class FilePersonRepositoryTest {

     PersonRepository pr;

    @Before
    public  void setUp() throws Exception{
            pr = FilePersonRepository.getInstance();
    }

    @Test
    public void originalNumberOfPersonsIs9() {
        assertEquals(9, pr.getAllPersons().size());
    }

    @Test
    public void person4IsBart() throws PersonNotFoundException {
        Person p = pr.findPersonById(4);
        assertEquals("Bart", p.getFirstName());
    }

    @Test
    public void person400NotFound() {
        assertThrows(PersonNotFoundException.class, () -> pr.findPersonById(400));
    }

    @Test
    public void personByEmailandPasswordIsSandy() throws PersonNotFoundException {
        Person p = pr.findPerson("sschillebeeckx@abis.be", "somepass1");
        assertEquals("Sandy", p.getFirstName());
    }

    @Test
    public void personByEmailandPasswordNotFound() throws PersonNotFoundException {
        Person p = pr.findPerson("sschillebeeckx@abis.be", "somepass1");
        assertThrows(PersonNotFoundException.class, () -> pr.findPerson("sschillebeeckx@abis.be", "abis123"));
    }

    @Test
    public void addPersonWithCompanyWorks() throws PersonAlreadyExistsException, IOException, PersonNotFoundException {

        Address a = new Address("Diestsevest", "32 bus 4b", "3000", "Leuven", "België", "B");
        Company c = new Company("Abis", a);
        Person p1 = new Person("Emily", "Mees", LocalDate.of(1990, 02, 5), "emees@abis.be", "abis345", c);

        pr.addPerson(p1);

        assertNotNull(pr.findPerson("emees@abis.be", "abis345"));

    }

    @Test
    public void addPersonWithoutCompanyWorks() throws PersonAlreadyExistsException, IOException, PersonNotFoundException {
        Person p2 = new Person("John", "Doe", LocalDate.of(1971, 01, 18), "john.doe@gmail.com", "mypassword");
        pr.addPerson(p2);
        assertNotNull(pr.findPerson("john.doe@gmail.com", "mypassword"));
    }

    @Test
    public void addExistingPersonThrowsException() throws PersonAlreadyExistsException, IOException, PersonNotFoundException {
        Person p3 = new Person("Sam", "Schillebeeckx", LocalDate.of(1980, 10, 04), "sschillebeeckx@abis.be", "abis123");
        assertThrows(PersonAlreadyExistsException.class, () -> pr.addPerson(p3));
    }

    @Test
    public void lostFileThrowsNoSuchFileException() {
        File original = new File("/temp/javacourses/persons.csv");
         File renamed = new File("/temp/javacourses/persons2.csv");
           original.renameTo(renamed);
        assertThrows(NoSuchFileException.class, () -> ((FilePersonRepository) pr).readFile());
         renamed.renameTo(original);
    }

    @Test
    public void readOnlyFileThrowsIOFoundExceptionWhenAddingPerson() {
        File original = new File("/temp/javacourses/persons.csv");
        original.setWritable(false);
        Person p = new Person("An", "Smits", LocalDate.of(1972, 05, 18), "an.smits@gmail.com", "mypassword");
        assertThrows(IOException.class, () -> pr.addPerson(p));
        original.setWritable(true);
    }

       @After
       public void tearDown() throws IOException {
           Path cleanFile= Paths.get("/temp/javacourses/personsClean.csv");
           Path originalLocation= Paths.get("/temp/javacourses/persons.csv");
           Files.copy(cleanFile,originalLocation, StandardCopyOption.REPLACE_EXISTING);
       }
}
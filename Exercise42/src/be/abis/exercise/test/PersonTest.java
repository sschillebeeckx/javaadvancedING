package be.abis.exercise.test;

import be.abis.exercise.exception.PersonShouldBeAdultException;
import be.abis.exercise.model.Person;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class PersonTest {

    @Test
    public void calculateAgeOfPersonIs42() throws PersonShouldBeAdultException {
        Person p = new Person("Jane","Doe", LocalDate.of(1980,04,10),"","");
        assertEquals(42,p.calculateAge());
    }

    @Test
    public void calculateAgeIsOk() throws PersonShouldBeAdultException {
        Person p = new Person("Jane","Doe", LocalDate.of(1980,04,10),"","");
        assertTrue(p.calculateAge()>18);
    }

    @Test
    public void tooYoungPersonThrowsException()  {
        LocalDate birthDateYoungPerson = LocalDate.now().minusYears(17);
        Person p = new Person("Noah","Dupont", birthDateYoungPerson,"","");
        assertThrows(PersonShouldBeAdultException.class,()->p.calculateAge());
    }
}
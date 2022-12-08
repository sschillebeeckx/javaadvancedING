package be.abis.exercise.test;

import be.abis.exercise.exception.PersonShouldBeAdultException;
import be.abis.exercise.exception.SalaryTooLowException;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PersonTest {

    @Mock
    Company mockCompany;


    @Test
    public void calculateAgeOfPersonIs42() throws PersonShouldBeAdultException {
        Person p = new Person("Jane","Doe", LocalDate.of(1980,04,10),"","");
        assertEquals(42,p.calculateAge());
    }

    @Test
    public void tooYoungPersonThrowsException()  {
        LocalDate birthDateYoungPerson = LocalDate.now().minusYears(17);
        Person p = new Person("Noah","Dupont", birthDateYoungPerson,"","");
        assertThrows(PersonShouldBeAdultException.class,()->p.calculateAge());
    }

    @Test
    public void calculateNetSalaryOfBelgianPersonUsingMockCompany() throws SalaryTooLowException {
        Person p = new Person("Jane","Doe", LocalDate.of(1980,04,10),"","",mockCompany);
        p.setGrossSalary(4000);
        when(mockCompany.calculateTaxToPay()).thenReturn(51.0);
        assertEquals(1960,p.calculateNetSalary(), 0.01);
        verify(mockCompany,times(1)).calculateTaxToPay();
    }

    @Test
    public void calculateNetSalaryTooLowThrowsException() {
        Person p = new Person("Jane","Doe", LocalDate.of(1980,04,10),"","",mockCompany);
        p.setGrossSalary(3000);
        when(mockCompany.calculateTaxToPay()).thenReturn(51.0);
        assertThrows(SalaryTooLowException.class,()->p.calculateNetSalary());
    }
}
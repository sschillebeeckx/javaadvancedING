package be.abis.exercise.test;


import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.exception.SalaryTooLowException;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.PersonRepository;
import be.abis.exercise.service.AbisPaymentService;
import be.abis.exercise.service.PaymentService;
import be.abis.exercise.util.DateUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(MockitoJUnitRunner.class)
//@RunWith(PowerMockRunner.class)
//@PrepareForTest({DateUtils.class})
public class PaymentServiceTest {

    @InjectMocks
    static PaymentService ps;

    @Mock
    Person person;

    @Mock
    PersonRepository pr;

    @BeforeClass
    public static void setUp(){
        ps = AbisPaymentService.getInstance();
    }

    @Test
    public void payingSalaryUnder1500shouldThrowException() throws SalaryTooLowException, PersonNotFoundException {
        when(person.calculateNetSalary()).thenThrow(SalaryTooLowException.class);
        when(pr.findPersonById(anyInt())).thenReturn(person);
        assertThrows(SalaryTooLowException.class,()->ps.paySalary(1));
    }

    /* attach the following in the run configuration
     --add-opens java.base/java.lang=ALL-UNNAMED
     --add-opens java.base/java.time.format=ALL-UNNAMED
     --add-opens java.base/java.time=ALL-UNNAMED
     --add-opens java.xml/javax.xml.parsers=ALL-UNNAMED
     --add-opens java.xml/jdk.xml.internal=ALL-UNNAMED

       or take out the static mocking!!!
    */

    @Test
    public void payingSalaryPrintsThings() throws SalaryTooLowException, PersonNotFoundException {
        //mockStatic(DateUtils.class);
        //when(DateUtils.format(any(LocalDate.class))).thenReturn("15/12/2022");
        when(person.calculateNetSalary()).thenReturn(1600.0);
        when(person.getFirstName()).thenReturn("John");
        when(pr.findPersonById(anyInt())).thenReturn(person);
        ps.paySalary(1);
    }



}
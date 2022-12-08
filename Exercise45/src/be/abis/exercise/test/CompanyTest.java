package be.abis.exercise.test;

import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CompanyTest {

    @Mock
    Address a;

    @Test
    public void taxForBelgianCompanyShouldBe51() {
        //arrange
        Company c = new Company("ABIS",a);
        when(a.getCountryCode()).thenReturn("BE");
        //act
        double taxToPay = c.calculateTaxToPay();

        //assert
        assertEquals(51.00,taxToPay,0.0001);

        verify(a,atLeastOnce()).getCountryCode();
    }

}

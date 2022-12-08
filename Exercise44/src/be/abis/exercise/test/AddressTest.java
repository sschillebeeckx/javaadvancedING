package be.abis.exercise.test;

import be.abis.exercise.model.Address;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressTest {

    @Test
    public void belgianZipCodeShouldBeNumeric(){
        //fail();

        //ARRANGE
        Address a = new Address("Diestsevest","32 bus 4b","3000","Leuven","België","B");

        //ACT
        boolean isNumeric = a.isBelgianZipCodeNumeric();

        //ASSERT
        assertTrue(isNumeric);

    }

}
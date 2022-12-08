package be.abis.exercise.service;

import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.exception.SalaryTooLowException;
import be.abis.exercise.model.Person;

public interface PaymentService {

    void paySalary(int personId) throws SalaryTooLowException, PersonNotFoundException;

}

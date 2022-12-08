package be.abis.exercise.service;

import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.exception.SalaryTooLowException;

public interface PaymentService {

    void paySalary(int personId) throws SalaryTooLowException, PersonNotFoundException;

}

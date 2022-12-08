package be.abis.exercise.service;

import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.exception.SalaryTooLowException;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.FilePersonRepository;
import be.abis.exercise.repository.PersonRepository;
import be.abis.exercise.util.DateUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.time.LocalDate;

public class AbisPaymentService implements PaymentService {
    static Logger infoLogger = Logger.getRootLogger();

    private static AbisPaymentService instance;
    private PersonRepository pr;

    private AbisPaymentService() {
        try {
            pr = FilePersonRepository.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static AbisPaymentService getInstance() {
        if (instance == null) instance = new AbisPaymentService();
        return instance;
    }


    public void paySalary(int personId) throws SalaryTooLowException, PersonNotFoundException {
        Person person = pr.findPersonById(personId);
        double salary = person.calculateNetSalary();
        String output = "Paying " + salary + " euros to " + person.getFirstName() + " on " + DateUtils.format(LocalDate.now());
        System.out.println(output);
        infoLogger.info(output);
    }

}

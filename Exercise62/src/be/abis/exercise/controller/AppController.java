package be.abis.exercise.controller;

import be.abis.exercise.exception.PersonNotFoundException;
import be.abis.exercise.gui.Login;
import be.abis.exercise.gui.Welcome;
import be.abis.exercise.model.Person;
import be.abis.exercise.repository.FilePersonRepository;
import be.abis.exercise.repository.PersonRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;

public class AppController {

    static Logger exceptionLogger = LogManager.getLogger("exceptionLogger");
    private static AppController instance;
    private static PersonRepository pr;

    private AppController() throws IOException {
        pr= FilePersonRepository.getInstance();
    }

    public static AppController getInstance() throws IOException {
        if (instance==null) instance=new AppController();
        return instance;
    }


    public void loginAction(Login login, String email, String password) {
        try {
            System.out.println("email="+email+", password="+password);
            Person p = pr.findPerson(email,password);
            System.out.println("Person found=" + p.getFirstName());
            Welcome w = new Welcome(p);
            login.setVisible(false);
            w.setVisible(true);
        } catch (PersonNotFoundException e) {
            login.fillErrorLabel(e.getMessage());
            login.resetFields();
            exceptionLogger.error(e.getMessage());
        }
    }
}

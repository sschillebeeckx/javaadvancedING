package be.abis.exercise.gui;

import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;

import javax.swing.*;

public class Welcome extends JFrame {

    Person loggedInPerson;
    private JPanel welcomePanel;
    private JLabel helloMessage;

    public Welcome(Person p) {
        super();
        loggedInPerson=p;
        initializeFrame();
        initializeWelcomeText();

    }

    private void initializeWelcomeText() {
        Company c = loggedInPerson.getCompany();
        String welcomeText = "Welcome " + loggedInPerson.getFirstName() + " " + loggedInPerson.getLastName() + ((c!=null)?" of " + c.getName():"" )+ ".";
        helloMessage.setText(welcomeText);
    }

    private void initializeFrame() {
        this.setSize(600, 500);
        this.setContentPane(welcomePanel);
        this.setTitle("Welcome");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

}

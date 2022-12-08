package be.abis.exercise.gui;

import be.abis.exercise.listener.LogoutListener;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome extends JFrame {

    Person loggedInPerson;
    private JPanel welcomePanel;
    private JLabel helloMessage;
    private JButton logoutButton;

    public Welcome(Person p) {
        super();
        loggedInPerson=p;
        initializeFrame();
        initializeWelcomeText();
        logoutButton.addActionListener(new LogoutListener());
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

    public class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Login l = new Login();
            Welcome.this.setVisible(false);
            l.setVisible(true);
        }
    }


}

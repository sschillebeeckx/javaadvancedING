package be.abis.exercise.gui;

import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationOK extends JFrame{
    private JPanel panel1;
    private JButton goToLoginButton;
    private JLabel okLabel;
    private Person registeredPerson;

    public RegistrationOK(Person person){
        super();
        registeredPerson=person;
        initializeFrame();
        initializeWelcomeText();
        goToLoginButton.addActionListener(new LoginListener());
    }


    private void initializeFrame() {
        this.setSize(600, 500);
        this.setContentPane(panel1);
        this.setTitle("Registration ok");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void initializeWelcomeText() {
        Company c = registeredPerson.getCompany();
        String welcomeText = "Welcome " + registeredPerson.getFirstName() + ". You can now login." ;
        okLabel.setText(welcomeText);
    }

    public class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Login l = new Login();
            RegistrationOK.this.setVisible(false);
            l.setVisible(true);
        }
    }


}

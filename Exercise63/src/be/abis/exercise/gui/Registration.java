package be.abis.exercise.gui;

import be.abis.exercise.controller.AppController;
import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import be.abis.exercise.util.DateUtils;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.time.LocalDate;

public class Registration extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField birthDateField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField companyNameField;
    private JTextField streetField;
    private JTextField nrField;
    private JTextField zipCodeField;
    private JComboBox countryComboBox;
    private JTextField townField;
    private JLabel errorLabel;

    private AppController controller;

    public Registration()  {
        super();
        initialize();
        try {
            controller= AppController.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        initializeComboBox();
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new CancelListener());

        // call onCancel() when cross is clicked
        //setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

    }

    private void initializeComboBox() {
        countryComboBox.addItem("B");
        countryComboBox.addItem("NL");
    }

    private void initialize() {
        this.setSize(600, 700);
        this.setContentPane(contentPane);
        this.setTitle("Register");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    private void onOK() {
         String firstName = firstNameField.getText();
         String lastName = lastNameField.getText();
         LocalDate birthDate = DateUtils.parse(birthDateField.getText());
         String email = emailField.getText();
         String password = passwordField.getText();
         String companyName = companyNameField.getText();
         String street = streetField.getText();
         String nr = nrField.getText();
         String zipCode = zipCodeField.getText();
         String town = townField.getText();
         String countryCode =(String)countryComboBox.getSelectedItem();
         String country="";
         switch (countryCode){
             case "B" : country="Belgium"; break;
             case "NL": country="Nederland"; break;
         }
         Person p = new Person(firstName,lastName,birthDate,email,password,new Company(companyName, new Address(street,nr,zipCode,town,country,countryCode)));
         controller.registerAction(this,p);
    }

    private void onCancel() {
         this.dispose();
         this.setVisible(false);
         System.exit(0);
    }


    public void fillErrorLabel(String message) {
        errorLabel.setText(message);
    }

    public class CancelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Login l = new Login();
            Registration.this.setVisible(false);
            l.setVisible(true);
        }
    }

}

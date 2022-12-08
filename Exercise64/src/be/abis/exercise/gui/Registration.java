package be.abis.exercise.gui;

import be.abis.exercise.controller.AppController;
import be.abis.exercise.model.Address;
import be.abis.exercise.model.Company;
import be.abis.exercise.model.Person;
import be.abis.exercise.util.DateUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

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
    private JLabel nameErrorLabel;
    private JLabel birthdateErrorLabel;
    private JLabel emailErrorLabel;
    private JLabel passwordErrorLabel;

    private AppController controller;
    private static final Pattern DECIMAL_DIGIT = Pattern.compile("\\p{Nd}");

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
        this.setSize(1100, 700);
        this.setContentPane(contentPane);
        this.setTitle("Register");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    private void onOK() {

         boolean allOk=true;
         String firstName = firstNameField.getText();
         String lastName = lastNameField.getText();
         if (lastName.isEmpty() || lastName.length()>45){
             nameErrorLabel.setText("Last name is required.");
             allOk=false;
         } else if (lastName.length()>45){
            nameErrorLabel.setText("Last name can be max 45 characters.");
             allOk=false;
         } else {
             nameErrorLabel.setText("");
             allOk=true;
         }

        LocalDate birthDate=null;
         try {
             birthDate = DateUtils.parse(birthDateField.getText());
             birthdateErrorLabel.setText("");
             allOk=true;
         } catch (DateTimeParseException pe){
             birthdateErrorLabel.setText("Please enter birthDate in format d/M/yyyy.");
             allOk=false;
         }

         String email = emailField.getText();
         if (email.isEmpty()){
             emailErrorLabel.setText("Email is required.");
             allOk=false;
         } else if (!email.matches(".+@.+\\.[a-z]+")) {
             emailErrorLabel.setText("Email format not correct.");
             allOk=false;
         } else {
             emailErrorLabel.setText("");
             allOk=true;
         }

         String password = passwordField.getText();
         if (password.isEmpty()){
             passwordErrorLabel.setText("Password is required.");
             allOk=false;
         } else if (password.length()<5){
             passwordErrorLabel.setText("Password should be minimum 5 characters.");
             allOk=false;
         } else if (!containsDigit(password)){
             passwordErrorLabel.setText("Password should contain minimally 1 digit.");
             allOk=false;
         } else {
             passwordErrorLabel.setText("");
             allOk=true;
         }

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

        if (allOk){
            Person p = new Person(firstName,lastName,birthDate,email,password,new Company(companyName, new Address(street,nr,zipCode,town,country,countryCode)));
            controller.registerAction(this,p);
        }

    }

    private boolean containsDigit(final String str) {
        return DECIMAL_DIGIT.matcher(str).find();
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

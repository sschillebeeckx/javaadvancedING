package be.abis.exercise.gui;

import be.abis.exercise.controller.AppController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Login extends JFrame {
    private AppController controller;
    private JPanel loginPanel;
    private JTextField emailField;
    private JButton loginButton;
    private JLabel emailLabel;
    private JPasswordField passwordField;
    private JLabel passwordLabel;
    private JLabel errorLabel;
    private JButton registerButton;

    public Login() throws HeadlessException {
        super();
        initialize();
        try {
            controller=AppController.getInstance();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        loginButton.addActionListener(new LoginButtonListener());
        registerButton.addActionListener(new RegisterButtonListener());
    }

    private void initialize() {
        this.setSize(600, 300);
        this.setContentPane(loginPanel);
        this.setTitle("Login");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }


    public void fillErrorLabel(String message) {
         errorLabel.setText(message);
    }

    public void resetFields() {
        passwordField.setText("");
        emailField.setText("");
    }

    private class LoginButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("doing things");
            String email = emailField.getText();
            String password = passwordField.getText();
            controller.loginAction(Login.this, email, password);
        }
    }

    private class RegisterButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Login.this.setVisible(false);
            Registration registration = new Registration();
            registration.setVisible(true);
        }
    }

}

package com.debi.socialmediaapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

// LoginController.java
public class ModernLoginController {
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private CheckBox rememberMe;

    @FXML
    private void handleLogin() {
        // Implement login logic
        String email = emailField.getText();
        String password = passwordField.getText();
        // Process login...
    }

    @FXML
    private void handleForgotPassword() {
        // Implement forgot password logic
    }
}

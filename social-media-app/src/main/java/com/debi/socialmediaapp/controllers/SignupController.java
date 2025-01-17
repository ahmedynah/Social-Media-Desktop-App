package com.debi.socialmediaapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.Getter;
import lombok.Setter;


public class SignupController {
    @Setter
    @Getter
    AuthContainerController authContainerController;

    @FXML
    private TextField signupName;
    @FXML
    private TextField signupEmail;
    @FXML
    private PasswordField signupPassword;
    @FXML
    private PasswordField confirmPassword;

    @FXML
    private void handleSignup() {
        // Implement signup logic
        String name = signupName.getText();
        String email = signupEmail.getText();
        String password = signupPassword.getText();
        // Process signup...
    }

    @FXML
    private void handleBackToLogin() {

//         Get parent controller and trigger form switch
        Node node = signupName.getScene().getRoot();
        authContainerController.toggleAuthForm();
    }
}

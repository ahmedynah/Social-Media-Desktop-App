package com.debi.socialmediaapp.controllers;

import com.debi.socialmediaapp.repositories.UserRepository;
import com.debi.socialmediaapp.models.User;
import com.debi.socialmediaapp.utils.GeneralUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


import javafx.scene.control.Alert;
import com.debi.socialmediaapp.utils.PasswordUtil;

public class RegisterController {

    UserRepository userRepository = new UserRepository();

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private TextField confirmPassword;

    @FXML
    private void submitRegistrationForm() {
        if (!validateFields()) {
            return;
        }

        User user = new User(firstName.getText(),
                lastName.getText(),
                email.getText(),
                PasswordUtil.hashPassword(password.getText())
        );
        userRepository.saveUser(user);
        GeneralUtil.showSuccessAlert("your account has bees registered successfully");
        redirectToLoginPage();
    }


    public boolean validateFields() {
        if (firstName.getText().isEmpty()) {
            GeneralUtil.showErrorAlert("First name is required.");
            return false;
        }

        if (lastName.getText().isEmpty()) {
            GeneralUtil.showErrorAlert("Last name is required.");
            return false;
        }

        if (email.getText().isEmpty()) {
            GeneralUtil.showErrorAlert("Email is required.");
            return false;
        }

        if (!isValidEmail(email.getText())) {
            GeneralUtil.showErrorAlert("Invalid email format.");
            return false;
        }

        if (password.getText().isEmpty()) {
            GeneralUtil.showErrorAlert("Password is required.");
            return false;
        }

        if (confirmPassword.getText().isEmpty()) {
            GeneralUtil.showErrorAlert("Confirm password is required.");
            return false;
        }

        if (!password.getText().equals(confirmPassword.getText())) {
            GeneralUtil.showErrorAlert("Passwords do not match.");
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
        return email.matches(emailRegex);
    }


    @FXML
    private void redirectToLoginPage() {
        GeneralUtil.redirectToView("login-view", email);
    }
}
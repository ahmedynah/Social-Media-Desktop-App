package com.debi.socialmediaapp.controllers;

import com.debi.socialmediaapp.repositories.UserRepository;
import com.debi.socialmediaapp.models.User;
import com.debi.socialmediaapp.utils.PasswordUtil;
import com.debi.socialmediaapp.utils.GeneralUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;


public class LoginController {

    UserRepository userRepository = new UserRepository();
    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private void submitLoginForm() {
        if (!validateFields())
            return;
        User user = userRepository.getUserByEmail(email.getText());
        if (!(user != null && PasswordUtil.verifyPassword(password.getText(), user.getPassword()))) {
            GeneralUtil.showErrorAlert("invalid credentials");
            return;
        }
        GeneralUtil.redirectToView("profile-view", email);
    }


    public boolean validateFields() {
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
        return true;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$";
        return email.matches(emailRegex);
    }

    @FXML
    private void redirectToRegisterPage() {
        GeneralUtil.redirectToView("register-view", email);
    }
}
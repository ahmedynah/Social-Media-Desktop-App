package com.debi.socialmediaapp.controllers;

import com.debi.socialmediaapp.repositories.UserRepository;
import com.debi.socialmediaapp.utils.HibernateUtil;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

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
    private void redirectToLoginPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/debi/socialmediaapp/login-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) firstName.getScene().getWindow();

            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
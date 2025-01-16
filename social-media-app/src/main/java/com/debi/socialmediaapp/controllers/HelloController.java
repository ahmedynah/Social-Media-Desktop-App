package com.debi.socialmediaapp.controllers;

import com.debi.socialmediaapp.models.User;
import com.debi.socialmediaapp.repositories.UserRepository;
import com.debi.socialmediaapp.utils.HibernateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;

public class HelloController {

    UserRepository userRepository = new UserRepository();
    @FXML
    private Label welcomeText;

    @FXML
    private TextField txField;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        JOptionPane.showMessageDialog(null, txField.getText());

        boolean isConnected = HibernateUtil.testConnection();
    }
}
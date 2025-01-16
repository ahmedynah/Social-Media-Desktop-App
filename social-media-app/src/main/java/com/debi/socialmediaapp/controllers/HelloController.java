package com.debi.socialmediaapp.controllers;

import com.debi.socialmediaapp.utils.HibernateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;

public class HelloController {

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
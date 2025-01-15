package com.debi.socialmediaapp.controllers;

import com.debi.socialmediaapp.repositories.UserRepository;
import com.debi.socialmediaapp.utils.HibernateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.swing.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    UserRepository userRepository = new UserRepository();
    @FXML
    private TextField txField;

    @FXML
    protected void onHelloButtonClick() {
        JOptionPane.showMessageDialog(null, txField.getText());

        boolean isConnected = HibernateUtil.testConnection();
        System.out.println("Database connection successful: " + isConnected);
//        System.out.println(userRepository.getUserById(1L));
    }

    @FXML
    private void redirectToRegisterPage() {
        try {
            // Load the register-view.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/debi/socialmediaapp/register-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) txField.getScene().getWindow();

            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
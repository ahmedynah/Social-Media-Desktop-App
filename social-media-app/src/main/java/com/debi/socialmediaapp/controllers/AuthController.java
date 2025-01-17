package com.debi.socialmediaapp.controllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class AuthController {
    @FXML private VBox loginForm;
    @FXML private VBox signupForm;
    @FXML private VBox promoPanel;

    @FXML
    public void initialize() {
        // Initialize view states
        loginForm.setVisible(true);
        signupForm.setVisible(false);
    }

    @FXML
    private void handleSignupClick() {
        animateFormTransition(loginForm, signupForm);
    }

    @FXML
    private void showLoginForm() {
        animateFormTransition(signupForm, loginForm);
    }

    private void animateFormTransition(VBox fromForm, VBox toForm) {
        // Fade out current form
        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), fromForm);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        // Fade in new form
        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), toForm);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        fadeOut.setOnFinished(e -> {
            fromForm.setVisible(false);
            toForm.setVisible(true);
            fadeIn.play();
        });

        fadeOut.play();
    }

    @FXML
    private void handleLogin() {
        // Add login logic here
    }

    @FXML
    private void handleSignup() {
        // Add signup logic here
    }

    @FXML
    private void handleForgotPassword() {
        // Add forgot password logic here
    }
}
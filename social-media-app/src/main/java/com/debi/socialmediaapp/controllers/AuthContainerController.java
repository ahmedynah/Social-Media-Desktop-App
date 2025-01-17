package com.debi.socialmediaapp.controllers;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;

public class AuthContainerController {
    @FXML private VBox formPanel;
    @FXML private VBox promoPanel;

    private Node loginForm;
    private Node signupForm;
    private boolean isLoginView = true;

    @FXML
    public void initialize() {
        try {
            // Load both forms
            loginForm = FXMLLoader.load(getClass().getResource("login-form.fxml"));
            signupForm = FXMLLoader.load(getClass().getResource("signup-form.fxml"));


            // Set initial alignment and position constraints for both forms
            VBox.setVgrow(loginForm, Priority.ALWAYS);
            VBox.setVgrow(signupForm, Priority.ALWAYS);

            // Ensure forms fill the width of the container
            ((VBox) loginForm).setFillWidth(true);
            ((VBox) signupForm).setFillWidth(true);

            // Set alignment for both forms
            ((VBox) loginForm).setAlignment(Pos.CENTER);
            ((VBox) signupForm).setAlignment(Pos.CENTER);

            // Show login form initially
            formPanel.getChildren().setAll(loginForm);
            formPanel.setAlignment(Pos.CENTER);

            // Store controller reference for child forms
            if (formPanel.getScene() != null) {
                formPanel.getScene().getRoot().setUserData(this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void toggleAuthForm() {
        if (formPanel.getChildren().isEmpty()) {
            return;
        }

        Node currentForm = formPanel.getChildren().getFirst();
        Node nextForm = isLoginView ? signupForm : loginForm;

        // Ensure the next form starts fully transparent and is properly positioned
        nextForm.setOpacity(0.0);

        // Ensure both forms have the same layout bounds
        nextForm.layoutXProperty().bind(currentForm.layoutXProperty());
        nextForm.layoutYProperty().bind(currentForm.layoutYProperty());

        // Create fade out transition for current form
        FadeTransition fadeOut = new FadeTransition(Duration.millis(300), currentForm);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        // Create fade in transition for next form
        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), nextForm);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);

        fadeOut.setOnFinished(e -> {
            formPanel.getChildren().clear();

            // Ensure the form panel's alignment is maintained
            formPanel.setAlignment(Pos.CENTER);

            // Add the new form
            formPanel.getChildren().add(nextForm);

            // Ensure the new form is centered
            VBox.setVgrow(nextForm, Priority.ALWAYS);
            if (nextForm instanceof VBox) {
                ((VBox) nextForm).setAlignment(Pos.CENTER);
            }

            fadeIn.play();
        });

        // Update promo panel before animation starts
        updatePromoPanel(isLoginView);
        fadeOut.play();
        isLoginView = !isLoginView;
    }
    private void updatePromoPanel(boolean showingLogin) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(150), promoPanel);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.8);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(150), promoPanel);
        fadeIn.setFromValue(0.8);
        fadeIn.setToValue(1.0);

        fadeOut.setOnFinished(e -> {
            if (showingLogin) {
                ((Label) promoPanel.getChildren().get(0)).setText("Already have an account?");
                ((Label) promoPanel.getChildren().get(1)).setText("Sign in to continue your journey!");
                ((Button) promoPanel.getChildren().get(2)).setText("SIGN IN");
            } else {
                ((Label) promoPanel.getChildren().get(0)).setText("New Here?");
                ((Label) promoPanel.getChildren().get(1)).setText("Sign up and discover great opportunities!");
                ((Button) promoPanel.getChildren().get(2)).setText("SIGN UP");
            }
            fadeIn.play();
        });

        fadeOut.play();
    }
}
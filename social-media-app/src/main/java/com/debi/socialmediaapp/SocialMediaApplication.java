package com.debi.socialmediaapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SocialMediaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Image icon = new Image(Objects.requireNonNull(SocialMediaApplication.class.getResourceAsStream("/imgs/social-contact-svgrepo-com.png")));

        FXMLLoader fxmlLoader = new FXMLLoader(SocialMediaApplication.class.getResource("/fxml/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        stage.setTitle("Social Media App");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
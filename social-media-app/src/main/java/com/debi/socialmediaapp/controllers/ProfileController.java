package com.debi.socialmediaapp.controllers;

import com.debi.socialmediaapp.DTOs.LoggedInUser;
import com.debi.socialmediaapp.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import com.debi.socialmediaapp.repositories.ProfileRepository;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ProfileController {

    @FXML
    private ImageView profilePicture;

    @FXML
    private Label bioText;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Button editProfileButton;

    ProfileRepository repository = new ProfileRepository();

    @FXML
    public void initialize() {
        // Get the logged-in user from the singleton
        User loggedInUser = LoggedInUser.getInstance().getUser();
        if (loggedInUser != null) {
            // Set the first name, last name, and email
            firstNameLabel.setText("First Name: " + loggedInUser.getFirstName());
            lastNameLabel.setText("Last Name: " + loggedInUser.getLastName());
            emailLabel.setText("Email: " + loggedInUser.getEmail());

            long userId = loggedInUser.getId();

            // Fetch bio and profile picture
            String userBio = repository.getBioByUserId(userId);
            byte[] imageBytes = repository.getProfilePictureByUserId(userId);

            // Set the bio text
            bioText.setText(userBio != null ? userBio : "No bio available");

            // Set profile picture
            if (imageBytes != null) {
                Image image = new Image(new ByteArrayInputStream(imageBytes));
                profilePicture.setImage(image);
            } else {
                // Optionally set a default image if the profile picture is missing
                profilePicture.setImage(new Image("https://placehold.co/150"));
            }
        }
    }

    @FXML
    private void onEditProfileButtonClick() {
        try {
            // Load the FXML for the Edit Profile window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/debi/socialmediaapp/EditProfile.fxml"));
            Scene scene = new Scene(loader.load());

            // Set up the stage
            Stage stage = new Stage();
            stage.setTitle("Edit Profile");
            stage.initModality(Modality.APPLICATION_MODAL); // Block interaction with other windows
            stage.setScene(scene);

            // Pass data to the Edit Profile controller if needed
            EditProfileController controller = loader.getController();
            controller.setProfileData(bioText.getText()); // Pass current bio to edit screen

            stage.showAndWait(); // Wait for the edit screen to close before proceeding
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

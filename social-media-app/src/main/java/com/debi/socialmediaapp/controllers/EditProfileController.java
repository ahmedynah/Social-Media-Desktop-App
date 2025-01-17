package com.debi.socialmediaapp.controllers;

import com.debi.socialmediaapp.DTOs.LoggedInUser;
import com.debi.socialmediaapp.models.User;
import com.debi.socialmediaapp.repositories.UserRepository;
import com.debi.socialmediaapp.utils.PasswordUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.debi.socialmediaapp.repositories.ProfileRepository;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class EditProfileController {

    @FXML
    private TextArea bioTextArea;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ImageView profileImageView;

    private final long userId = LoggedInUser.getInstance().getUser().getId();

    private final ProfileRepository repository = new ProfileRepository();
    private final UserRepository userRepository = new UserRepository();

    /**
     * Populates the profile fields with the current user data.
     */
    public void setProfileData() {
        User currentUser = LoggedInUser.getInstance().getUser();
        String currentBio = repository.getBioByUserId(currentUser.getId());
        byte[] profilePictureBytes = repository.getProfilePictureByUserId(currentUser.getId());

        // Populate fields
        bioTextArea.setText(currentBio);
        firstNameField.setText(currentUser.getFirstName());
        lastNameField.setText(currentUser.getLastName());
        emailField.setText(currentUser.getEmail());
        passwordField.setText(""); // Do not populate the password for security reasons

        // Optionally populate the profile image if available
        if (profilePictureBytes != null) {
            Image profileImage = new Image(new ByteArrayInputStream(profilePictureBytes));
            profileImageView.setImage(profileImage);
        }
    }


    @FXML
    private void onSaveButtonClick() {
        // Update bio
        String updatedBio = bioTextArea.getText();
        repository.updateBioByUserId(userId, updatedBio);

        // Update other fields
        String updatedFirstName = firstNameField.getText();
        String updatedLastName = lastNameField.getText();
        String updatedEmail = emailField.getText();
        String updatedPassword = passwordField.getText();

        if (!updatedFirstName.isEmpty()) {
            userRepository.updateFirstNameByUserId(userId, updatedFirstName);
        }

        if (!updatedLastName.isEmpty()) {
            userRepository.updateLastNameByUserId(userId, updatedLastName);
        }

        if (!updatedEmail.isEmpty() && updatedEmail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            userRepository.updateEmailByUserId(userId, updatedEmail);
        } else if (!updatedEmail.isEmpty()) {
            System.out.println("Invalid email format.");
            return;
        }

        if (!updatedPassword.isEmpty()) {
            String hashedUpdatedPassword = PasswordUtil.hashPassword(updatedPassword);
            userRepository.updatePasswordByUserId(userId, hashedUpdatedPassword);
        }

        // Show confirmation message
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Profile Update");
        alert.setHeaderText(null);
        alert.setContentText("Profile updated successfully!");
        alert.showAndWait();

        // Close the window
        Stage stage = (Stage) bioTextArea.getScene().getWindow();
        stage.close();
    }


    @FXML
    private void onUploadImageButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File selectedFile = fileChooser.showOpenDialog(profileImageView.getScene().getWindow());
        if (selectedFile != null) {
            try {
                // Convert the selected file to a byte array
                byte[] imageBytes = Files.readAllBytes(selectedFile.toPath());

                // Save the image to the database
                repository.saveProfilePictureByUserId(userId, imageBytes);

                // Update the ImageView with the new image
                Image image = new Image(new ByteArrayInputStream(imageBytes));
                profileImageView.setImage(image);
            } catch (IOException e) {
                e.printStackTrace(); // Handle file reading error
            }
        }
    }

}

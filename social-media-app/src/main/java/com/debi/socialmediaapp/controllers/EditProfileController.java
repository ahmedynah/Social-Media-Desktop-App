package com.debi.socialmediaapp.controllers;

import com.debi.socialmediaapp.DTOs.LoggedInUser;
import com.debi.socialmediaapp.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import com.debi.socialmediaapp.repositories.ProfileRepository;

public class EditProfileController {

    @FXML
    private TextArea bioTextArea;

    private long userId = LoggedInUser.getInstance().getUser().getId();

    ProfileRepository repository = new ProfileRepository();

    public void setProfileData(String currentBio) {
        bioTextArea.setText(currentBio); // Populate the TextArea with the current bio
    }

    @FXML
    private void onSaveButtonClick() {
        String updatedBio = bioTextArea.getText();
        repository.updateBioByUserId(userId, updatedBio);

        // Close the window
        Stage stage = (Stage) bioTextArea.getScene().getWindow();
        stage.close();
    }
}

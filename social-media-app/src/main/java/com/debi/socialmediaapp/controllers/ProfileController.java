package com.debi.socialmediaapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import com.debi.socialmediaapp.repositories.ProfileRepository;
import java.io.ByteArrayInputStream;

public class ProfileController {

    @FXML
    private ImageView profilePicture;

    @FXML
    private Label bioText;
    ProfileRepository repository = new ProfileRepository();
    // This method initializes the profile data
    @FXML
    public void initialize() {
        //TODO: add a mechanism to store the logged in user
        long userId = 1L;
        String userBio = repository.getBioByUserId(userId);
        byte[] imageBytes = repository.getProfilePictureByUserId(userId);

        // Set the bio text
        bioText.setText(userBio != null ? userBio : "No bio available");

        // Convert the byte[] to an Image and set it in the ImageView
        if (imageBytes != null) {
            Image image = new Image(new ByteArrayInputStream(imageBytes));
            profilePicture.setImage(image);
        } else {
            // Optionally set a default image if the profile picture is missing
            profilePicture.setImage(new Image("https://dummyimage.com/150x150/cccccc/000000"));
        }
    }


}

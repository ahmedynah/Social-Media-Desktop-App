package com.debi.socialmediaapp.controllers;

import com.debi.socialmediaapp.DTOs.LoggedInUser;
import com.debi.socialmediaapp.models.Post;
import com.debi.socialmediaapp.models.PostImage;
import com.debi.socialmediaapp.models.User;
import com.debi.socialmediaapp.repositories.PostImagesRepository;
import com.debi.socialmediaapp.repositories.PostRepository;
import com.debi.socialmediaapp.repositories.UserRepository;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.File;

public class StatusViewController {

    @FXML
    public JFXButton profileButton;

    @FXML
    private TextField statusInput;

    @FXML
    private JFXButton uploadImageButton;

    private ImageView uploadedImageView = new ImageView();

    @FXML
    private JFXButton submitButton;

    private PostRepository postRepository = new PostRepository();

    private PostImagesRepository postImagesRepository = new PostImagesRepository();

    @FXML
    private void handleUploadImage(ActionEvent event) {
        // Open a FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        // Get the selected file
        File selectedFile = fileChooser.showOpenDialog(uploadImageButton.getScene().getWindow());
        if (selectedFile != null) {
            // Load the image and display it in the ImageView
            Image image = new Image(selectedFile.toURI().toString());
            uploadedImageView.setImage(image);
        }
    }

    @FXML
    private void handleSubmitPost(ActionEvent event) {
        // Handle post submission logic here
        String postContent = statusInput.getText();
        Image selectedImage = uploadedImageView.getImage();

        if (postContent.isEmpty() && selectedImage == null) {
            System.out.println("Post content or image must be provided.");
        } else {
            System.out.println("Post submitted: " + postContent);

            // Convert the selected image to byte[]
            if (selectedImage != null) {
                byte[] imageBytes = convertImageToBytes(selectedImage);
                System.out.println("Image converted to byte array of size: " + imageBytes.length);
                // You can now save or process the imageBytes
                PostImage postImage = new PostImage();
                postImage.setImage(imageBytes);

                UserRepository userRepository = new UserRepository();

                User user = LoggedInUser.getInstance().getUser();
//                User user = new User();
//                user.setEmail("axy@gg.com");
//                user.setFirstName("7amasa");
//                user.setLastName("7amasa Jr");
//                user.setPassword("123456");
                userRepository.saveUser(user);

                Post post = new Post();
                post.setUser(user);
                post.setText(postContent);
                post.addPostImage(postImage);
                postRepository.savePost(post);
                postImagesRepository.savePostImageWithPost(postImage, post);

                JOptionPane.showMessageDialog(null, "Post Created!");
            }
        }
    }

    private byte[] convertImageToBytes(Image image) {
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        PixelReader pixelReader = image.getPixelReader();
        if (pixelReader == null) {
            throw new IllegalArgumentException("PixelReader is null for the provided image.");
        }

        // Create a buffer to hold the raw pixel data
        byte[] buffer = new byte[width * height * 4]; // Assuming ARGB (4 bytes per pixel)
        pixelReader.getPixels(0, 0, width, height, PixelFormat.getByteBgraInstance(), buffer, 0, width * 4);

        return buffer;
    }
}

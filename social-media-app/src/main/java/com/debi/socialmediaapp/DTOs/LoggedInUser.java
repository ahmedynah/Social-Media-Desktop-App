package com.debi.socialmediaapp.DTOs;
import com.debi.socialmediaapp.models.User;

public class LoggedInUser {
    // Static instance for the singleton
    private static LoggedInUser instance;

    // User object representing the logged-in user
    private User user;

    // Private constructor to prevent instantiation
    private LoggedInUser() {
    }

    // Public method to access the single instance
    public static LoggedInUser getInstance() {
        if (instance == null) {
            instance = new LoggedInUser();
        }
        return instance;
    }

    // Getter for the user
    public User getUser() {
        return user;
    }

    // Setter for the user
    public void setUser(User user) {
        this.user = user;
    }

    // Method to clear the logged-in user (e.g., during logout)
    public void clearUser() {
        user = null;
    }
}


package com.debi.socialmediaapp.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

/**
 * Utility class for configuring and managing Hibernate SessionFactory.
 * Provides methods to obtain the SessionFactory, test database connections, and shut down Hibernate resources.
 *
 * @author ahmedynah
 * @version 1.0.0
 * @since 1.0.0
 */

public class GeneralUtil {
    public static void redirectToView(String viewName, Node element) {
        try {
            FXMLLoader loader = new FXMLLoader(GeneralUtil.class.getResource("/com/debi/socialmediaapp/" + viewName + ".fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) element.getScene().getWindow();

            stage.setScene(new Scene(root));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}

package com.debi.socialmediaapp.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ProfileHelper {

    /**
     * Fetches a default image from a remote URL and returns it as a byte array.
     *
     * @return byte[] representing the image data
     */
    public static byte[] getDefaultImageBytes() {
        try {
            URL url = new URL("https://placehold.co/150");
            try (InputStream inputStream = url.openStream();
                 ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }
                return byteArrayOutputStream.toByteArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null if an error occurs
        }
    }

    /**
     * Loads an image from a local file path and returns it as a byte array.
     *
     * @param filePath The local file path of the image
     * @return byte[] representing the image data
     */
    public static byte[] getLocalImageBytes(String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Return null if an error occurs
        }
    }
}

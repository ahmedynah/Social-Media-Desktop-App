package com.debi.socialmediaapp.utils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
/**
 * Utility class for configuring and managing Hibernate SessionFactory.
 * Provides methods to obtain the SessionFactory, test database connections, and shut down Hibernate resources.
 *
 * @author ahmedynah
 * @version 1.0.0
 * @since 1.0.0
 */

public class PasswordUtil {
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String hashPassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        return passwordEncoder.matches(plainPassword, hashedPassword);
    }
}

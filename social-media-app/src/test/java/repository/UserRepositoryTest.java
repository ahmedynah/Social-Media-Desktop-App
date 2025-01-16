package repository;

import com.debi.socialmediaapp.models.User;
import com.debi.socialmediaapp.repositories.UserRepository;

import java.util.List;

/**
 * A demonstration class for performing CRUD operations on the User entity
 * using the UserRepository. This class simulates the process of creating,
 * retrieving, updating, and deleting users.
 * <p>
 * It covers the creation of a user, retrieval of users by ID, listing all users,
 * and deleting a user by ID.
 * </p>
 *
 * @author ahmedynah
 * @version 1.0.0
 * @since 1.0.0
 */
public class UserRepositoryTest {

    /**
     * Main method that demonstrates CRUD operations for the User entity.
     * This method performs the following steps:
     * 1. Creates and saves a new user.
     * 2. Retrieves and displays a user by ID.
     * 3. Retrieves and displays all users.
     * 4. Deletes a user by ID.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // 1. Creating a new User
        User newUser = new User();
        newUser.setFirstName("John"); // Set first name for the new user
        newUser.setLastName("Doe"); // Set last name for the new user
        newUser.setEmail("john.doe@example.com"); // Set email for the new user
        newUser.setPassword("securepassword"); // Set password for the new user

        // Save or update the user
        UserRepository userRepository = new UserRepository();
        userRepository.saveUser(newUser);  // Save the user to the database

        // 2. Get a user by ID
        Long userId = 1L;  // Define a user ID (assumed to be 1L here)
        User user = userRepository.getUserById(userId);  // Fetch user by ID from the database
        System.out.println("User details: " + user);  // Print the user details to the console

        // 3. Get all users
        List<User> users = userRepository.getAllUsers();  // Retrieve all users from the database
        users.forEach(System.out::println);  // Print each user in the list

        // 4. Delete user by ID
        userRepository.deleteUser(userId);  // Delete the user by their ID from the database
    }
}

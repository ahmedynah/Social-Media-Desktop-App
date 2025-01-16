package repository;

import com.debi.socialmediaapp.models.Post;
import com.debi.socialmediaapp.models.PostImage;
import com.debi.socialmediaapp.models.User;
import com.debi.socialmediaapp.repositories.PostImagesRepository;
import com.debi.socialmediaapp.repositories.PostRepository;
import com.debi.socialmediaapp.repositories.UserRepository;

/**
 * This class demonstrates the usage of repositories to perform CRUD operations
 * on User, Post, and PostImage entities.
 * <p>
 * It includes creating and saving a User, associating a Post with the User, and
 * linking a PostImage to the Post. It also demonstrates retrieval and deletion
 * of entities.
 *
 * @author Ahmed Hany
 * @version 1.0.0
 * @since 1.0.0
 */
public class PostImageRepositoryTest {

    /**
     * The entry point of the program, demonstrating CRUD operations.
     *
     * @param args Command-line arguments (not used in this example).
     */
    public static void main(String[] args) {
        // Instantiate repository objects
        PostImagesRepository postImagesRepository = new PostImagesRepository();
        PostRepository postRepository = new PostRepository();
        UserRepository userRepository = new UserRepository();

        // Step 1: Create and save a User
        User user = new User();
        user.setEmail("sampleUser@xyz.com"); // Set user details
        userRepository.saveUser(user); // Save the User entity in the database

        // Step 2: Create and save a Post associated with the User
        Post post = new Post();
        post.setText("Sample post for images"); // Set post content
        post.setUser(user); // Associate the Post with the previously created User
        postRepository.savePost(post); // Save the Post entity in the database

        // Step 3: Create and save a PostImage associated with the Post
        PostImage postImage = new PostImage();
        postImage.setImage("Sample image data".getBytes()); // Set image data (as byte array)
        postImagesRepository.savePostImageWithPost(postImage, post); // Save PostImage and associate it with the Post

        // Step 4: Retrieve and display the PostImage by its ID
        PostImage retrievedImage = postImagesRepository.getPostImageById(postImage.getId());
        if (retrievedImage != null) {
            System.out.println("Retrieved PostImage with ID: " + retrievedImage.getId());
        } else {
            System.out.println("PostImage not found.");
        }

        // Step 5: Clean up by deleting the PostImage, Post, and User entities
        postImagesRepository.deletePostImage(postImage.getId()); // Delete the PostImage by ID
        postRepository.deletePost(post.getId()); // Delete the Post by ID
        userRepository.deleteUser(user.getId()); // Delete the User by ID
    }
}

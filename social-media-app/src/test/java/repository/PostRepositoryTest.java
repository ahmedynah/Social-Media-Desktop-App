package repository;

import com.debi.socialmediaapp.models.Post;
import com.debi.socialmediaapp.models.PostImage;
import com.debi.socialmediaapp.models.User;
import com.debi.socialmediaapp.repositories.PostRepository;
import com.debi.socialmediaapp.repositories.UserRepository;

import java.util.Arrays;

/**
 * A demonstration class for performing CRUD operations on the Post entity
 * using the PostRepository. This class simulates the process of creating
 * a user, creating a post, adding images to the post, retrieving posts,
 * and deleting a post.
 * <p>
 * The operations also showcase the cascading effects where deleting a post
 * will also delete its associated images.
 * </p>
 *
 * @author ahmedynah
 * @version 1.0.0
 * @since 1.0.0
 */
public class PostRepositoryTest {

    /**
     * Main method that demonstrates CRUD operations for the Post entity.
     * This method performs the following steps:
     * 1. Creates and saves a new user.
     * 2. Creates and saves a new post for the user.
     * 3. Adds images to the post.
     * 4. Fetches and displays all posts.
     * 5. Fetches a specific post and its associated images.
     * 6. Deletes the post (and images).
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Initialize repositories for interacting with Post and User entities
        PostRepository postRepository = new PostRepository();
        UserRepository userRepository = new UserRepository();

        // 1. Create and save a new user
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@example.com");
        user.setPassword("password123");
        userRepository.saveUser(user);  // Save the user in the repository

        // 2. Create and save a new post for the user
        Post post = new Post();
        post.setText("This is my first post!");
        post.setUser(user);  // Associate the post with the created user

        // 3. Add images to the post
        PostImage image1 = new PostImage();
        image1.setImage("Image data 1".getBytes()); // Example image data (replace with actual image bytes)
        image1.setPost(post);  // Associate the image with the post

        PostImage image2 = new PostImage();
        image2.setImage("Image data 2".getBytes()); // Example image data (replace with actual image bytes)
        image2.setPost(post);  // Associate the second image with the post

        // Set the list of images for the post
        post.setImages(Arrays.asList(image1, image2));
        postRepository.savePost(post);  // Save the post with its images in the repository

        // 4. Fetch and display all posts
        System.out.println("All Posts:");
        postRepository.getAllPosts().forEach(System.out::println);  // Print all posts

        // 5. Fetch a specific post and its images
        Post fetchedPost = postRepository.getPostWithImagesById(post.getId());  // Fetch the post by ID
        System.out.println("Fetched Post: " + fetchedPost);
        if (fetchedPost != null) {
            // Print the IDs of the images associated with the post
            fetchedPost.getImages().forEach(image -> System.out.println("Image ID: " + image.getId()));
        }

        // 6. Delete the post (cascade will delete images as well)
        postRepository.deletePost(post.getId());  // Delete the post (images will be deleted due to cascading)
        System.out.println("Post deleted successfully.");
    }
}

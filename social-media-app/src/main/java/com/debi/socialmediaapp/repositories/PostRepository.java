package com.debi.socialmediaapp.repositories;

import com.debi.socialmediaapp.models.Post;
import com.debi.socialmediaapp.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Repository class for managing CRUD operations on Post entities.
 * <p>
 * This class provides methods to save, retrieve, update, and delete Post
 * entities, including their associated images (via a left join).
 *
 * @author Ahmed Hany
 * @version 1.0.0
 * @since 1.0.0
 */
public class PostRepository {

    /**
     * Saves or updates a Post entity.
     *
     * @param post The Post object to save or update.
     */
    public void savePost(Post post) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            // Save or update the Post entity in the database
            session.saveOrUpdate(post);
            // Commit the transaction to persist the changes
            transaction.commit();
        } catch (Exception e) {
            handleError(e); // Handle errors in a structured way
        }
    }

    /**
     * Retrieves a Post entity by its ID, including its associated images.
     * This method uses a left join to fetch the Post along with its images.
     *
     * @param id The ID of the Post to retrieve.
     * @return The Post object with its images, or null if not found.
     */
    public Post getPostWithImagesById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // HQL query to fetch the Post along with its associated images
            String hql = "FROM Post p LEFT JOIN FETCH p.images WHERE p.id = :id";
            return session.createQuery(hql, Post.class)
                    .setParameter("id", id)
                    .uniqueResult(); // Retrieve the unique result (Post with images)
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for debugging
            return null; // Return null if an error occurs
        }
    }

    /**
     * Retrieves all Post entities from the database.
     *
     * @return A list of all Post objects, or null if an error occurs.
     */
    public List<Post> getAllPosts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // HQL query to fetch all Post entities
            Query<Post> query = session.createQuery("from Post", Post.class);
            return query.getResultList(); // Return the list of Post entities
        } catch (Exception e) {
            handleError(e); // Handle errors
            return null;
        }
    }

    /**
     * Deletes a Post entity by its ID.
     *
     * @param id The ID of the Post to delete.
     */
    public void deletePost(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            // Retrieve the Post entity by ID
            Post post = session.get(Post.class, id);
            if (post != null) {
                // Delete the Post entity if it exists
                session.delete(post);
            }
            // Commit the transaction to persist the deletion
            transaction.commit();
        } catch (Exception e) {
            handleError(e); // Handle errors
        }
    }

    /**
     * Handles errors that occur during database operations.
     *
     * @param e The exception to handle.
     */
    private void handleError(Exception e) {
        // Log the error details (in a production system, use a logging framework)
        e.printStackTrace();
    }
}

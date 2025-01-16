package com.debi.socialmediaapp.repositories;

import com.debi.socialmediaapp.models.Post;
import com.debi.socialmediaapp.models.PostImage;
import com.debi.socialmediaapp.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Repository class for managing CRUD operations on PostImage entities.
 * <p>
 * This class provides methods to save, retrieve, update, and delete PostImage
 * objects, while maintaining their association with Post entities.
 *
 * @author Ahmed Hany
 * @version 1.0.0
 * @since 1.0.0
 */
public class PostImagesRepository {

    /**
     * Saves or updates a PostImage entity and its associated Post entity.
     *
     * @param postImage The PostImage object to save or update.
     * @param post      The associated Post object to save or update.
     */
    public void savePostImageWithPost(PostImage postImage, Post post) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Save or update the Post entity first
            session.saveOrUpdate(post);

            // Associate the Post with the PostImage and save the PostImage
            postImage.setPost(post);
            session.saveOrUpdate(postImage);

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for debugging
        }
    }

    /**
     * Retrieves a PostImage entity by its ID.
     *
     * @param id The ID of the PostImage to retrieve.
     * @return The PostImage object if found, or null if not found.
     */
    public PostImage getPostImageById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // HQL query to fetch PostImage by ID
            String hql = "FROM PostImage p WHERE p.id = :id";
            return session.createQuery(hql, PostImage.class)
                    .setParameter("id", id)
                    .uniqueResult(); // Retrieve a single result
        } catch (Exception e) {
            handleError(e);
            return null;
        }
    }

    /**
     * Retrieves all PostImage entities from the database.
     *
     * @return A list of all PostImage objects, or null if an error occurs.
     */
    public List<PostImage> getAllPostImages() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // HQL query to fetch all PostImage entities
            Query<PostImage> query = session.createQuery("from PostImage", PostImage.class);
            return query.getResultList();
        } catch (Exception e) {
            handleError(e);
            return null;
        }
    }

    /**
     * Deletes a PostImage entity by its ID.
     *
     * @param id The ID of the PostImage to delete.
     */
    public void deletePostImage(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Retrieve the PostImage by ID
            PostImage postImage = session.get(PostImage.class, id);

            // Delete the PostImage if it exists
            if (postImage != null) {
                session.delete(postImage);
            }

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            handleError(e);
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

package com.debi.socialmediaapp.repositories;

import com.debi.socialmediaapp.models.Users;
import com.debi.socialmediaapp.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Repository class for managing Users entities.
 * Provides methods to perform CRUD operations on the USERS table.
 *
 * @author ahmedynah
 * @version 1.0.0
 * @since 1.0.0
 */
public class UserRepository {

    /**
     * Saves or updates a user in the database.
     * If the user already exists, it will be updated; otherwise, a new record will be created.
     *
     * @param users the Users entity to be saved or updated
     */
    public void saveUser(Users users) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Start a transaction
            session.saveOrUpdate(users); // Save or update the user
            transaction.commit(); // Commit the transaction
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Roll back the transaction in case of an error
            }
            e.printStackTrace(); // Print the stack trace for debugging purposes
        }
    }

    /**
     * Retrieves a user from the database by its ID.
     *
     * @param id the ID of the user to be fetched
     * @return the Users entity if found, or null if no user exists with the given ID
     */
    public Users getUserById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Users.class, id); // Fetch a user by its ID
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace for debugging purposes
            return null; // Return null in case of an error
        }
    }

    /**
     * Retrieves all users from the database.
     *
     * @return a list of all Users entities, or null if an error occurs
     */
    public List<Users> getAllUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Users", Users.class).getResultList(); // Fetch all users
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace for debugging purposes
            return null; // Return null in case of an error
        }
    }

    /**
     * Deletes a user from the database by its ID.
     *
     * @param id the ID of the user to be deleted
     */
    public void deleteUser(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Start a transaction
            Users users = session.get(Users.class, id); // Fetch the user by ID
            if (users != null) {
                session.delete(users); // Delete the user if it exists
            }
            transaction.commit(); // Commit the transaction
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Roll back the transaction in case of an error
            }
            e.printStackTrace(); // Print the stack trace for debugging purposes
        }
    }
}

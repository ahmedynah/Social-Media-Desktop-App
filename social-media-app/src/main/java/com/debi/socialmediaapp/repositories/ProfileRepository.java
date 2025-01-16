package com.debi.socialmediaapp.repositories;

import com.debi.socialmediaapp.models.Profile;
import com.debi.socialmediaapp.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.TypedQuery;
import java.util.List;

public class ProfileRepository {

    /**
     * Saves or updates a profile in the database.
     * If the profile already exists, it will be updated; otherwise, a new record will be created.
     *
     * @param profile the Profile entity to be saved or updated
     */
    public void saveProfile(Profile profile) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Start a transaction
            session.saveOrUpdate(profile); // Save or update the profile
            transaction.commit(); // Commit the transaction
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Roll back the transaction in case of an error
            }
            e.printStackTrace(); // Print the stack trace for debugging purposes
        }
    }

    /**
     * Retrieves a profile from the database by its ID.
     *
     * @param id the ID of the profile to be fetched
     * @return the Profile entity if found, or null if no profile exists with the given ID
     */
    public Profile getProfileById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Profile.class, id); // Fetch the profile by its ID
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace for debugging purposes
            return null; // Return null in case of an error
        }
    }

    /**
     * Retrieves all profiles from the database.
     *
     * @return a list of all Profile entities, or null if an error occurs
     */
    public List<Profile> getAllProfiles() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Profile", Profile.class).getResultList(); // Fetch all profiles
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace for debugging purposes
            return null; // Return null in case of an error
        }
    }

    /**
     * Retrieves a profile by its associated user ID.
     *
     * @param userId the user ID associated with the profile to be fetched
     * @return the Profile entity if found, or null if no profile exists for the given user ID
     */
    public Profile getProfileByUserId(Long userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String query = "SELECT p FROM Profile p WHERE p.user.id = :userId";
            TypedQuery<Profile> typedQuery = session.createQuery(query, Profile.class);
            typedQuery.setParameter("userId", userId);
            return typedQuery.getSingleResult(); // Fetch the profile by user ID
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace for debugging purposes
            return null; // Return null in case of an error
        }
    }

    /**
     * Deletes a profile from the database by its ID.
     *
     * @param id the ID of the profile to be deleted
     */
    public void deleteProfile(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction(); // Start a transaction
            Profile profile = session.get(Profile.class, id); // Fetch the profile by ID
            if (profile != null) {
                session.delete(profile); // Delete the profile if it exists
            }
            transaction.commit(); // Commit the transaction
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Roll back the transaction in case of an error
            }
            e.printStackTrace(); // Print the stack trace for debugging purposes
        }
    }

    /**
     * Retrieves the bio of a profile by its associated user ID.
     *
     * @param userId the user ID associated with the profile
     * @return the bio of the profile if found, or null if no profile exists for the given user ID
     */
    public String getBioByUserId(Long userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String query = "SELECT p.bio FROM Profile p WHERE p.user.id = :userId";
            return session.createQuery(query, String.class)
                    .setParameter("userId", userId)
                    .uniqueResult(); // Fetch the bio by user ID
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace for debugging purposes
            return null; // Return null in case of an error
        }
    }

    /**
     * Retrieves the profile picture of a profile by its associated user ID.
     *
     * @param userId the user ID associated with the profile
     * @return the profile picture (as byte[]) if found, or null if no profile exists for the given user ID
     */
    public byte[] getProfilePictureByUserId(Long userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String query = "SELECT p.personalPhoto FROM Profile p WHERE p.user.id = :userId";
            return session.createQuery(query, byte[].class)
                    .setParameter("userId", userId)
                    .uniqueResult(); // Fetch the profile picture by user ID
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace for debugging purposes
            return null; // Return null in case of an error
        }
    }
}

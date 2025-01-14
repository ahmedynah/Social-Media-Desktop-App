package com.debi.socialmediaapp.utils;

import com.debi.socialmediaapp.models.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Utility class for configuring and managing Hibernate SessionFactory.
 * Provides methods to obtain the SessionFactory, test database connections, and shut down Hibernate resources.
 *
 * @author ahmedynah
 * @version 1.0.0
 * @since 1.0.0
 */
public class HibernateUtil {

    /**
     * Singleton SessionFactory instance for the application.
     * It is initialized once during the application lifecycle.
     */
    private static final SessionFactory sessionFactory;

    /**
     * Provides access to the SessionFactory instance.
     *
     * @return the singleton SessionFactory instance
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Static initializer block for configuring Hibernate SessionFactory
    static {
        try {
            // Load configuration from hibernate.properties or manually set properties
            Configuration configuration = new Configuration();
            configuration.addProperties(configuration.getProperties());

            // Add all annotated entity classes
            configuration.addAnnotatedClass(Users.class);

            // Alternatively, scan a package containing entities
            configuration.addPackage("com.debi.socialmediaapp.models");

            // Build the ServiceRegistry with Hibernate settings
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();

            // Build the SessionFactory using the configuration and service registry
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            // Throw an error if initialization fails
            throw new ExceptionInInitializerError("Initial SessionFactory creation failed: " + ex);
        }
    }

    /**
     * Shuts down the Hibernate SessionFactory, releasing resources such as caches and connection pools.
     */
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    /**
     * Tests the database connection by executing a simple query.
     *
     * @return true if the connection is successful, false otherwise
     */
    public static boolean testConnection() {
        try (Session session = sessionFactory.openSession()) {
            // Execute a simple native SQL query to test the connection
            session.createNativeQuery("SELECT 1").getResultList();
            return true; // Connection is successful
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for debugging purposes
            return false; // Connection failed
        }
    }
}

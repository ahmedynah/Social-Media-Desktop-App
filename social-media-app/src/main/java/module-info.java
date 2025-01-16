module com.debi.socialmediaapp {

    // Requires JavaFX modules for building GUI applications
    requires javafx.controls; // Provides JavaFX UI controls such as buttons, labels, etc.
    requires javafx.fxml; // Enables the use of FXML files for defining the UI layout

    // Lombok library for generating boilerplate code (e.g., getters, setters) at compile time
    requires static lombok; // Marked as static because it is only needed during compilation, not runtime

    // Java modules for database and naming services
    requires java.naming; // Provides support for directory and naming services (e.g., JNDI)
    requires java.sql; // Allows working with relational databases using SQL APIs

    // External libraries required by the application
    requires com.dlsc.formsfx; // A library for creating form-based UI components
    requires java.desktop; // Provides AWT and Swing APIs, which might be used for desktop utilities
    requires java.persistence; // Includes annotations and interfaces for JPA (Java Persistence API)
    requires org.hibernate.orm.core;
    requires spring.security.crypto; // The Hibernate ORM framework for object-relational mapping and persistence

    // Specifies packages that can be accessed by other modules or frameworks

    // Opens the main package to JavaFX FXML loader for reflection-based access
    opens com.debi.socialmediaapp to javafx.fxml;

    // Opens the models package to Hibernate for reflection-based access to JPA entities
    opens com.debi.socialmediaapp.models to org.hibernate.orm.core;

    // Exports the main package to make it accessible to other modules
    exports com.debi.socialmediaapp;

    // Exports the models package specifically to Hibernate ORM for persistence and mapping purposes
    exports com.debi.socialmediaapp.models to org.hibernate.orm.core;

    // Exports the controllers package to make JavaFX controller classes accessible to other modules
    exports com.debi.socialmediaapp.controllers;

    // Opens the controllers package to JavaFX FXML loader for reflection-based access
    opens com.debi.socialmediaapp.controllers to javafx.fxml;

    // Exports the repositories package to allow external modules to use data access classes
    exports com.debi.socialmediaapp.repositories;

    // Opens the repositories package to JavaFX FXML loader for reflection-based access
    opens com.debi.socialmediaapp.repositories to javafx.fxml;
}

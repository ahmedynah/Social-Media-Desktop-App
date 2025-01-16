package com.debi.socialmediaapp.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString methods
@Entity // Marks this class as a JPA entity
@NoArgsConstructor // Generates a no-argument constructor
@AllArgsConstructor // Generates an all-argument constructor

@Table(name = "profiles") // Maps this class to the USERS table in the database
public class Profile {
    /**
     * Primary key for the PROFILES table.
     * It is auto-generated by the database using the IDENTITY strategy.
     */
    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Specifies the primary key generation strategy
    @Column(name = "id") // Maps this field to the ID column in the PROFILES table
    private long id;
    // A constructor that takes everything except the id
    public Profile(String bio, byte[] personalPhoto, User user) {
        this.bio = bio;
        this.personalPhoto = personalPhoto;
        this.user = user;
    }
    /**
     * Column representing the bio of the profile.
     */
    @Column(name = "bio", nullable = false) // Maps this field to the BIO column in the PROFILES table, cannot be null
    private String bio;

    /**
     * Column representing the personal photo of the profile.
     * Stored as binary data (BLOB).
     */
    @Lob // Marks this field as a large object, suitable for BLOB storage
    @Column(name = "personal_photo", nullable = false) // Maps this field to the PERSONAL_PHOTO column in the PROFILES table, cannot be null
    private byte[] personalPhoto;

    /**
     * Column representing the user ID associated with this profile.
     * Establishes a one-to-one relationship with the USERS table.
     */
    @OneToOne // Specifies a one-to-one relationship
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true, nullable = false,
            foreignKey = @ForeignKey(name = "FK_PROFILE_USER")) // Maps this field to the USER_ID column in the PROFILES table with a foreign key constraint
    private User user;
}


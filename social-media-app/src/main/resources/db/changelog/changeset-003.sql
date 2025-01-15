CREATE TABLE profiles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- Primary key, auto-incrementing unique identifier for each profile
    bio TEXT NOT NULL,                     -- Stores the bio of the user; this field cannot be NULL
    personal_photo BLOB NOT NULL,          -- Stores the personal photo of the user; this field cannot be NULL
    user_id INT UNIQUE,                    -- A unique identifier linking this profile to a user in the 'users' table
    FOREIGN KEY (user_id) REFERENCES users(id) 
        ON UPDATE CASCADE                  -- Updates in 'users.id' cascade to 'profiles.user_id'
        ON DELETE CASCADE                  -- Deletion in 'users.id' cascades to remove the associated profile
);

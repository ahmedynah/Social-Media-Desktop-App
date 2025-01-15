-- Creating the USERS table with an ID, USERNAME, and PASSWORD columns
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- The primary key for the table, an auto-incrementing BIGINT
    first_name VARCHAR(255) NOT NULL,         -- A column for storing the username, it cannot be null
    last_name VARCHAR(255) NOT NULL,         -- A column for storing the username, it cannot be null
    email VARCHAR(255) NOT NULL,         -- A column for storing the username, it cannot be null
    password VARCHAR(1024) NOT NULL         -- A column for storing the password, it cannot be null
);
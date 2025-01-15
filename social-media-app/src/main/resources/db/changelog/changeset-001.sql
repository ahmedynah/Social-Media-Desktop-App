-- Creating the USERS table with an ID, USERNAME, and PASSWORD columns
CREATE TABLE USERS (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,  -- The primary key for the table, an auto-incrementing BIGINT
    USERNAME VARCHAR(255) NOT NULL,         -- A column for storing the username, it cannot be null
    PASSWORD VARCHAR(1024) NOT NULL         -- A column for storing the password, it cannot be null
);


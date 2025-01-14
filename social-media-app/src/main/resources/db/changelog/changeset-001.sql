-- Creating the USERS table with an ID, USERNAME, and PASSWORD columns
CREATE TABLE USERS (
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,  -- The primary key for the table, an auto-incrementing BIGINT
    USERNAME VARCHAR(255) NOT NULL,         -- A column for storing the username, it cannot be null
    PASSWORD VARCHAR(1024) NOT NULL         -- A column for storing the password, it cannot be null
);

-- Adding comments to the table and columns using ALTER TABLE
ALTER TABLE USERS COMMENT = 'Table storing user information such as ID, username, and password';

-- Adding comments to individual columns
ALTER TABLE USERS MODIFY COLUMN ID BIGINT AUTO_INCREMENT COMMENT 'The primary key, auto-incremented';
ALTER TABLE USERS MODIFY COLUMN USERNAME VARCHAR(255) NOT NULL COMMENT 'The username for the user, cannot be null';
ALTER TABLE USERS MODIFY COLUMN PASSWORD VARCHAR(1024) NOT NULL COMMENT 'The password for the user, cannot be null';

-- End of changeset

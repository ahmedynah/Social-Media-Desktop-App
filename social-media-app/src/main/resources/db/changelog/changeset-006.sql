CREATE TABLE social_media_app_db.friendship (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    friend_id BIGINT NOT NULL,
    CONSTRAINT fk_friendship_users FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_friendship_friends FOREIGN KEY (friend_id) REFERENCES users(id) ON DELETE CASCADE
);
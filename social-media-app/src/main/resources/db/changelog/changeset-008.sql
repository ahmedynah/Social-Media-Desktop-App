CREATE TABLE social_media_app_db.post_comments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    text VARCHAR(255) NOT NULL,
    CONSTRAINT fk_postimages_posts FOREIGN KEY (post_id) REFERENCES Posts(id) ON DELETE CASCADE
    CONSTRAINT fk_postimages_posts FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
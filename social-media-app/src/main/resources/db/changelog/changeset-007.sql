CREATE TABLE social_media_app_db.post_likes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_like_posts FOREIGN KEY (post_id) REFERENCES Posts(id) ON DELETE CASCADE,
    CONSTRAINT fk_like_users FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
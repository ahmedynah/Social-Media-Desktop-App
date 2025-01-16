CREATE TABLE social_media_app_db.PostImages (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    image BLOB NOT NULL,
    post_id BIGINT NOT NULL,
    CONSTRAINT fk_postimages_posts FOREIGN KEY (post_id) REFERENCES Posts(id) ON DELETE CASCADE
);
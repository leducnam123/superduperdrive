CREATE TABLE IF NOT EXISTS USERS (
                                     user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     username VARCHAR(20) UNIQUE NOT NULL,
    salt VARCHAR(64) NOT NULL,
    password VARCHAR(512) NOT NULL,
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL
    );

CREATE TABLE IF NOT EXISTS NOTES (
                                     note_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     user_id BIGINT NOT NULL,
                                     note_title VARCHAR(100) NOT NULL,
    note_description VARCHAR(1000) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES USERS(user_id)
    );

CREATE TABLE IF NOT EXISTS FILES (
                                     file_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     file_name VARCHAR(255) NOT NULL,
    content_type VARCHAR(100) NOT NULL,
    file_size VARCHAR(100) NOT NULL,
    user_id BIGINT NOT NULL,
    file_data LONGBLOB NOT NULL,
    FOREIGN KEY (user_id) REFERENCES USERS(user_id)
    );

CREATE TABLE IF NOT EXISTS CREDENTIALS (
                                           credential_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                           url VARCHAR(100) NOT NULL,
    username VARCHAR(30) NOT NULL,
    `key` VARCHAR(128) NOT NULL,
    password VARCHAR(128) NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES USERS(user_id)
    );
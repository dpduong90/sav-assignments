DROP TABLE IF EXISTS app_user;
CREATE TABLE app_user (
    id  BIGINT AUTO_INCREMENT  PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255)
);

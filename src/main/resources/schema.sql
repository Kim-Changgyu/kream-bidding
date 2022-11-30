CREATE TABLE if NOT EXISTS users
(
    user_id     BIGINT      PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_name   VARCHAR(50) NOT NULL,
    address     VARCHAR(50) NOT NULL
);

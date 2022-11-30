CREATE TABLE if NOT EXISTS user
(
    id          BIGINT      PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL,
    address     VARCHAR(50) NOT NULL
);

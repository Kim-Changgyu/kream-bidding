CREATE TABLE if NOT EXISTS user
(
    id          BIGINT      PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL,
    address     VARCHAR(50) NOT NULL
);

CREATE TABLE if NOT EXISTS product
(
    id              BIGINT          PRIMARY KEY NOT NULL AUTO_INCREMENT,
    category        VARCHAR(20)     NOT NULL,
    brand           VARCHAR(50)     NOT NULL,
    name            VARCHAR(50)     NOT NULL,
    serial_number   VARCHAR(50)     NOT NULL,
    price           BIGINT,
    color           VARCHAR(20),
    released_at      DATETIME(6),
    gender          VARCHAR(20),
    size            VARCHAR(20)
);

CREATE TABLE if NOT EXISTS user
(
    id          BIGINT      PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL,
    email       VARCHAR(50) NOT NULL UNIQUE,
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

CREATE TABLE if NOT EXISTS bidding
(
    id              BIGINT          PRIMARY KEY NOT NULL AUTO_INCREMENT,
    product_id      VARCHAR(50)     NOT NULL,
    user_id         VARCHAR(50)     NOT NULL,
    bidding_type    VARCHAR(50)     NOT NULL,
    price           BIGINT          NOT NULL,
    bidding_status  VARCHAR(50)     NOT NULL,
    created_at      DATETIME(6)     DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE TABLE if NOT EXISTS trade
(
    id              BIGINT          PRIMARY KEY NOT NULL AUTO_INCREMENT,
    buy_bidding_id  VARCHAR(50)     NOT NULL,
    sell_bidding_id VARCHAR(50)     NOT NULL,
    invoice_number  VARCHAR(50)     DEFAULT NULL,
    price           BIGINT          NOT NULL,
    trade_status    VARCHAR(50)     NOT NULL
);
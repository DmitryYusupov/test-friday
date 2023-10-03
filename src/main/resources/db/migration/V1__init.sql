CREATE TABLE country
(
    uid  bigint PRIMARY KEY,
    name text NOT NULL
);

CREATE TABLE city
(
    uid         bigint PRIMARY KEY,
    country_uid bigint NOT NULL,
    name        text   NOT NULL,
    CONSTRAINT fk_city_country_uid_country_uid FOREIGN KEY (country_uid) REFERENCES country (uid)
);
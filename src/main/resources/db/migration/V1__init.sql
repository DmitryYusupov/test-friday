CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE country
(
    uid  uuid PRIMARY KEY,
    name text NOT NULL
);

CREATE TABLE city
(
    uid         uuid PRIMARY KEY,
    country_uid uuid NOT NULL,
    name        text   NOT NULL,
    CONSTRAINT fk_city_country_uid_country_uid FOREIGN KEY (country_uid) REFERENCES country (uid)
);
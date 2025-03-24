--liquibase formatted sql
--changeset eremin-a:1 logicalFilePath:init.sql

CREATE TABLE clothes
(
    id       uuid,
    gender   VARCHAR(255),
    name     VARCHAR(255),
    category VARCHAR(255),
    season   VARCHAR(255),
    brand    VARCHAR(255),
    color    VARCHAR(255),
    PRIMARY KEY (id)
);
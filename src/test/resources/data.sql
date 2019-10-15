CREATE SCHEMA IF NOT EXISTS db;

USE db;

DROP TABLE IF EXISTS pets, owners, appointments;

CREATE TABLE IF NOT EXISTS owners
(
  id             VARCHAR(50) PRIMARY KEY,
  first_name     VARCHAR(50),
  surname        VARCHAR(50),
  contact_number VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS pets
(
  id            VARCHAR(50) PRIMARY KEY,
  name          VARCHAR(50),
  breed         VARCHAR(50),
  date_of_birth DATE,
  owner_id      VARCHAR(50) NOT NULL,
  FOREIGN KEY (owner_id) REFERENCES owners (id)
);

CREATE TABLE IF NOT EXISTS appointments
(
  id       VARCHAR(50) PRIMARY KEY,
  pet_id   VARCHAR(50),
  start    DATETIME    NOT NULL,
  end      DATETIME    NOT NULL,
  FOREIGN KEY (pet_id) REFERENCES pets (id)
);



INSERT INTO owners (id, first_name, surname, contact_number)
VALUES ('57801773-58ed-4b60-a2bc-3a449749e77e', 'Sally', 'Brown', '01773 857748'),
       ('fae9463c-25b1-473c-b328-9b91cde2fce9', 'Harry', 'Smith', '07354 543224'),
       ('613c0673-1119-4a60-ab31-059f08009267', 'David', 'Graham', '07146 879238');

INSERT INTO pets (id, name, breed, date_of_birth, owner_id)
VALUES ('761666de-610a-4a4c-8506-16a741b779d5', 'Greg', 'Border Collie', '2013-05-12',
        '57801773-58ed-4b60-a2bc-3a449749e77e'),
       ('72d20ac6-d26b-42f0-bff5-5f2c1e6606d2', 'Dave', 'Labrador', '2015-11-26',
        'fae9463c-25b1-473c-b328-9b91cde2fce9'),
       ('64b660a4-d606-47d2-9cca-ab693779bec1', 'Greg II', 'Border Collie', '2010-08-04',
        '613c0673-1119-4a60-ab31-059f08009267');

INSERT INTO appointments (id, pet_id, start, end)
VALUES ('e93bcc1f-98b1-48b8-bf17-563406770526', '761666de-610a-4a4c-8506-16a741b779d5', '2019-08-14 14:30:00', '2019-08-14 15:00:00'),
       ('0fd285c6-259c-4c9c-bfd2-df19cf2570b5', '72d20ac6-d26b-42f0-bff5-5f2c1e6606d2', '2019-07-06 09:45:00', '2019-07-06 10:00:00'),
       ('9a16ba1c-ef42-43a0-a3cf-06a9bdd5999b', '64b660a4-d606-47d2-9cca-ab693779bec1', '2019-06-05 16:30:00', '2019-06-05 17:00:00'),
       ('a58195a6-00a1-4c74-a9e5-6562e38b3476', '72d20ac6-d26b-42f0-bff5-5f2c1e6606d2', '2019-08-25 11:00:00', '2019-08-25 12:00:00');
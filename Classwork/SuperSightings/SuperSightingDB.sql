DROP DATABASE IF EXISTS SuperSightingDB;
CREATE DATABASE SuperSightingDB;

USE SuperSightingDB;

CREATE TABLE `Super`(
    superId INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    isHero BOOL NOT NULL,
    isVillain BOOL NOT NULL
);

CREATE TABLE Power(
    powerId INT PRIMARY KEY AUTO_INCREMENT,
    `name`VARCHAR(50) NOT NULL,
    `description` VARCHAR(255) NOT NULL
);

CREATE TABLE SuperPower(
    superId INT NOT NULL,
    powerId INT NOT NULL,
    PRIMARY KEY (superId, powerId),
    FOREIGN KEY fk_SuperPower_superId (superId)
        REFERENCES `Super` (superId),
    FOREIGN KEY fk_SuperPower_powerId (powerId)
        REFERENCES Power (powerId)
);

CREATE TABLE `Organization`(
    organizationId INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `description`VARCHAR(255) NOT NULL,
    address VARCHAR(50),
    phone CHAR(14),
    email VARCHAR(32),
    isHeroic BOOL NOT NULL,
    isVillainous BOOL NOT NULL
);

CREATE TABLE SuperOrganization(
    superId INT NOT NULL,
    organizationId INT NOT NULL,
    PRIMARY KEY (superId, organizationId),
    FOREIGN KEY fk_SuperOrganization_superId (superId)
        REFERENCES `Super` (superId),
    FOREIGN KEY fk_SuperOrganization_organizationId (organizationId)
        REFERENCES `Organization` (organizationId)
);

CREATE TABLE Location(
    locationId INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `description`VARCHAR(255) NOT NULL,
    address VARCHAR(50) NOT NULL,
    latitude CHAR(9) NOT NULL,
    longitude CHAR(9) NOT NULL
);

CREATE TABLE Sighting(
    sightingId INT PRIMARY KEY AUTO_INCREMENT,
    locationId INT NOT NULL,
    `date` TIMESTAMP DEFAULT NOW() NOT NULL,
    FOREIGN KEY fk_Sighting_locationId (locationId)
        REFERENCES Location (locationId)
);

CREATE TABLE SuperSighting(
    superId INT NOT NULL,
    sightingId INT NOT NULL,
    PRIMARY KEY (superId, sightingId),
    FOREIGN KEY fk_SuperSighting_superId (superId)
        REFERENCES `Super` (superId),
    FOREIGN KEY fk_SuperSighting_sightingId (sightingId)
        REFERENCES Sighting (sightingId)
);

-- -- -- -- -- -- -- -- -- --

INSERT INTO `Super`(`name`, `description`, isHero, isVillain)
VALUES
("All Might", "Japan's Number 1 Hero, he smashes villains with extreme physical strength", 1, 0),
("All For One", "A mysterious villain in Japan for whom there is little public information available", 0, 1),
("Endeavor", "Japan's Numbe 2 Hero, he blasts villains with blazing fire", 1, 0);

INSERT INTO Power(`name`, `description`)
VALUES
("One for All", "Stores power over time, and can be passed down from one user to a new user"),
("All for One", "Steals powers from others, enables those powers to be freely used, and can grant those powers to others"),
("Hellflame", "Generate extremely hot fire and manipulate it to a certain degree");

INSERT INTO SuperPower(superId, powerId)
VALUES
(1, 1),
(2, 2),
(3, 3);

INSERT INTO `Organization`(`name`, `description`, address, phone, email, isHeroic, isVillainous)
VALUES
("Might Tower", "All Might's Hero Office, located in the Roppongi district of Minato, Tokyo, Japan", "Not Available", "Not Available", "Not Available", 1, 0),
("League of Villains", "A small underground organization committed to evil acts", "", "", "", 0, 1),
("Endeavor Hero Agency", "Endeavor's Hero Office", "Not Available", "Not Available", "Not Available", 1, 0);

INSERT INTO SuperOrganization(superId, organizationId)
VALUES
(1, 1),
(2, 2),
(3, 3);

INSERT INTO Location(`name`, `description`, address, latitude, longitude)
VALUES
("Kamino, Yokohama", "A large town with a thriving business scene; it was heavily damaged in a past incident, but has since been rebuilt", "City-wide", "  35.4441", " 139.6380");

INSERT INTO Sighting(locationId, `date`)
VALUES
(1, TIMESTAMP("2018-06-16", "05:00:00"));

INSERT INTO SuperSighting(superId, sightingId)
VALUES
(1, 1),
(2, 1),
(3, 1);

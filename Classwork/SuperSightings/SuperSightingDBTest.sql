DROP DATABASE IF EXISTS SuperSightingDBTest;
CREATE DATABASE SuperSightingDBTest;

USE SuperSightingDBTest;

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

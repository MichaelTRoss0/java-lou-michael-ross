USE SuperSightingDB;

INSERT INTO `Super`(`name`, `description`, isHero, isVillain)
VALUES
("All Might", "Japan's Number 1 Hero, he smashes villains with extreme physical strength", 1, 0),
("All For One", "A mysterious villain in Japan for whom there is little public information available", 0, 1),
("Endeavor", "Japan's Numbe 2 Hero, he blasts villains with blazing fire", 1, 0);

SELECT * FROM `Super`;

INSERT INTO Power(`name`, `description`)
VALUES
("One for All", "Stores power over time, and can be passed down from one user to a new user"),
("All for One", "Steals powers from others, enables those powers to be freely used, and can grant those powers to others"),
("Hellflame", "Generate extremely hot fire and manipulate it to a certain degree");

SELECT * FROM Power;

INSERT INTO SuperPower(superId, powerId)
VALUES
(1, 1),
(2, 2),
(3, 3);

SELECT
    su.`name`,
    p.`name`
FROM `Super` su
JOIN SuperPower sp ON sp.superId = su.superId
JOIN Power p ON p.powerId = sp.powerId;

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

SELECT
    su.`name`,
    o.`name`
FROM `Super` su
JOIN SuperOrganization so ON so.superId = su.superId
JOIN Organization o ON o.organizationId = so.organizationId;

INSERT INTO Location(`name`, `description`, address, latitude, longitude)
VALUES
("Kamino, Yokohama", "A large town with a thriving business scene; it was heavily damaged in a past incident, but has since been rebuilt", "City-wide", "  35.4441", " 139.6380");

INSERT INTO Sighting(locationId, `date`)
VALUES
(1, NOW()),
(1, NOW()),
(1, NOW());

INSERT INTO SuperSighting(superId, sightingId)
VALUES
(1, 1),
(2, 2),
(3, 3);

SELECT
    su.`name`,
    si.`date`,
    l.`name`
FROM `Super` su
JOIN SuperSighting ss ON su.superId = ss.superId
JOIN Sighting si ON si.sightingId = ss.sightingId
JOIN Location l ON l.locationId = si.locationId;

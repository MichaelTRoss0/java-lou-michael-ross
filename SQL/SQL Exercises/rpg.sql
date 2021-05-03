DROP DATABASE IF EXISTS discfinder;

CREATE DATABASE discfinder;

USE discfinder;

CREATE TABLE items (
	itemId INT PRIMARY KEY AUTO_INCREMENT,
    itemName VARCHAR(100),
	goldValue decimal
);

CREATE TABLE races (
	raceId INT PRIMARY KEY NOT NULL,
    raceName VARCHAR(20)
);

CREATE TABLE classes (
	classId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    className VARCHAR(20)
);

CREATE TABLE players (
	playerId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    playerName VARCHAR(20)
);

CREATE TABLE characters (
	characterId INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    playerId INT,
    firstName VARCHAR(20),
    lastName VARCHAR(20),
    FOREIGN KEY fk_Characters_players (playerId)
		REFERENCES players(playerId)
);

CREATE TABLE characterRaces (
	characterId INT,
    raceId INT,
    foreign key fk_CharRaces_races (raceId)
		REFERENCES races(raceId),
	foreign key fk_CharRace_character (characterId)
		REFERENCES characters(characterId)
);

CREATE TABLE characterClasses (
	characterId INT,
    classId INT,
    isPrimary BOOL,
    foreign key fk_CharClasses_char (characterId)
		REFERENCES characters(characterId),
	foreign key fk_CharClasses_class (classId)
		REFERENCES classes (classId)
); 

CREATE TABLE inventory (
	characterId INT,
    itemId INT,
    quantity INT,
	foreign key fk_Inventory_CharacterId (characterId)
		REFERENCES characters (characterId),
	foreign key fk_Inventory_itemId (itemId)
		REFERENCES items(itemId)
);

INSERT INTO `players` (`playerName`) VALUES ("Brianna");
INSERT INTO `players` (`playerName`) VALUES ("Saffron");
INSERT INTO `players` (`playerName`) VALUES ("Brand");
INSERT INTO `players` (`playerName`) VALUES ("Jesse");
INSERT INTO `players` (`playerName`) VALUES ("Sierra");
INSERT INTO `players` (`playerName`) VALUES ("Imarie");
INSERT INTO `players` (`playerName`) VALUES ("Julius");
INSERT INTO `players` (`playerName`) VALUES ("Kuang");
INSERT INTO `players` (`playerName`) VALUES ("Sajia");
INSERT INTO `players` (`playerName`) VALUES ("Tamali");
INSERT INTO `players` (`playerName`) VALUES ("Xavier");

INSERT INTO `classes` (`className`) VALUES ("Barbarian");
INSERT INTO `classes` (`className`) VALUES ("Bard");
INSERT INTO `classes` (`className`) VALUES ("Cleric");
INSERT INTO `classes` (`className`) VALUES ("Druid");
INSERT INTO `classes` (`className`) VALUES ("Fighter");
INSERT INTO `classes` (`className`) VALUES ("Monk");
INSERT INTO `classes` (`className`) VALUES ("Paladin");
INSERT INTO `classes` (`className`) VALUES ("Ranger");
INSERT INTO `classes` (`className`) VALUES ("Rogue");
INSERT INTO `classes` (`className`) VALUES ("Sorcerer");
INSERT INTO `classes` (`className`) VALUES ("Wizard");
INSERT INTO `classes` (`className`) VALUES ("Alchemist");
INSERT INTO `classes` (`className`) VALUES ("Cavalier");
INSERT INTO `classes` (`className`) VALUES ("Gunslinger");
INSERT INTO `classes` (`className`) VALUES ("Inquisitor");
INSERT INTO `classes` (`className`) VALUES ("Magus");
INSERT INTO `classes` (`className`) VALUES ("Oracle");
INSERT INTO `classes` (`className`) VALUES ("Summoner");
INSERT INTO `classes` (`className`) VALUES ("Witch");
INSERT INTO `classes` (`className`) VALUES ("Antipaladin");
INSERT INTO `classes` (`className`) VALUES ("Ninja");
INSERT INTO `classes` (`className`) VALUES ("Samurai");
INSERT INTO `classes` (`className`) VALUES ("Cheesemaker");
INSERT INTO `classes` (`className`) VALUES ("Watchman");
INSERT INTO `classes` (`className`) VALUES ("Ingrate");
INSERT INTO `classes` (`className`) VALUES ("King of Dwarves");

INSERT INTO `races` (`raceId`, `raceName`) VALUES (1,"Aasimar");
INSERT INTO `races` (`raceId`, `raceName`) VALUES (3,"Drow");
INSERT INTO `races` (`raceId`, `raceName`) VALUES (4, "Dwarf");
INSERT INTO `races` (`raceId`, `raceName`) VALUES (5, "Elf");
INSERT INTO `races` (`raceId`, `raceName`) VALUES (7, "Gnome");
INSERT INTO `races` (`raceId`, `raceName`) VALUES (8, "Goblin");
INSERT INTO `races` (`raceId`, `raceName`) VALUES (9, "Half Elf");
INSERT INTO `races` (`raceId`, `raceName`) VALUES (10, "Half Orc");
INSERT INTO `races` (`raceId`, `raceName`) VALUES (11, "Halfling");
INSERT INTO `races` (`raceId`, `raceName`) VALUES (12, "Hobgoblin");
INSERT INTO `races` (`raceId`, `raceName`) VALUES (13, "Human");
INSERT INTO `races` (`raceId`, `raceName`) VALUES (15, "Kobold");
INSERT INTO `races` (`raceId`, `raceName`) VALUES (16, "Orc");
INSERT INTO `races` (`raceId`, `raceName`) VALUES (22, "Werewolf");
INSERT INTO `races` (`raceId`, `raceName`) VALUES (26, "Ratfolk");
INSERT INTO `races` (`raceId`, `raceName`) VALUES (27, "Sylph");
INSERT INTO `races` (`raceId`, `raceName`) VALUES (28, "Tengu");
INSERT INTO `races` (`raceId`, `raceName`) VALUES (30, "Tiefling");
INSERT INTO `races` (`raceId`, `raceName`) VALUES (31, "Undine");
INSERT INTO `races` (`raceId`, `raceName`) VALUES (32, "Oread");
INSERT INTO `races` (`raceId`, `raceName`) VALUES (36, "Tian");
INSERT INTO `races` (`raceId`, `raceName`) VALUES (39, "Nac Mac Feegle");
INSERT INTO `races` (`raceId`, `raceName`) VALUES (40, "Nobby");
INSERT INTO `races` (`raceId`, `raceName`) VALUES (42, "Pot o Pinks");
INSERT INTO `races` (`raceId`, `raceName`) VALUES (112, "Sapient Pearwood");
INSERT INTO `races` (`raceId`, `raceName`) VALUES (113, "Robot");

INSERT INTO `items` (`itemName`, `goldValue`) VALUES ("gold", 1);
INSERT INTO `items` (`itemName`, `goldValue`) VALUES ("large ruby", 100);
INSERT INTO `items` (`itemName`, `goldValue`) VALUES ("soup pot", 0.1);
INSERT INTO `items` (`itemName`, `goldValue`) VALUES ("butterfly net", 2);
INSERT INTO `items` (`itemName`, `goldValue`) VALUES ("small onion", 0.05);
INSERT INTO `items` (`itemName`, `goldValue`) VALUES ("small carrot", 0.1);
INSERT INTO `items` (`itemName`, `goldValue`) VALUES ("large carrot", 0.2);
INSERT INTO `items` (`itemName`, `goldValue`) VALUES ("wheel of cheese", 3);
INSERT INTO `items` (`itemName`, `goldValue`) VALUES ("poisoned apple", 1);
INSERT INTO `items` (`itemName`, `goldValue`) VALUES ("regular apple", 0.2);
INSERT INTO `items` (`itemName`, `goldValue`) VALUES ("mealy apple", 0.1);
INSERT INTO `items` (`itemName`, `goldValue`) VALUES ("leather graves", 5);
INSERT INTO `items` (`itemName`, `goldValue`) VALUES ("evil chicken", 2);
INSERT INTO `items` (`itemName`, `goldValue`) VALUES ("obviously fake moustache", 1);
INSERT INTO `items` (`itemName`, `goldValue`) VALUES ("bowler hat", 3);
INSERT INTO `items` (`itemName`, `goldValue`) VALUES ("cardboard soled boots", 0.1);
INSERT INTO `items` (`itemName`, `goldValue`) VALUES ("leather boots", 10);
INSERT INTO `items` (`itemName`, `goldValue`) VALUES ("rusty dagger", 1);
INSERT INTO `items` (`itemName`, `goldValue`) VALUES ("less rusty dagger", 2);
INSERT INTO `items` (`itemName`, `goldValue`) VALUES ("butter knife", 1);
INSERT INTO `items` (`itemName`, `goldValue`) VALUES ("smushed velvet and rhinestone wizard hat", 1337);
INSERT INTO `items` (`itemName`) VALUES ("odd disposition");
INSERT INTO `items` (`itemName`) VALUES ("CRIVENS!");

INSERT INTO `characters` (`playerId`, `firstName`, `lastName`) VALUES (10, "Cherry", "Strongbow");
INSERT INTO `characters` (`playerId`, `firstName`, `lastName`) VALUES (4, "Susan", "Sto Helit");
INSERT INTO `characters` (`playerId`, `firstName`, `lastName`) VALUES (2, "Sam", "Vimes");
INSERT INTO `characters` (`playerId`, `firstName`, `lastName`) VALUES (6, "Tiffany", "Aching");
INSERT INTO `characters` (`playerId`, `firstName`, `lastName`) VALUES (8, "Nobby", "Nobbs");
INSERT INTO `characters` (`playerId`, `firstName`, `lastName`) VALUES (8, "Rob", "Anybody");
INSERT INTO `characters` (`playerId`, `firstName`) VALUES (3, "Cohen");
INSERT INTO `characters` (`playerId`, `firstName`, `lastName`) VALUES (5, "Angua", "von Uberwald");
INSERT INTO `characters` (`playerId`, `firstName`) VALUES (7, "Carrot");
INSERT INTO `characters` (`firstName`, `lastName`) VALUES ("The", "Luggage");
INSERT INTO `characters` (`playerId`, `lastName`) VALUES (11, "Rincewind");

INSERT INTO `characterRaces` (`characterId`, `raceId`) VALUES (1, 4);
INSERT INTO `characterRaces` (`characterId`, `raceId`) VALUES (3, 13);
INSERT INTO `characterRaces` (`characterId`, `raceId`) VALUES (4, 13);
INSERT INTO `characterRaces` (`characterId`, `raceId`) VALUES (7, 13);
INSERT INTO `characterRaces` (`characterId`, `raceId`) VALUES (6, 39);
INSERT INTO `characterRaces` (`characterId`, `raceId`) VALUES (5, 40);
INSERT INTO `characterRaces` (`characterId`, `raceId`) VALUES (8, 22);
INSERT INTO `characterRaces` (`characterId`, `raceId`) VALUES (8, 13);
INSERT INTO `characterRaces` (`characterId`, `raceId`) VALUES (9, 13);
INSERT INTO `characterRaces` (`characterId`, `raceId`) VALUES (10, 112);
INSERT INTO `characterRaces` (`characterId`, `raceId`) VALUES (11, 13);

INSERT INTO `characterClasses` (`characterId`, `classId`, `isPrimary`) VALUES (4, 19, TRUE);
INSERT INTO `characterClasses` (`characterId`, `classId`, `isPrimary`) VALUES (4, 23, FALSE);
INSERT INTO `characterClasses` (`characterId`, `classId`, `isPrimary`) VALUES (7, 1, TRUE);
INSERT INTO `characterClasses` (`characterId`, `classId`, `isPrimary`) VALUES (1, 24, FALSE);
INSERT INTO `characterClasses` (`characterId`, `classId`, `isPrimary`) VALUES (1, 5, TRUE);
INSERT INTO `characterClasses` (`characterId`, `classId`, `isPrimary`) VALUES (5, 24, FALSE);
INSERT INTO `characterClasses` (`characterId`, `classId`, `isPrimary`) VALUES (5, 25, TRUE);
INSERT INTO `characterClasses` (`characterId`, `classId`, `isPrimary`) VALUES (3, 24, TRUE);
INSERT INTO `characterClasses` (`characterId`, `classId`, `isPrimary`) VALUES (8, 24, TRUE);
INSERT INTO `characterClasses` (`characterId`, `classId`, `isPrimary`) VALUES (9, 24, TRUE);
INSERT INTO `characterClasses` (`characterId`, `classId`, `isPrimary`) VALUES (9, 26, FALSE);
INSERT INTO `characterClasses` (`characterId`, `classId`, `isPrimary`) VALUES (11, 11, FALSE);

INSERT INTO `inventory` (`characterId`, `itemId`, `quantity`) VALUES (3, 16, 1);
INSERT INTO `inventory` (`characterId`, `itemId`, `quantity`) VALUES (11, 21, 1);
INSERT INTO `inventory` (`characterId`, `itemId`, `quantity`) VALUES (11, 4, 1);
INSERT INTO `inventory` (`characterId`, `itemId`, `quantity`) VALUES (4, 8, 5);
INSERT INTO `inventory` (`characterId`, `itemId`, `quantity`) VALUES (4, 3, 1);
INSERT INTO `inventory` (`characterId`, `itemId`, `quantity`) VALUES (4, 6, 2);
INSERT INTO `inventory` (`characterId`, `itemId`, `quantity`) VALUES (4, 7, 1);
INSERT INTO `inventory` (`characterId`, `itemId`, `quantity`) VALUES (4, 10, 3);
INSERT INTO `inventory` (`characterId`, `itemId`, `quantity`) VALUES (4, 20, 1);
INSERT INTO `inventory` (`characterId`, `itemId`, `quantity`) VALUES (5, 22, 1);
INSERT INTO `inventory` (`characterId`, `itemId`, `quantity`) VALUES (5, 15, 1);
INSERT INTO `inventory` (`characterId`, `itemId`, `quantity`) VALUES (5, 11, 1);
INSERT INTO `inventory` (`characterId`, `itemId`, `quantity`) VALUES (6, 23, 1001);
INSERT INTO `inventory` (`characterId`, `itemId`, `quantity`) VALUES (10, 1, 10000);
INSERT INTO `inventory` (`characterId`, `itemId`, `quantity`) VALUES (2, 13, 1);
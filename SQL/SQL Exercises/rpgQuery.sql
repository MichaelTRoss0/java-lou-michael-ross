USE discfinder;

SELECT
    players.playerName,
    characters.firstName,
    characters.lastName,
    races.raceName,
    classes.className
--    characterClasses.isPrimary
    
--    players.playerId,
    
--    characters.characterId,
--    characters.playerId,
    
--    classes.classId,
    
--    races.raceId,

--    characterClasses.characterId,
--    characterClasses.classId,
    
--    characterRaces.characterId,
--    characterRaces.raceId
    
--    items.itemId,
--    items.itemName,
--    items.goldValue,
    
--    inventory.characterId,
--    inventory.itemId,
--    inventory.quantity
    
FROM players
INNER JOIN characters ON players.playerId = characters.playerId
INNER JOIN characterClasses ON characters.characterId = characterClasses.characterId
INNER JOIN characterRaces ON characters.characterId = characterRaces.characterId
INNER JOIN classes ON characterClasses.classId = classes.classId
INNER JOIN races ON characterRaces.raceId = races.raceId
WHERE characterClasses.isPrimary = TRUE AND raceName != "Werewolf";
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.testing.dao.implementations;

import com.tsguild.testing.model.Monster;
import com.tsguild.testing.model.MonsterType;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mike
 */
public class BadMonsterDaoCTest {
    
    BadMonsterDaoC testDAO;
    
    public BadMonsterDaoCTest() {
        testDAO = new BadMonsterDaoC();
    }

    @Test
    public void testAddGetOneMonster() {
        // ARRANGE
        int monsterId = 1;
        Monster thingIn = new Monster("Frosty", MonsterType.YETI, 100, "yellow snow");
        
        // ACT
        Monster thingOut = testDAO.addMonster(monsterId, thingIn);
        Monster outedMonster = testDAO.getMonster(monsterId);
        
        // ASSERT
        Assertions.assertEquals(outedMonster.getName(), thingIn.getName(), "Names should match");
        Assertions.assertEquals(outedMonster.getType(), thingIn.getType(), "Types should match");
        Assertions.assertEquals(outedMonster.getPeopleEaten(), thingIn.getPeopleEaten(), "People Eaten should match");
        Assertions.assertEquals(outedMonster.getFavoriteFood(), thingIn.getFavoriteFood(), "Favorite Food should match");
        
        Assertions.assertNull(thingOut, "There should not have been a monster here");
        
    }
    
    @Test
    public void testAddGetAllMonsters() {
        // ARRANGE
        int monsterId = 1;
        Monster thingIn = new Monster("Frosty", MonsterType.YETI, 100, "yellow snow");
        
        int monsterId2 = 2;
        Monster thingIn2 = new Monster("Goo", MonsterType.SWAMPTHING, 0, "frog");
        
        // ACT
        Monster firstThingOut = testDAO.addMonster(monsterId, thingIn);
        Monster secondThingOut = testDAO.addMonster(monsterId2, thingIn2);
        
        List<Monster> monsterHouse = testDAO.getAllMonsters();
        
        // ASSERT
        Assertions.assertNotNull(monsterHouse, "The list of monsters should not be null");
        Assertions.assertEquals(2, monsterHouse.size(), "There should be 2 monsters in the list");
        Assertions.assertTrue(monsterHouse.contains(thingIn),
                "Monster list should have the first monster stored");
        Assertions.assertTrue(monsterHouse.contains(thingIn2),
                "Monster list should have the second monster stored");
        
        Assertions.assertNull(firstThingOut,
                "There shouldn't be a monster returned when storing in an empty dao");
        Assertions.assertNull(secondThingOut,
                "There shouldn't be a monster returned when storing in the dao");
        
    }
    
    @Test
    public void testAddRemoveMonster() {
        // ARRANGE
        int monsterId = 1;
        Monster thingIn = new Monster("Frosty", MonsterType.YETI, 100, "yellow snow");
        
        // ACT
        testDAO.addMonster(monsterId, thingIn);
        Monster slain = testDAO.removeMonster(monsterId);
        Monster shouldBeNull = testDAO.getMonster(monsterId);
        
        // ASSERT
        Assertions.assertEquals(thingIn, slain, "Stored monster and removed monster should match");
        Assertions.assertNull(shouldBeNull, "Monster should no longer be in DAO");
        
    }
    
    @Test
    public void testAddUpdateMonster() {
        // ARRANGE
        int monsterId = 1;
        Monster firstThing = new Monster("Frosty", MonsterType.YETI, 100, "yellow snow");
        Monster secondThing = new Monster("Goo", MonsterType.SWAMPTHING, 0, "frog");
        
        // ACT
        testDAO.addMonster(monsterId, firstThing);
        testDAO.updateMonster(monsterId, secondThing);
        
        Monster replaced = testDAO.getMonster(monsterId);
        List<Monster> monsterHouse = testDAO.getAllMonsters();
        
        // ASSERT
        Assertions.assertEquals(secondThing, replaced, "Monster should have been replaced by second");
        Assertions.assertEquals(1, monsterHouse.size(), "There should be only one monster");
        Assertions.assertTrue(monsterHouse.contains(secondThing), "Only monster left should be the second one");
        
    }
    
    @Test
    public void emptyDAOTest() {
        // ARRANGE - done in constructor
        
        // ACT
        List<Monster> emptyHouse = testDAO.getAllMonsters();
        
        // ASSERT
        Assertions.assertNotNull(emptyHouse, "Should be empty list, not null");
        Assertions.assertEquals(0, emptyHouse.size(), "Should be an empty list");
        
    }
    
}

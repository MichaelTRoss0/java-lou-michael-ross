/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.statefulunittesting.dao;

import com.tsg.statefulunittesting.dtos.Llama;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author austynhill
 */
public class LlamaDAOInMemTest {
    
    LlamaDAOInMemImpl testDAO;
    
    public LlamaDAOInMemTest() {
        testDAO = new LlamaDAOInMemImpl();
    }

    @Test
    public void addGetOneLlamaTest() {
        // ARRANGE - already know we have a blank DAO
        // we also need a llama, and an id
        String llamaID = "DA FLOOFY ONE";
        Llama toStore = new Llama(llamaID, "Purple", "Purple floofmeister", 12, "Grapes");
        
        // ACT
        Llama gotBack = testDAO.addLlama(llamaID, toStore);
        Llama gottenLlama = testDAO.getLlama(llamaID);
        
        // ASSERT
        // Check that the llama that we tried to store, and the one we got back
        // from the dao after we stored it look identical!!!
        Assertions.assertEquals(gottenLlama.getId(), toStore.getId(), "Llama Ids should match");
        Assertions.assertEquals(gottenLlama.getName(), toStore.getName(), "Llama Names should match");
        Assertions.assertEquals(gottenLlama.getYearsOld(), toStore.getYearsOld(), "Llama Years Old should match");
        Assertions.assertEquals(gottenLlama.getColor(), toStore.getColor(), "Llama Colors should match");
        Assertions.assertEquals(gottenLlama.getFavFood(), toStore.getFavFood(), "Llama Ids should match");
        
        Assertions.assertNull(gotBack, "There shouldn't have been a llama in there, yo.");
        
    }
    
    @Test
    public void addGetAllLlamaTest() {
        // ARRANGE - already know we have a blank DAO
        // we also need a llama, and an id
        String llamaID = "DA FLOOFY ONE";
        Llama toStore = new Llama(llamaID, "Purple", "Purple floofmeister", 12, "Grapes");
        
        String llamaID2 = "DA OTHER ONE";
        Llama toStore2 = new Llama(llamaID2, "Red", "Red Fluffers", 2, "Strawberries");
        
        // ACT
        Llama gotBackFirst = testDAO.addLlama(llamaID, toStore);
        Llama gotBackSecond = testDAO.addLlama(llamaID2, toStore2);
        
        List<Llama> allDaLlamas = testDAO.getAllLlamas();
        
        // ASSERT
        // Check that each llama we got back are null.
        // And then also, that our list is not null, contains 2
        // and those two are both our llamas we stored
        
        Assertions.assertNotNull(allDaLlamas, "our llama list should not be null");
        Assertions.assertEquals(2, allDaLlamas.size(), "there should be 2 llamas in the list.");
        Assertions.assertTrue(allDaLlamas.contains(toStore), 
                "Llama list should have the first llama stored.");
        Assertions.assertTrue(allDaLlamas.contains(toStore2), 
                "Llama list should have the second llama stored.");
        
        Assertions.assertNull(gotBackFirst, "There shouldn't be a llama returned when storing in an empty dao.");
        Assertions.assertNull(gotBackSecond, "There shouldn't be a llama returned when storing in the dao.");
        
        
    }
    
    @Test
    public void addRemoveLlamaTest(){
        // ARRANGE
        // we also need a llama, and an id
        String llamaID = "DA FLOOFY ONE";
        Llama toStore = new Llama(llamaID, "Purple", "Purple floofmeister", 12, "Grapes");
        
        // ACT - add the llama, so you can remove it
        // then check if it's still in there
        testDAO.addLlama(llamaID, toStore);
        Llama culled = testDAO.removeLlama(llamaID);
        Llama shouldBeNullBecauseItWasRemoved = testDAO.getLlama(llamaID);
        
        // ASSERT
        // AS LONG AS YOU HAVE OVERRIDDEN EQUALS YOU CAN SKIP THE PARTS
        // AND GO STRAIGHT TO COMPARING WHOLE OBJECTS
        Assertions.assertEquals(toStore, culled, "Stored llama and removed llama should match");
        Assertions.assertNull(shouldBeNullBecauseItWasRemoved, "Llama should no longer be in DAO");
    }
    
    @Test
    public void addEditLlamaTest(){
        // ARRANGE
        String llamaID = "DA FLOOFY ONE";
        Llama firstLlama = new Llama(llamaID, "Purple", "Purple floofmeister", 12, "Grapes");
        Llama secondLlama = new Llama(llamaID, "Red", "Red Fluffers", 2, "Strawberries");
        
        // ACT
        // first add the llama
        // then replace the llama
        // then get the llama, make sure its the second one
        // and that get all only has one llama, the second
        testDAO.addLlama(llamaID, firstLlama);
        testDAO.editLlama(llamaID, secondLlama);
        
        Llama retrieved = testDAO.getLlama(llamaID);
        List<Llama> allDaLlamas = testDAO.getAllLlamas();
        
        // ASSERT
        // check that retrieved is equal to second
        // check that list is size 1, and has second
        Assertions.assertEquals(secondLlama, retrieved, "Llama should have been replaced by second");
        Assertions.assertEquals(1, allDaLlamas.size(), "There should only be one llama");
        Assertions.assertTrue(allDaLlamas.contains(secondLlama), "Only llama left should be second one.");

    }
    
    @Test
    public void emptyDAOTest(){
        // ARRANGE - done in constructor
        
        // ACT - just check that it's empty
        List<Llama> emptyHerd = testDAO.getAllLlamas();
        
        // ASSERT - prove it
        Assertions.assertNotNull(emptyHerd, "Should be empty list, not null");
        Assertions.assertEquals(0, emptyHerd.size(), "Should be an empty list.");
        
    }
    
}

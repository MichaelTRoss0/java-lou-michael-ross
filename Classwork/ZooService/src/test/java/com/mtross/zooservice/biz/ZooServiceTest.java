/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.zooservice.biz;

import com.mtross.zooservice.biz.stubs.CritterDAOStub;
import com.mtross.zooservice.biz.stubs.HabitatDAOStub;
import com.mtross.zooservice.dtos.Critter;
import com.mtross.zooservice.dtos.Diet;
import com.mtross.zooservice.dtos.Habitat;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mike
 */
public class ZooServiceTest {
    
    ZooServiceImpl testService;
    Critter testCritterOne;
    Critter testCritterTwo;
    Habitat testHabitat;
    
    public ZooServiceTest() {
        testCritterOne
                = new Critter(1, "Bertha", "Mammoth",
                        Diet.HERBIVORE, new BigDecimal("1000.00"));
        
        testCritterTwo
                = new Critter(2, "Chompy", "T-Rex",
                        Diet.CARNIVORE, new BigDecimal("25.00"));
        
        testHabitat = new Habitat(42, 5, new ArrayList<>());
        
        CritterDAOStub cStub = new CritterDAOStub(testCritterOne, testCritterTwo);
        HabitatDAOStub hStub = new HabitatDAOStub(testHabitat);
        testService = new ZooServiceImpl(cStub, hStub);
    }

    @Test
    public void testMovingToHabitatThatDoesntExist() throws Exception {
        // ARRANGE
        int berthaId = 1;
        int chompyId = 2;
        int emptyHabId = 42;
        
        // ACT
        try {
            testService.introduceToHabitat(berthaId, 0);
            // ASSERT
            fail("Whoa. You just let the Mammoth loose. There was no habitat.");
        } catch (NoSuchHabitatException thrownException) {
            String expMsg = thrownException.getMessage();
            Assertions.assertEquals("Couldn't find habitat #0", expMsg);
        }
    }
    
    @Test
    public void testMovingNonExistantCritterToHabitat() throws Exception {
        // ARRANGE
        int berthaId = 1;
        int chompyId = 2;
        int emptyHabId = 42;
        
        // ACT
        try {
            testService.introduceToHabitat(0, emptyHabId);
            // ASSERT
            fail("Whoa. Youtried to flib the books. There was no critter.");
        } catch (NoSuchCritterException thrownException) {
            String expMsg = thrownException.getMessage();
            Assertions.assertEquals("Couldn't find critter #0", expMsg);
        }
    }
    
    @Test
    public void testMovingBerthaIntoEmptyHab() throws Exception {
        // ARRANGE
        Critter bertha = testCritterOne;
        int berthaId = 1;
        int chompyId = 2;
        int emptyHabId = 42;
        
        // ACT
        List<Critter> justBertha = testService.introduceToHabitat(berthaId, emptyHabId);
        
        // ASSERT
        Assertions.assertNotNull(justBertha, "Returned list should have Bertha. Not null.");
        Assertions.assertEquals(1, justBertha.size(), "List should just have Bertha.");
        Assertions.assertTrue(justBertha.contains(bertha));
        // check the hab!
        List<String> critterIds =  testHabitat.getCurrentOccupants();
        Assertions.assertNotNull(critterIds);
        Assertions.assertEquals(1, critterIds.size(), "List should have bertha id only.");
        Assertions.assertTrue(critterIds.contains("1"), "There should be bertha's Id in there.");
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.zooservice.biz;

import com.mtross.zooservice.dao.CritterDAO;
import com.mtross.zooservice.dao.HabitatDAO;
import com.mtross.zooservice.dtos.Critter;
import com.mtross.zooservice.dtos.Diet;
import com.mtross.zooservice.dtos.Habitat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author mike
 */
public class ZooServiceImpl implements ZooService {

    private CritterDAO critterRepo;
    private HabitatDAO habRepo;

    // THIS ALL ARGS CONSTRUCTOR IS TO ENABLE DEPENDENCY INJECTION
    public ZooServiceImpl(CritterDAO cDAO, HabitatDAO hDAO) {
        this.critterRepo = cDAO;
        this.habRepo = hDAO;
    }
    
    @Override
    public List<Critter> getDaCritters() {
        return critterRepo.getAllCritters();
    }

    @Override
    public List<Habitat> getDaHabitats() {
        return habRepo.getAllHabitats();
    }

    @Override
    public Critter getCritter(int id) {
        return critterRepo.getCritter(id);
    }

    @Override
    public Habitat getHabitat(int id) {
        return habRepo.getHabitat(id);
    }

    @Override
    public List<Critter> introduceToHabitat(int critterId, int habitatId)
            throws HabitatIsFullException, CritterWouldGetEatenException,
            NoSuchCritterException, NoSuchHabitatException {
        
        // THIS IS A METHOD TO INTRODUCE A CRITTER TO A HABITAT
        // THE BIZ RULES FOR THIS ARE AS FOLLOWS:
        // - if the habitat doesn't exist, throw an exception
        // - if the critter doesn't exist, throw an exception
        // - check the number currently in there against max occupancy
        // - if moving the critter into a habitat meant too many critters,
        //            throw an exception
        // - if the critter move would result in something getting eaten,
        //            throw an exception
        // otherwise, move the critter into the hab
        //    & return a list of all critters in that hab
        
        // AUSTYN'S SOLUTION
        // get the habitat, check if its real
        Habitat theHab = habRepo.getHabitat(habitatId);
        
        // check if its real
        if (theHab == null) {
            throw new NoSuchHabitatException("Couldn't find habitat #" + habitatId);
        }
        
        // get critter, check if its real
        Critter critter = critterRepo.getCritter(critterId);
        if (critter == null) {
            throw new NoSuchCritterException("Couldn't find critter #" + critterId);
        }
        
        // check whether or not adding one would be too many
        if (this.wouldExceedOccupancy(theHab)) {
            throw new HabitatIsFullException("Habitat #" + habitatId + " is full.");
        }
        
        // check if someone gets eaten
            // get all the critters in the habitat
            // other magic
        List<Critter> crittersInHab = this.getAllCrittersInHab(theHab);
        if (this.someoneWouldGetEaten(critter, crittersInHab)) {
            throw new CritterWouldGetEatenException("This would be dangerous. Don't do it.");
        }
        
        // update the hab w/ the critter id
        List<String> critterIds = theHab.getCurrentOccupants();
//        Integer boxedId = critterId;
//        String idAsString = boxedId.toString();
//        critterIds.add(idAsString);
        critterIds.add(String.valueOf(critterId));
        theHab.setCurrentOccupants(critterIds);
        // make sure its saved
        habRepo.editHabitat(habitatId, theHab);
        
        // add critter to hab critters
        crittersInHab.add(critter);
        // return list
        return crittersInHab;
    }

    private boolean wouldExceedOccupancy(Habitat theHab) {
        // what are the things in the hab
        List<String> habCritterIds = theHab.getCurrentOccupants();
        
        // how many can stay?
        int maxAllowed = theHab.getMaxOccupancy();
        
        // how many are there NOW?
        int currentNumber = habCritterIds.size();
        
        // would +1 exceed limit?
        if (currentNumber + 1 > maxAllowed) return true;
        else return false;
    }

    private List<Critter> getAllCrittersInHab(Habitat theHab) {
        // get the critter ids
        List<String> critterIds = theHab.getCurrentOccupants();
        // make an empty critter list
        List<Critter> crittersInHab = new ArrayList<>();
        
        for (String critterId : critterIds) {
            // fix the id
            int critterNum = Integer.parseInt(critterId);
            // get the critter
            Critter aCritter = critterRepo.getCritter(critterNum);
            // add the critter to the list
            crittersInHab.add(aCritter);
        }
        // return the critter list
        return crittersInHab;
    }
    
    // EAT OR BE EATEN RULES:
    // - same species don't eat eachother
    // - herbivores don't eat any other critters
    // - carnivores eat all others their size or smaller
    // - omnivores eat all others 1/2 their size or smaller
    // - ohmnomnomnivores eat all others if there are 3 of them in the hab
    // - and if it is less than 10x their size

    private boolean someoneWouldGetEaten(Critter critter, List<Critter> crittersInHab) {
        // put everyone in a new list for ease of calculation
        List<Critter> everyoneTogether = new ArrayList<>(crittersInHab);
        everyoneTogether.add(critter);
        
        // check if everyon is the same species
        if (this.areAllSameSpecies(everyoneTogether)) return false;
        
        // check if everyone is an herbivore
        if (this.areAllHerbivores(everyoneTogether)) return false;
        
        // check if anyone is a carnivore
        // check if anyone is an omnivore
        // check if anyone is an ohmnomnomnivore
        
        
        return true;
    }

    private boolean areAllSameSpecies(List<Critter> everyoneTogether) {
        Set<String> critterSpecies = new HashSet<>();
        for (Critter c : everyoneTogether) {
            String species = c.getSpecies();
            critterSpecies.add(species);
        }
        
        if (critterSpecies.size() > 1) return false;
        else return true;
    }

    private boolean areAllHerbivores(List<Critter> everyoneTogether) {
        for (Critter c : everyoneTogether) {
            Diet critterDiet = c.getDiet();
            if (critterDiet != Diet.HERBIVORE) return false;
        }
        return true;
    }
    
    
}

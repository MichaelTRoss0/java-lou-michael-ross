/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.zooservice.biz;

import com.mtross.zooservice.dtos.Critter;
import com.mtross.zooservice.dtos.Habitat;
import java.util.List;

/**
 *
 * @author mike
 */
public interface ZooService {
    
    public List<Critter> getDaCritters();
    public List<Habitat> getDaHabitats();
    
    public Critter getCritter(int id);
    public Habitat getHabitat(int id);
    
    
    // introduce to habitat
    public List<Critter> introduceToHabitat(int critterId, int habitatId)
            throws HabitatIsFullException,
                   CritterWouldGetEatenException,
                   NoSuchCritterException,
                   NoSuchHabitatException;
    
    
}

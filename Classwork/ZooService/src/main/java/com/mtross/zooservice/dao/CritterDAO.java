/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.zooservice.dao;

import com.mtross.zooservice.dtos.Critter;
import java.util.List;

/**
 *
 * @author mike
 */
public interface CritterDAO {
    
    // C - create
    public Critter addCritter(int id, Critter toAdd);
    
    // R - read (getOne)
    public Critter getCritter(int id);
    
    // R - read (getAll)
    public List<Critter> getAllCritters();
    
    // U - update
    public void editCritter(int oldId, Critter toUpdate);
    
    // D - delete
    public Critter removeCritter(int id);
    
}

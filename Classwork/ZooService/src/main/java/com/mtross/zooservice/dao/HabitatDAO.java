/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.zooservice.dao;

import com.mtross.zooservice.dtos.Habitat;
import java.util.List;

/**
 *
 * @author mike
 */
public interface HabitatDAO {
    
    // C - create
    public Habitat addHabitat(int id, Habitat toAdd);
    
    // R - read (getOne)
    public Habitat getHabitat(int id);
    
    // R - read (getAll)
    public List<Habitat> getAllHabitats();
    
    // U - update
    public void editHabitat(int oldId, Habitat toUpdate);
    
    // D - delete
    public Habitat removeHabitat(int id);
    
}

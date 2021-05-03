/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.zooservice.biz.stubs;

import com.mtross.zooservice.dao.HabitatDAO;
import com.mtross.zooservice.dtos.Habitat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mike
 */
public class HabitatDAOStub implements HabitatDAO {
    
    public Habitat theOne;

    public HabitatDAOStub(Habitat theOne) {
        this.theOne = theOne;
    }

    @Override
    public Habitat addHabitat(int id, Habitat toAdd) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Habitat getHabitat(int id) {
        if (id == theOne.getId()) return theOne;
        else return null;
    }

    @Override
    public List<Habitat> getAllHabitats() {
        List<Habitat> theOneAsList = new ArrayList<>();
        theOneAsList.add(theOne);
        return theOneAsList;
    }

    @Override
    public void editHabitat(int oldId, Habitat toUpdate) {
        if (oldId == theOne.getId()) theOne = toUpdate;
    }

    @Override
    public Habitat removeHabitat(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

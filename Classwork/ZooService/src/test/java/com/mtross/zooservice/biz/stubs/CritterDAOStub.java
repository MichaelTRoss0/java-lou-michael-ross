/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.zooservice.biz.stubs;

import com.mtross.zooservice.dao.CritterDAO;
import com.mtross.zooservice.dtos.Critter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mike
 */
public class CritterDAOStub implements CritterDAO {
    
    public Critter alpha;
    public Critter beta;

    public CritterDAOStub(Critter alpha, Critter beta) {
        this.alpha = alpha;
        this.beta = beta;
    }

    @Override
    public Critter addCritter(int id, Critter toAdd) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Critter getCritter(int id) {
        if (id == alpha.getId()) return alpha;
        else if (id == beta.getId()) return beta;
        else return null;
    }

    @Override
    public List<Critter> getAllCritters() {
        List<Critter> theStubCritters = new ArrayList<>();
        theStubCritters.add(alpha);
        theStubCritters.add(beta);
        return theStubCritters;
    }

    @Override
    public void editCritter(int oldId, Critter toUpdate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Critter removeCritter(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

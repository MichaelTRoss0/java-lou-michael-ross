/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.exercises.racer.vehicles;

import com.tsguild.exercises.racer.interfaces.Engine;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author ahill
 */
public class JWBeetle extends Vehicle{
    
    public JWBeetle(Engine anEngine) {
        super(anEngine);
        
        this.changeThruput(new BigDecimal("1.1"));
        this.MAX_TANK_SIZE = new BigDecimal("15.00");
        this.DRIVE_EFFICIENCY  = new BigDecimal("1.1");
        this.PERCENT_CHANCE_OF_FLAT = 7;
    }
    
    @Override
    public String getShortDescription() {
        return "a shiny "+this.color+" beetle w/ a " + this.engine.getClass().getSimpleName() + " engine";
    }
    
}

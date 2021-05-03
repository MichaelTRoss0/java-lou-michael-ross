/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.exercises.racer.vehicles;

import com.tsguild.exercises.racer.interfaces.Engine;
import java.math.BigDecimal;

/**
 *
 * @author ahill
 */
public class DigitalPorsche extends Vehicle{
    
    public DigitalPorsche(Engine anEngine) {
        super(anEngine);
        
        this.changeThruput(new BigDecimal("1.5"));
        this.MAX_TANK_SIZE = new BigDecimal("12.00");
        this.DRIVE_EFFICIENCY  = new BigDecimal("2.5");
        this.PERCENT_CHANCE_OF_FLAT = 10;
    }
    
    @Override
    public String getShortDescription() {
        return "a zippy "+this.color+" digital porshe w/ a " + this.engine.getClass().getSimpleName() + " engine";
    }
    

    
}

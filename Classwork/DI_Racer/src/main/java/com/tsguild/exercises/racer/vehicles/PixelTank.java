/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.exercises.racer.vehicles;

import com.tsguild.exercises.racer.engines.exceptions.EngineException;
import com.tsguild.exercises.racer.interfaces.Engine;
import com.tsguild.exercises.racer.vehicles.exceptions.VehicleException;
import java.math.BigDecimal;

/**
 *
 * @author ahill
 */
public class PixelTank extends Vehicle {

    public PixelTank(Engine anEngine) {
        super(anEngine);
        this.changeThruput(new BigDecimal("2.5"));
        this.MAX_TANK_SIZE = new BigDecimal("100");
        this.DRIVE_EFFICIENCY = new BigDecimal(".3");
        this.PERCENT_CHANCE_OF_FLAT = 0; // ITS A TANK
    }

    @Override
    public String getShortDescription() {
        return "a rugged "+this.color+" pixel tank w/ a " + this.engine.getClass().getSimpleName() + " engine";
    }

}

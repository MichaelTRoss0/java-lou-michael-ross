/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.exercises.racer.helpers;

import com.tsguild.exercises.racer.interfaces.Driveable;
import com.tsguild.exercises.racer.vehicles.Vehicle;
import java.math.BigDecimal;

/**
 *
 * @author ahill
 */
public class Mechanic {

    public boolean tryToFix(Driveable aRacer) {
        if (aRacer != null && aRacer instanceof Vehicle) {
            Vehicle downedCar = (Vehicle)aRacer;
            // Check Gas, and if empty - fill it!
            if(downedCar.readGasGauge().compareTo(BigDecimal.ZERO) <= 0){
                System.out.println("Looks like " + aRacer.getIdentifier() + " might just be out of gas...");
                System.out.println("Mechanic gets to work & fills " + aRacer.getIdentifier() + " w/ 15 more gallons!");
                downedCar.setGasTank("15");
                
            } else{
                System.out.println("Looks like " + aRacer.getIdentifier() + " might have had a tire, or engine issue.");
                System.out.println("The Mechanic gets to work & might just have fixed it!");
                
            }
            
            downedCar.fix();
            return true;
        } else{
            return false;
        }
    }

}

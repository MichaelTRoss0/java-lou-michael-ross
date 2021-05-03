/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.exercises.racer.vehicles;

import com.tsguild.exercises.racer.interfaces.Driveable;
import com.tsguild.exercises.racer.vehicles.exceptions.VehicleException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 *
 * @author ahill
 */
public class HamsterBall implements Driveable{

    Random r = new Random();
    BigDecimal odometer = BigDecimal.ZERO;
    
    public HamsterBall(){ }
    
    @Override // There's not a lot of logic to a Hamsterball...
    public BigDecimal drive() throws VehicleException {
        BigDecimal miles = new BigDecimal(.5 + r.nextDouble() * 4).setScale(2, RoundingMode.HALF_DOWN);
        odometer = odometer.add(miles);
        return miles;
    }
    
    public BigDecimal readOdometer() {
        return odometer;
    }

    @Override
    public String getShortDescription() {
        return "a Roly Poly Hamster Ball";
    }
    
    @Override
    public String getIdentifier() {
        return "Hamster Ball";
    }

    @Override
    public String getName() {
        return "Hamster Ball";
    }
    
    // NEVER BREAKS!
    public boolean isBrokenDown(){
        return false;
    }
    
    // We don't do this.
    public void breakDown(){ }
    public void fix(){ }
    
    @Override
    public int compareTo(Object o) {
        if(o instanceof Driveable){
            Driveable anotherRacer = (Driveable)o;
            return anotherRacer.readOdometer().compareTo(this.readOdometer());
        } else{
            throw new IllegalArgumentException("Can't compare anything except racers.");
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.exercises.racer.interfaces;

import com.tsguild.exercises.racer.vehicles.exceptions.VehicleException;
import java.math.BigDecimal;


/**
 *
 * @author ahill
 */
public interface Driveable extends Comparable {
    
    public BigDecimal drive() throws VehicleException;
    
    public BigDecimal readOdometer();
    
    public String getShortDescription();
    public String getIdentifier();
    public String getName();

    
    public boolean isBrokenDown();
    public void breakDown();
    public void fix();
    
}

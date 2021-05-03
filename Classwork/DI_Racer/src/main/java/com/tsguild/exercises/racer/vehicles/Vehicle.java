/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.exercises.racer.vehicles;

import com.tsguild.exercises.racer.engines.exceptions.EngineException;
import com.tsguild.exercises.racer.interfaces.Driveable;
import com.tsguild.exercises.racer.interfaces.Engine;
import com.tsguild.exercises.racer.vehicles.exceptions.FlatTireException;
import com.tsguild.exercises.racer.vehicles.exceptions.OutOfGasException;
import com.tsguild.exercises.racer.vehicles.exceptions.StalledOutException;
import com.tsguild.exercises.racer.vehicles.exceptions.VehicleException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 *
 * @author ahill
 */
public class Vehicle implements Driveable {

    protected Random r = new Random();

    protected String color = "grey";
    protected Engine engine;
    protected BigDecimal odometer = BigDecimal.ZERO;
    protected BigDecimal gasTank = BigDecimal.ZERO; // in gallons;
    protected boolean isBrokenDown = false;

    protected BigDecimal DRIVE_EFFICIENCY = new BigDecimal(5);
    protected BigDecimal GAS_THRUPUT = new BigDecimal(1);
    protected BigDecimal MAX_TANK_SIZE = new BigDecimal(50);

    protected int PERCENT_CHANCE_OF_FLAT = 10;

    public Vehicle(Engine anEngine) {
        this.engine = anEngine;
        this.color = this.getRandomColor();
    }

    public void setGasTank(String moreGas) {
        gasTank = new BigDecimal(moreGas);
        if (gasTank.compareTo(MAX_TANK_SIZE) > 0) {
            gasTank = MAX_TANK_SIZE;
        }
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public BigDecimal drive() throws VehicleException {
        BigDecimal milesTraveled = BigDecimal.ZERO;
        if (this.isBrokenDown()) {
            throw new VehicleException("We're broken down!");
        }

        try {
            BigDecimal gas = this.getGasFromTank();
            BigDecimal kwOfPower = this.engine.generateKWPower(gas);
            milesTraveled = this.convertPowerToMiles(gas);
            odometer = odometer.add(milesTraveled);
            this.possibleFlatTire();
        } catch (EngineException ex) {
            throw new VehicleException("Something's wrong with the Engine!", ex);
        }
        return milesTraveled;
    }

    protected BigDecimal getGasFromTank() throws OutOfGasException {
        BigDecimal gas;
        if (gasTank.compareTo(BigDecimal.ZERO) < 1) {
            // We're outta gas
            throw new OutOfGasException("No power, cap'n! We can't go any farther ...!");
        } else if (gasTank.compareTo(GAS_THRUPUT) < 1) {
            // Not a lot of gas, but a bit
            gas = gasTank;
            gasTank = BigDecimal.ZERO;
        } else {
            // apparently plenty of gas!
            gas = GAS_THRUPUT;
            gasTank = gasTank.subtract(GAS_THRUPUT);
        }

        return gas;
    }

    protected BigDecimal convertPowerToMiles(BigDecimal kw) throws StalledOutException {
        if (kw.compareTo(BigDecimal.ZERO) < 1) {
            throw new StalledOutException("No power, cap'n! We can't go any farther ...!");
        } else {
            return kw.multiply(this.getMilesPerKWHour()).setScale(1, RoundingMode.HALF_DOWN);
        }
    }

    protected void possibleFlatTire() throws FlatTireException {
        int diceRoll = r.nextInt(100);
        if (diceRoll < PERCENT_CHANCE_OF_FLAT) {
            this.breakDown();
            throw new FlatTireException("OH NO! The " + this.getIdentifier() + " has THROWN A SHOE!");
        }
    }

    // Helper functions
    public String getEngineInfo() {
        return engine.getClass().getSimpleName();
    }

    public BigDecimal readOdometer() {
        return odometer;
    }

    public BigDecimal readGasGauge() {
        return gasTank;
    }

    final protected void changeThruput(BigDecimal multiplier) {
        this.GAS_THRUPUT = this.GAS_THRUPUT.multiply(multiplier).setScale(0, RoundingMode.HALF_DOWN);
    }

    final protected void changeDriveEfficiency(BigDecimal multiplier) {
        this.GAS_THRUPUT = this.DRIVE_EFFICIENCY.multiply(multiplier).setScale(0, RoundingMode.HALF_DOWN);
    }

    public BigDecimal getMilesPerKWHour() {
        return this.GAS_THRUPUT.multiply(this.DRIVE_EFFICIENCY).setScale(0, RoundingMode.HALF_DOWN);
    }

    public boolean isBrokenDown() {
        return this.isBrokenDown;
    }

    public void breakDown() {
        this.isBrokenDown = true;
    }

    public void fix() {
        this.isBrokenDown = false;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Driveable) {
            Driveable anotherRacer = (Driveable) o;
            return anotherRacer.readOdometer().compareTo(this.readOdometer());
        } else {
            throw new IllegalArgumentException("Can't compare anything except racers.");
        }
    }

    
    @Override
    public String getShortDescription() {
        return "a plain ol' car w/ a " + this.engine.getClass().getSimpleName() + " engine";
    }

    @Override
    public String getIdentifier() {
        return this.color + " " + this.getName();
    }
    
    @Override
    public String getName(){
        if (this.getClass().equals(Vehicle.class)) {
            return "Car";
        }
        return this.getClass().getSimpleName();   
    }
    
    private String getRandomColor() {
        switch ((int) (Math.random() * 10)) {
            case 0:
                return "Purple";
            case 1:
                return "Lime Green";
            case 2:
                return "Neon Orange";
            case 3:
                return "Lilac";
            case 4:
                return "Butter-Yellow";
            case 5:
                return "Amethyst";
            case 6:
                return "Glitter Confetti";
            case 7:
                return "Tiger Striped";
            case 8:
                return "Polka-dot";
            case 9:
                return "Sky-blue";
            default:
                return "Red";
        }
    }

}

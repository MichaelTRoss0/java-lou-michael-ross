/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.exercises.racer.vehicles.exceptions;

/**
 *
 * @author ahill
 */
public class VehicleException extends Exception{

    public VehicleException(String message) {
        super(message);
    }

    public VehicleException(String message, Throwable cause) {
        super(message, cause);
    }
    
}

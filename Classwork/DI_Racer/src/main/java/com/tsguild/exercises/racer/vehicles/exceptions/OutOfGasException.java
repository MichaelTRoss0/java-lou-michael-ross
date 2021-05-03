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
public class OutOfGasException extends VehicleException{
    
    public OutOfGasException(String message) {
        super(message);
    }
}

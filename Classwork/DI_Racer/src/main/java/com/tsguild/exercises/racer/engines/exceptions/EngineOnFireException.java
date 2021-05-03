/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.exercises.racer.engines.exceptions;

/**
 *
 * @author ahill
 */
public class EngineOnFireException extends EngineException{

    public EngineOnFireException(String message) {
        super(message);
    }

    public EngineOnFireException(String message, Throwable cause) {
        super(message, cause);
    }
    
}

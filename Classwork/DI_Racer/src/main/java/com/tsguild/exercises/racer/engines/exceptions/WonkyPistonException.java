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
public class WonkyPistonException extends EngineException{

    public WonkyPistonException(String message) {
        super(message);
    }

    public WonkyPistonException(String message, Throwable cause) {
        super(message, cause);
    }
    
}

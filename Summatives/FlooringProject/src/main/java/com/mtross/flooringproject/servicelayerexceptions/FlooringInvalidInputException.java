/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.flooringproject.servicelayerexceptions;

/**
 *
 * @author mike
 */
public class FlooringInvalidInputException extends Exception {

    public FlooringInvalidInputException() {
    }

    public FlooringInvalidInputException(String message) {
        super(message);
    }

    public FlooringInvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public FlooringInvalidInputException(Throwable cause) {
        super(cause);
    }

    public FlooringInvalidInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}

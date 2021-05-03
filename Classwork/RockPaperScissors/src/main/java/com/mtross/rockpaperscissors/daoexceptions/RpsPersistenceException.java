/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.rockpaperscissors.daoexceptions;

/**
 *
 * @author mike
 */
public class RpsPersistenceException extends Exception {

    public RpsPersistenceException() {
    }

    public RpsPersistenceException(String message) {
        super(message);
    }

    public RpsPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpsPersistenceException(Throwable cause) {
        super(cause);
    }

    public RpsPersistenceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

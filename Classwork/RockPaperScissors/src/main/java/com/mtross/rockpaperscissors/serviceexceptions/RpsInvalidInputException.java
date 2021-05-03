/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.rockpaperscissors.serviceexceptions;

/**
 *
 * @author mike
 */
public class RpsInvalidInputException extends Exception {

    public RpsInvalidInputException() {
    }

    public RpsInvalidInputException(String message) {
        super(message);
    }

    public RpsInvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpsInvalidInputException(Throwable cause) {
        super(cause);
    }

    public RpsInvalidInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

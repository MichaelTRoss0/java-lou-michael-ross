/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.zooservice.biz;

/**
 *
 * @author mike
 */
public class NoSuchHabitatException extends Exception {

    public NoSuchHabitatException() {
    }

    public NoSuchHabitatException(String message) {
        super(message);
    }

    public NoSuchHabitatException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchHabitatException(Throwable cause) {
        super(cause);
    }

    public NoSuchHabitatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    
    
}

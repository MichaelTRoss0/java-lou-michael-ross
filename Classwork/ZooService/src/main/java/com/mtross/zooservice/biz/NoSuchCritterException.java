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
public class NoSuchCritterException extends Exception {

    public NoSuchCritterException() {
    }

    public NoSuchCritterException(String message) {
        super(message);
    }

    public NoSuchCritterException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchCritterException(Throwable cause) {
        super(cause);
    }

    public NoSuchCritterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    
    
}

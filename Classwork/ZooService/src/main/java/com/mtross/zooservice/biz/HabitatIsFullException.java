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
public class HabitatIsFullException extends Exception {

    public HabitatIsFullException() {
    }

    public HabitatIsFullException(String message) {
        super(message);
    }

    public HabitatIsFullException(String message, Throwable cause) {
        super(message, cause);
    }

    public HabitatIsFullException(Throwable cause) {
        super(cause);
    }

    public HabitatIsFullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    
    
}

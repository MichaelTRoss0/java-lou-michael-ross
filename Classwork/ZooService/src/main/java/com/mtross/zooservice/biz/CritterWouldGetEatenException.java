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
public class CritterWouldGetEatenException extends Exception {

    public CritterWouldGetEatenException() {
    }

    public CritterWouldGetEatenException(String message) {
        super(message);
    }

    public CritterWouldGetEatenException(String message, Throwable cause) {
        super(message, cause);
    }

    public CritterWouldGetEatenException(Throwable cause) {
        super(cause);
    }

    public CritterWouldGetEatenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
    
    
}

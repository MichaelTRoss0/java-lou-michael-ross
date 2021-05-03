/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.statefulunittesting.dao;

/**
 *
 * @author mike
 */
public class LlamaDAOException extends Exception {

    public LlamaDAOException() {
    }

    public LlamaDAOException(String message) {
        super(message);
    }

    public LlamaDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public LlamaDAOException(Throwable cause) {
        super(cause);
    }

    public LlamaDAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}

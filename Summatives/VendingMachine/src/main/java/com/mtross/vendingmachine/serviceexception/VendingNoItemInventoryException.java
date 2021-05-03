/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.vendingmachine.serviceexception;

/**
 *
 * @author mike
 */
public class VendingNoItemInventoryException extends Exception {

    public VendingNoItemInventoryException(String message) {
        super(message);
    }

    public VendingNoItemInventoryException(String message, Throwable cause) {
        super(message, cause);
    }
    
}

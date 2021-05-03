/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdklueber.gumball.model;

/**
 *
 * @author jason
 */
public class GumballMachine {
    public static final int READY_FOR_COIN = 0;
    public static final int HAS_COIN = 1;
    
    public static final int SUCCESS = 1;
    public static final int FAILURE = 0;
	
	private boolean coinAdded;
	
    public GumballMachine() {
		coinAdded = false;
    }

	public boolean isCoinAdded() {
		return coinAdded;
	}

	public void setCoinAdded(boolean coinAdded) {
		this.coinAdded = coinAdded;
	}
    
    /* 
    *  Should return READY_FOR_COIN if the machine is ready for a coin
    *  Should return HAS_COIN if the machine has had a coin added
    */
    public int getState() {
        if (isCoinAdded() == true){
			return HAS_COIN;
		} else {
			return READY_FOR_COIN;
		}
    }
    
    /*
    *  Should return SUCCESS if the machine is ready for a coin
    *  Should return FAILURE otherwise
    */
    public int addCoin() {
        if (isCoinAdded() == true){
			return FAILURE;
		} else {
			setCoinAdded(true);
			return SUCCESS;
		}
    }
    
    /* Should print "Have a gumball!" and return SUCCESS if the machine has a coin
    *  Should return FAILURE otherwise
    */
    public int turnHandle() {
        if (isCoinAdded() == true){
			setCoinAdded(false);
			return SUCCESS;
		} else {
			return FAILURE;
		}
    }
}

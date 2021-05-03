/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.gumballmachine;

/**
 *
 * @author mike
 */
public class GumballMachine {
	private int treats;
	private boolean coinInserted;
	private int coinsEaten;
	
	public GumballMachine() {
		treats = 100;
		coinInserted = false;
		coinsEaten = 0;
	}

	public int getTreats() {
		return treats;
	}

	public boolean isCoinInserted() {
		return coinInserted;
	}

	public int getCoinsEaten() {
		return coinsEaten;
	}
	
	
}

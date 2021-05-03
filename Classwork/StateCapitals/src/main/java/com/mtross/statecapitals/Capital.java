/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.statecapitals;

/**
 *
 * @author mike
 */
public class Capital {
	private final String NAME;
	private final int POPULATION;
	private final double SQUAREMILEAGE;
	
	public Capital (String name, int population, double squareMileage) {
		this.NAME = name;
		this.POPULATION = population;
		this.SQUAREMILEAGE = squareMileage;
	}

	public String getName() {
		return NAME;
	}

	public int getPopulation() {
		return POPULATION;
	}

	public double getSquareMileage() {
		return SQUAREMILEAGE;
	}
	
}

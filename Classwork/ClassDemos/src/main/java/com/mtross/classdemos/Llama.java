/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.classdemos;

import java.util.Random;

/**
 *
 * @author mike
 */
public class Llama {
	
	private String name;
	private String woolColor;
	private String woolType;
	private int ageYears;
	private double heightInches;

	public Llama(String name, String woolColor,
			String woolType, int years,
			double heightInches) {
		this.name = name;
		this.woolColor = woolColor;
		this.woolType = woolType;
		this.ageYears = years;
		this.heightInches = heightInches;
	}
	
	public void spit() {
		// say PTOOEY x a 1+age upper bound randomly generated number
		Random rGen = new Random();
		int numSpits = rGen.nextInt(ageYears) + 1;
		
		for (int i = 0; i < numSpits; i++) {
			System.out.println("PTOOOOOOOOEEEEEY!!!");
		}
	}
	
	// Getters and Setters
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getWoolColor() {
		return this.woolColor;
	}
	public void setWoolColor(String woolColor) {
		this.woolColor = woolColor;
	}

	public String getWoolType() {
		return this.woolType;
	}
	public void setWoolType(String woolType) {
		this.woolType = woolType;
	}

	public int getAgeYears() {
		return this.ageYears;
	}
	public void setAgeYears(int ageYears) {
		this.ageYears = ageYears;
	}

	public double getHeightInches() {
		return this.heightInches;
	}
	public void setHeightInches(int heightInches) {
		this.heightInches = heightInches;
	}
	
}

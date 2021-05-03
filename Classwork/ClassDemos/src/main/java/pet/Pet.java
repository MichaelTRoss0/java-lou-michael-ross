/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pet;

/**
 *
 * @author mike
 */
public class Pet {
	// Properties
	private String name;
	private String owner;

	// Constructors
	public Pet(String name, String owner) {
		this.name = name;
		this.owner = owner;
	}
	public Pet() {
	}
	
	// Getters/Setters
	public String getName() {
		return this.name;
	}
	public void setName(String aName) {
		this.name = aName;
	}
	
	public String getOwner() {
		return this.owner;
	}
	public void setOwner(String anOwner) {
		this.owner = anOwner;
	}
}

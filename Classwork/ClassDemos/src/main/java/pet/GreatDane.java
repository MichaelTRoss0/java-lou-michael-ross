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
public class GreatDane extends Dog {
	
	private String favCouch;
	
	public GreatDane(String aName, String anOwner, String favCouch) {
		super("Great Dane", aName, anOwner);
		this.favCouch = favCouch;
	}
	
	public void beRegal() {
		System.out.println("*POSE*");
	}
	
	public void bark() {
		System.out.println("BOOF!!");
	}
	
}

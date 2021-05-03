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
public class Chihuahua extends Dog {
	
	private String favPillow;
	
	public Chihuahua(String favPillow, String breed, String name, String owner) {
		super(breed, name, owner);
		this.favPillow = favPillow;
	}
	
	public void beYippy() {
		System.out.println("YIP YIP YIP!!!");
	}
	
	@Override
	public String getName() {
		return "The Great " + super.getName() + " the XXX";
	}
	
}

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
public class Dog extends Pet
	implements Snuggleable{
	private String breed;

	public Dog(String breed, String aName, String anOwner) {
		super(aName, anOwner);
		this.breed = breed;
	}
	
	public void bark() {
		System.out.println("Woof!");
	}
	
	public String getBreed() {
		return breed;
	}
	
	public void setBreed(String breed) {
		this.breed = breed;
	}

	@Override
	public void beSnuggled() {
		System.out.println("*snugglesnugglesnuggle*");
		this.bark();
		System.out.println("*snugglesnugglesnuggle*");
	}
}

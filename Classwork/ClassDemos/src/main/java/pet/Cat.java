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
public class Cat extends Pet
	implements Snuggleable{
	
	private String favFood;
	
	public Cat(String aFavFood, String aName, String anOwner) {
		// new Pet(aName, anOwner);
		super(aName, anOwner);
		this.favFood = aFavFood;
	}
	
	public void meow() {
		System.out.println("Nyaaa~~~");
	}

	public String getFavFood() {
		return favFood;
	}

	public void setFavFood(String aFavFood) {
		this.favFood = aFavFood;
	}

	@Override
	public void beSnuggled() {
		System.out.println("*snugglesnugglesnuggle*");
		this.meow();
		System.out.println("*snugglesnugglesnuggle*");
	}
	
}

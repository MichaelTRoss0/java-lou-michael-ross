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
public class PetPark {
	public static void main(String[] args) {
		System.out.println("Welcome to the Pet Park!");
		
		Pet aGenericPet = new Pet("Blah", "Boring Joe");
		Cat darky = new Cat("Tuna", "Darky", "Claire");
		Iguana titan = new Iguana();
		
		Dog someDog = new Dog("Not Sure", "Fido", "Me");
		Chihuahua kingCharles = new Chihuahua("Silk Embroidered", "Best Breed", "King Charles", "Queen Victoria III");
		GreatDane beefCake = new GreatDane("BeefCake", "Me", "Red Tartan");
		
		Pet[] allDaPets = new Pet[6];
		allDaPets[0] = aGenericPet;
		allDaPets[1] = darky;
		allDaPets[2] = titan;
		allDaPets[3] = someDog;
		allDaPets[4] = kingCharles;
		allDaPets[5] = beefCake;
		
		for (Pet aPet : allDaPets) {
			// print out name & owner
			System.out.println(aPet.getName() + " is owned by " + aPet.getOwner() + ".");
		}
		
		System.out.println("Gonna snuggle Darky");
		PetPark.snugglePet(darky);
		System.out.println("Gonna snuggle some dog");
		PetPark.snugglePet(someDog);
		System.out.println("Gonna snuggle King Charles");
		PetPark.snugglePet(kingCharles);
		System.out.println("Gonna snuggle BeefCake");
		PetPark.snugglePet(beefCake);
		
		
		
		
	}
	
	public static void snugglePet(Snuggleable toSnuggle) {
		toSnuggle.beSnuggled();
		toSnuggle.beSnuggled();
		toSnuggle.beSnuggled();
	}
}

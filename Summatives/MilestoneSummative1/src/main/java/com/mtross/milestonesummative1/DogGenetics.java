/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.milestonesummative1;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author mike
 */
public class DogGenetics {
	public static void main(String[] args) {
		// Declare all the variables
		Scanner sc = new Scanner(System.in);
		String name;
		int breed[] = new int[5];
		int breedTotal = 4;	// Start with 4% total, so that generated percentages aren't too big and cause problems
		
		// Ask the user for the name of their dog
		System.out.print("What is your dog's name? ");
		name = sc.nextLine();
		
		// Randomly generate how much of one breed the dog is - between 1 and 96 (because all of the breeds must be at least 1% present)
		breed[0] = randPercent(breedTotal);
		breedTotal += breed[0];
		breedTotal--;	// "Free up" some of the total percentage for later use
		
		// Randomly generate how much of a second breed the dog is - between 1 and 96 minus the first percentage
		breed[1] = randPercent(breedTotal);
		breedTotal += breed[1];
		breedTotal--;	// "Free up" some of the total percentage for later use
		System.out.println("TRACE: breed[1] is: " + breed[1]);
		System.out.println("TRACE: breedTotal is: " + breedTotal);
		
		// Randomly generate how much of a third breed the dog is - between 1 and 96 minus the other percentages
		breed[2] = randPercent(breedTotal);
		breedTotal += breed[2];
		breedTotal--;	// "Free up" some of the total percentage for later use
		System.out.println("TRACE: breed[2] is: " + breed[2]);
		System.out.println("TRACE: breedTotal is: " + breedTotal);
		
		// Randomly generate how much of a fourth breed the dog is - between 1 and 96 minus the other percentages
		breed[3] = randPercent(breedTotal);
		breedTotal += breed[3];
		breedTotal--;	// "Free up" some of the total percentage for later use

		
		// DO NOT randomly generate how much of a fifth breed the dog is - this final percentage is equal to 100 minus the other percentages
		breed[4] = 100 - breedTotal;
		breedTotal += breed[4];
		
		if (breedTotal != 100) {
			System.out.println("Something has gone terribly wrong.");
			System.out.println("breedTotal = " + breedTotal);
			System.exit(0);
		}
		
		// Confidently print out the results
		System.out.println("Well then, I have this highly reliable report on " + name + "'s prestigious background right here.");
		
		System.out.println("\n" + name + " is:\n");
		
		System.out.println(breed[0] + "%\tSt. Bernard");
		System.out.println(breed[1] + "%\tChihuahua");
		System.out.println(breed[2] + "%\tJack Russel");
		System.out.println(breed[3] + "%\tCommon Cur");
		System.out.println(breed[4] + "%\tKing Doberman");
		
		System.out.println("\nWow, that's QUITE the dog!");
	}
	
	private static int randPercent(int x) {
		Random rGen = new Random();
		int result = rGen.nextInt(100 - x) + 1;
		return result;
	}
}

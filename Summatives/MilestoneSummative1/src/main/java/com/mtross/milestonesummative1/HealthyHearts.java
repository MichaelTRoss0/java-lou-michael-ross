/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.milestonesummative1;

import java.util.Scanner;

/**
 *
 * @author mike
 */
public class HealthyHearts {
	public static void main(String[] args) {
		// Declare the variables
		Scanner sc = new Scanner(System.in);
		String input;
		int age = 0;
		int max;
		int zoneLower;
		int zoneUpper;

		// Ask the user for their age
		System.out.print("What is your age? ");
		boolean goodInput = false;
		do {
			if (sc.hasNextInt()) {
				age = sc.nextInt();
				goodInput = true;
			} else {
				input = sc.nextLine();
				System.out.print("Please enter a whole number with no decimals: ");
			}
		} while (!goodInput);
		
		// Calculate maximum heart rate (220 - age)
		max = 220 - age;
		
		// Calculate target heart rate zone (50-85% of max)
		zoneLower = max * 50 / 100;
		zoneUpper = max * 85 / 100;

		// Display the maximum heart rate and target HR zone (in beats per minute)
		System.out.println("Your maximum heart rate should be " + max + " beats per minute");
		System.out.println("Your target HR Zone is " + zoneLower + " - " + zoneUpper + " beats per minute");
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.interestcalculator;

import java.util.Scanner;

/**
 *
 * @author mike
 */
public class InterestCalculator {
	public static void main(String[] args) {
		// Declare variables
		Scanner sc = new Scanner(System.in);
		boolean goodInput;
		double rateA = 0.0;
		double currentPrincipal =0.0;
		int years = 0;
		double rateQ;
		double startPrincipal;
		double interest;
		
		// Prompt user for:
		// 1) Annual Interest Rate,
		// 2) Initial Amount of Principal, and
		// 3) The Number of Years the Money is to Stay in the Fund
		System.out.print("What is your annual interest rate? ");
		goodInput = false;
		do {
			if (sc.hasNextDouble()) {
				rateA = sc.nextDouble();
				goodInput = true;
			} else {
				sc.nextLine();
				System.out.print("Input must be a number: ");
			}
		} while (goodInput == false);
		
		System.out.print("\nWhat is your initial principal? ");
		goodInput = false;
		do {
			if (sc.hasNextDouble()) {
				currentPrincipal = sc.nextDouble();
				goodInput = true;
			} else {
				sc.nextLine();
				System.out.print("Input must be a number: ");
			}
		} while (goodInput == false);
		
		System.out.print("\nHow long will the money stay in the fund? ");
		goodInput = false;
		do {
			if (sc.hasNextInt()) {
				years = sc.nextInt();
				if (years > 0) {
					goodInput = true;
				} else {
					sc.nextLine();
					System.out.print("Input must be a positive whole number: ");
				}
			} else {
				sc.nextLine();
				System.out.print("Input must be a positive whole number: ");
			}
		} while (goodInput == false);
		
		// Calculate the Quarterly Rate from the Annual Rate
		rateQ = rateA / 4.0;
		
		for (int i = 1; i <= years; i++) {	// Beginning of the yearly calculations
			// Store the principal at the start of the year in a seperate variable
			startPrincipal = currentPrincipal;

			// Calculate the principal at the end of the year by calculating the new amount each quarter four times
			for (int j = 0; j < 4; j++) {
				currentPrincipal = currentPrincipal * (1.0 + (rateQ / 100.0));
			}

			// Calculate the interest earned during the year in a seperate variable
			interest = currentPrincipal - startPrincipal;

			// Print out the following:
			// The Year Number (i)
			// The principal at the beginning of the year (startPrincipal)
			// The total amount of interest earned for the year (interest)
			// The principal at the end of the year (currentPrincipal)
			System.out.println("\nCurrent year:\t\t\t" + i);
			System.out.println("This year's starting principal:\t$" + startPrincipal);
			System.out.println("Interest earned this year:\t$" + interest);
			System.out.println("Principal at year's end:\t$" + currentPrincipal);

			// Gone through all the years?
			// No -> Calculate interest for another year
			// Yes -> END
			
		}	// End of the yearly calculations
	}
}

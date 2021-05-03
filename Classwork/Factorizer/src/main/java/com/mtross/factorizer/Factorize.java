/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.factorizer;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author mike
 */
public class Factorize {
	public static void main(String[] args) {
		int num = 0;
		int numFactors;
		int[] factor;
		
		Scanner sc = new  Scanner(System.in);
		String input;
		
		// Factor a user-entered number
		boolean goodInput = false;
		do {
			System.out.print("Please enter in a whole number: ");
			if (sc.hasNextInt()) {
				num = sc.nextInt();
				goodInput = true;
			} else {
				System.out.print("Input must be a whole number.");
			}
		} while (goodInput == false);
		
//		num = 28;
		
		// Print the original number
		System.out.println("\n\nYou entered: " + num);
		
		// Print number of factors
		numFactors = countFactors(num);
		System.out.println(num + " has " + numFactors + " factors.");
		
		// Print factors
		factor = findFactors(num, numFactors);
		System.out.println("The factors of " + num + " are: ");
		for (int i : factor) {
			System.out.println(i);
		}
		
		// Is number perfect?
		System.out.print("Is " + num + " a perfect number? ");
		
		if (num == factorSum(factor)) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
		
		// Is number prime?
		System.out.print("Is " + num + " a prime number? ");
		if (factor.length == 2) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}

	private static int countFactors(int num) {
		int result = 0;
		int div = 1;
		
		do {
			if ((num % div) == 0) {
				result++;
			}
			div++;
		} while(div <= num);
		
		return result;
	}

	private static int[] findFactors(int num, int length) {
		int[] result = new int[length];
		int div = 1;
		int index = 0;
		
		do {
			if ((num % div) == 0) {
				result[index] = div;
				index++;
			}
			div++;
		} while(div <= num);
		
		return result;
	}

	private static int factorSum(int[] factor) {
		int result = 0;
		
//		for (int num : factor) {
//			result += num;
//		}

		for (int i = 0; i < factor.length - 1; i++) {
			result += factor[i];
		}
		
		return result;
	}
}

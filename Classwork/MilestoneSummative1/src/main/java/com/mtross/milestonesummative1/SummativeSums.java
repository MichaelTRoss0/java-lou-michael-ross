/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.milestonesummative1;

/**
 *
 * @author mike
 */
public class SummativeSums {
	public static void main(String[] args) {
		// Declare three arrays of ints in the main method
		int[] array1 = { 1, 90, -33, -55, 67, -16, 28, -55, 15 };
		int[] array2 = { 999, -60, -77, 14, 160, 301 };
		int[] array3 = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, -99 } ;
		
		// For each of the arrays, use another static method to calculate the sum of all the ints. Store the results in seperate variables
		int sum1 = calcSum(array1);
		int sum2 = calcSum(array2);
		int sum3 = calcSum(array3);
		
		// Print out the results in the main method
		System.out.println("#1 Array Sum: " + sum1);
		System.out.println("#2 Array Sum: " + sum2);
		System.out.println("#3 Array Sum: " + sum3);
	}
	private static int calcSum(int num[]) {	// The static method uses an enhanced for loop to to go through each of the ints, and adds their value into a result that is returned at the end
		int result = 0;
		
		for (int i : num) {
			result += i;
		}
		
		return result;
	}
}

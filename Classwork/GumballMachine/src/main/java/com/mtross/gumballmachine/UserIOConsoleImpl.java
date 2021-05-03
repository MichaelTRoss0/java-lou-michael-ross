/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.gumballmachine;

import java.util.Scanner;

/**
 *
 * @author mike
 */
public class UserIOConsoleImpl implements UserIO {
	Scanner scanner = new Scanner(System.in);

	@Override
	public void print(String msg) {
		System.out.println(msg);
	}

	@Override
	public double readDouble(String prompt) {
		double result;
		
		print(prompt);
		result = scanner.nextDouble();
		scanner.nextLine();
		
		return result;
	}

	@Override
	public double readDouble(String prompt, double min, double max) {
		double result = 0.0;
		boolean goodInput = false;
		
		do {
			result = readDouble(prompt);
			if (min <= result && result <= max) {
				goodInput = true;
			} else {
				print("Input must be a double between " + min + " and " + max);
			}
		} while (goodInput == false);
		
		return result;
	}

	@Override
	public float readFloat(String prompt) {
		float result;
		
		print(prompt);
		result = scanner.nextFloat();
		scanner.nextLine();
		
		return result;
	}

	@Override
	public float readFloat(String prompt, float min, float max) {
		float result = 0;
		boolean goodInput = false;
		
		do {
			result = readFloat(prompt);
			if (min <= result && result <= max) {
				goodInput = true;
			} else {
				print("Input must be an flaot between " + min + " and " + max);
			}
		} while (goodInput == false);
		
		return result;
	}

	@Override
	public int readInt(String prompt) {
		int result;
		
		print(prompt);
		result = scanner.nextInt();
		scanner.nextLine();
		
		return result;
	}

	@Override
	public int readInt(String prompt, int min, int max) {
		int result = 0;
		boolean goodInput = false;
		
		do {
			result = readInt(prompt);
			if (min <= result && result <= max) {
				goodInput = true;
			} else {
				print("Input must be an integer between " + min + " and " + max);
			}
		} while (goodInput == false);
		
		return result;
	}

	@Override
	public long readLong(String prompt) {
		long result;
		
		print(prompt);
		result = scanner.nextLong();
		scanner.nextLine();
		
		return result;
	}

	@Override
	public long readLong(String prompt, long min, long max) {
		long result = 0;
		boolean goodInput = false;
		
		do {
			result = readLong(prompt);
			if (min <= result && result <= max) {
				goodInput = true;
			} else {
				print("Input must be an long between " + min + " and " + max);
			}
		} while (goodInput == false);
		
		return result;
	}

	@Override
	public String readString(String prompt) {
		String result;
		
		print(prompt);
		result = scanner.nextLine();
		
		return result;
	}
	
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.userio;

import com.mtross.view.UserIO;
import com.mtross.view.UserIOImpl;

/**
 *
 * @author mike
 */
public class App {
	public static void main(String[] args) {
		UserIO io = new UserIOImpl();
		
//		String msg = io.readString("Please enter a message to echo:");
//		io.print(msg);
		
//		int inputInt = io.readInt("Give me an integer:");
//		io.print("You gave me " + inputInt);
//		
//		System.out.println();
//		
//		int validatedInt = io.readInt("Give me an integer from 1-10:", 1, 10);
//		io.print("You gave me: " + validatedInt);
		
//		double inputDouble = io.readDouble("Give me a double:");
//		io.print("You gave me: " + inputDouble);
//		
//		System.out.println();
//		
//		double validatedDouble = io.readDouble("Give me a double from 1-10:", 1, 10);
//		io.print("You gave me: " + validatedDouble);
		
//		float inputFloat = io.readFloat("Give me a float:");
//		io.print("You gave me: " + inputFloat);
//		
//		System.out.println();
//		
//		float validatedFloat = io.readFloat("Give me a float from 1-10:", 1, 10);
//		io.print("You gave me: " + validatedFloat);
		
		long inputLong = io.readLong("Give me a long:");
		io.print("You gave me: " + inputLong);
		
		System.out.println();
		
		long validatedLong = io.readLong("Give me a long from 1-10:", 1, 10);
		io.print("You gave me: " + validatedLong);
		
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.basics.arrays;

/**
 *
 * @author mike
 */
public class FruitsBasket {

	public static void main(String[] args) {
		String[] fruit = {"Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Apple", "Apple", "Orange", "Orange", "Apple", "Apple", "Apple", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange", "Orange", "Orange", "Apple", "Apple", "Apple", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Orange", "Orange", "Apple", "Apple", "Orange", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Apple", "Orange", "Orange"};
		
		int numA = 0;
		int numO = 0;
	
		for (String f : fruit) {
			if (f.matches("Apple")) {
			numA++;
			} else if (f.matches("Orange")) {
			numO++;
			}
		}
		
		System.out.println("There are " + numA + " apples in the mixed basket.");
		System.out.println("There are " + numO + " oranges in the mixed basket.");
	
		String[] apples = new String[numA];
		String[] oranges = new String[numO];
		
		for (var a : apples) {
			a = "Apple";
		}
		for (var o : oranges) {
			o = "Orange";
		}
		
		System.out.println("There are " + (apples.length + oranges.length) + " pieces of fruit in total.");
		System.out.println("There are " + apples.length + " apples.");
		System.out.println("There are " + oranges.length + " oranges.");
    }
}

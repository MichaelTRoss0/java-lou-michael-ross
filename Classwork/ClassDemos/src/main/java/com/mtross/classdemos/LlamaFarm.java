/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.classdemos;

/**
 *
 * @author mike
 */
public class LlamaFarm {
	
	public static void main(String[] args) {
		
		System.out.println("WELCOME TO THE LLAMA FARM");
		
		Llama floyd = new Llama("Floyd", "Brown & White",
			"Curly", 8, 55.4);
		System.out.println("This is floyd, tell us yor name floyd...!");
		System.out.println(floyd.getName());
		
		// let's make a herd
		Llama[] llamaHerd = new Llama[5];
		llamaHerd[0] = floyd;
		llamaHerd[1] = new Llama("BeeBop", "Brown", "Super Curly", 15, 58.9);
		llamaHerd[2] = new Llama("Dixie", "Milk Chocolate", "Kinda Curly", 9, 55.1);
		llamaHerd[3] = new Llama("Secret", "Dark Brown & White", "Straight", 6, 68.2);
		llamaHerd[4] = new Llama("Spectra", "White & Black", "Straight", 6, 51.9);
		
		// let's make them introduce themselves!
		for (int i = 0; i < llamaHerd.length; i++) {
			// get out each llama in order:
			Llama theLlama = llamaHerd[i];
			String llamaInfo = "This llama's name is " + theLlama.getName();
			System.out.println(llamaInfo);
			
			// could be reduced to this, but Austyn thinks the above is more readable:
//			System.out.println("This llama's name is " + llamaHerd[i].getName());
			
		}
		
		// SPITTING CONTEST!
		System.out.println("Let's watch them work their llama magi:");
		for (Llama aLlama : llamaHerd) {
			System.out.println("My name is " + aLlama.getName());
			aLlama.spit();
		}
		
	}
	
}

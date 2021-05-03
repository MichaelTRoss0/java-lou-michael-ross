/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.milestonesummative1;

import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author mike
 */
public class RockPaperScissors {
	public static void main(String[] args) {
		// Declare all the variables
		Scanner sc = new Scanner(System.in);
		String input;
		int rounds = 0;
		int user = 0;
		int comp = 0;
		Random rGen = new Random();
		int ties;
		int userWins;
		int compWins;
		boolean keepGoing = false;
		
		do {	// Start of the main body
			// Ask how many rounds will be played
			System.out.print("How many rounds of Rock, Paper, Scissors would you like to play (1-10)? ");
			input = sc.nextLine();

			// Did the user enter a number between 1 and 10?
			// Yes -> New round!
			// No -> Error message & quit
			if (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("5")  || input.equals("6")  || input.equals("7")  || input.equals("8") || input.equals("9") || input.equals("10")) {
				rounds = Integer.parseInt(input);
			} else {
				endSession();
			}

			ties = 0;
			userWins = 0;
			compWins = 0;

			do {
				// Ask user for Rock, Paper, or Scissors (1 = Rock, 2 = Paper, 3 = Scissors)
				boolean goodInput = false;
				System.out.print("Enter '1' for Rock, '2' for Paper, or '3' for Scissors: ");
				do {
					input = sc.nextLine();
					if (input.equals("1") || input.equals("2") || input.equals("3")) {
						user = Integer.parseInt(input);
						goodInput = true;
					} else {
						System.out.print("Input must be '1', '2', or '3': ");
					}
				} while (goodInput == false);

				// Computer randomly generates Rock, Paper or Scissors
				comp = rGen.nextInt(3) + 1;

				// Calculate the results of the round (Rock beats Scissors (1 > 3), Paper beats Rock (2 > 1), Scissors beats Paper (3 > 2))
				// Keep track of the results (tie, user win, computer win) in seperate variables
				// Display the results of the round
				System.out.println("The user selected " + identify(user));
				System.out.println("The computer selected " + identify(comp));
				
				if (didBeat(user, comp)) {
					System.out.println("The user wins this round.");
					userWins++;
				} else if (didBeat(comp, user)) {
					System.out.println("The computer wins this round.");
					compWins++;
				} else {
					System.out.println("This round ends in a draw.");
					ties++;
				}
				
				// All rounds played?
				// No  -> go back to "New Round!"
				// Yes -> continue
				rounds--;

			} while (rounds > 0);

			// Print out the total number of ties, user wins, and computer wins
			System.out.println("Ties: " + ties);
			System.out.println("User Wins: " + userWins);
			System.out.println("Computer Wins: " + compWins);

			// Declare the overall winner based on who won more rounds
			System.out.println("The winner is...");
			if (userWins > compWins) {
				System.out.println("The user!");
			} else if (compWins > userWins) {
				System.out.println("The computer!");
			} else {
				System.out.println("Nobody! This round ends in a draw.");
			}

			// Ask the user if they'd like to play again
			// Yes -> go back to "Ask how many rounds will be played"
			// No  -> continue
			System.out.print("Would you like to play again (y/N)? ");
			input = sc.nextLine();
			keepGoing = input.equals("y");
			
		} while (keepGoing);	// End of the main body - loops if the user wants it to
		
		// Print out "Thanks for playing!" and exit the program
		System.out.println("Thanks for playing!");
	}
	
	private static void endSession() {
		System.out.println("Something went wrong somewhere.");
		System.out.println("The current session will now end.");
		System.exit(0);
	}
	
	private static String identify(int x) {
		switch (x) {
			case 1:
				return "Rock";
			case 2:
				return "Paper";
			case 3:
				return "Scissors";
			default:
				endSession();
				return "";
		}
	}
	
	// Checks if x beat y
	private static boolean didBeat(int x, int y) {
		// Rock beats Scissors (1 > 3), Paper beats Rock (2 > 1), Scissors beats Paper (3 > 2)
		switch (x) {
			case 1:	// x selected Rock
				return y == 3;
			case 2:	// x selected Paper
				return y == 1;
			case 3:	// x selected Scissors
				return y == 2;
			default:
				endSession();
				return false;
		}
	}
}

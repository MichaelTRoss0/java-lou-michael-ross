/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.flowcontrol.fors;

import java.util.Scanner;

/**
 *
 * @author mike
 */
public class TwoForsAndTenYearsAgo {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        System.out.print("What year would you like to count back from? ");
        int year = userInput.nextInt();
        
        for (int i = 0; i <= 10; i++) {
            System.out.println(i + " years ago would be " + (year - i));
        }
        
        System.out.println("\nI can count backwards using a different way too...");
        
        for (int i = year; i >= year-10; i--) {
            System.out.println((year - i) + " years ago would be " + i);
        }
    }
}

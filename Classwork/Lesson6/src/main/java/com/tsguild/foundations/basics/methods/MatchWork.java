/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.basics.methods;

/**
 *
 * @author mike
 */
public class MatchWork {
    public static void main(String[] args) {
        
        System.out.println(" The word Cart should come before Horse alphabetically : " + comesBefore("Cart", "Horse"));
        System.out.println(" Half of 42 = " + halfOf(42.0));
        System.out.println(" (short) Pi = " + pi());
        System.out.println(" The first letter of the word Llama is: " + firstLetter("Llama"));
        System.out.println(" 1337 x 1337 = " + times1337(1337));
        
    }

    private static double pi() {
        return 3.14;
    }
    
    private static int times1337(int x) {
        return x * 1337;
    }
    
    private static double halfOf(double y) {
        return y / 2;
    }
    
    private static String firstLetter(String word) {
        return word.substring(0,1);
    }
    
    private static boolean comesBefore(String a, String b) {
        return a.compareToIgnoreCase(b) < 0;
    }
}

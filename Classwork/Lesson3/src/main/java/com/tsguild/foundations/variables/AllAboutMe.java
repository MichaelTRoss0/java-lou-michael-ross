/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.variables;

/**
 *
 * @author mike
 */
public class AllAboutMe {
    public static void main(String[] args) {
        // This program has five variables that are not all the same type.
        // These varaibles have data that stores my name, my favorite food,
        // how many pets I have, if I have ever eaten gnocchi,
        // and the age when I learned to whistle.
        // Then each of these variables are printed out on its own line.
        
        String name = "Michael Ross";
        String food = "pizza";
        int pets = 2;
        boolean gnocchi = false;
        int whistle = 7;
        
        System.out.println("My name is " + name + ".");
        System.out.println("My favorite food is " + food + ".");
        System.out.println("I have " + pets + " pet(s).");
        System.out.println("It is " + gnocchi + " that I have eaten gnocchi.");
        System.out.println("I learned to whistle at age " + whistle + ".");
    }
}

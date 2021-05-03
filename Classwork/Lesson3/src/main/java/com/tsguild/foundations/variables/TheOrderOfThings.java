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
public class TheOrderOfThings {
    public static void main(String[] args) {
        
        double number;
        String opinion, size, age, shape, color, origin, material, purpose;
        String noun;
        
        number = 5.0;
        opinion = "AWESOME";
        size = "teensy-weensy";
        age = "new";
        shape = "oblong";
        color = "yellow";
        origin = "Alpha-Centaurian";
        material = "platinum";
        purpose = "good";
        
        noun = "dragon";
        
        // When you use + with strings, Java concatenates (e.g., string them together) them instead of adding them.
        System.out.println(number + " " + opinion + " " + size + " " + age + " " + shape + " " + color + " " + origin + " " + material + " " + purpose + " " + noun);
    }
}

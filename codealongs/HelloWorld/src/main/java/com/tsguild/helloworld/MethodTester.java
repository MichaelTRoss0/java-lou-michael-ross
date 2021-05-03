/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.helloworld;

/**
 *
 * @author mike
 */
public class MethodTester {
    public static void main(String[] args) {
        double expected = -3.2;
        double actual = getSmallest(12.3, 4.2, 3.1415, -3.2, 53.3);
        
        if (actual == expected) {
            System.out.println("HUZZAH!");
        } else {
            System.out.println("Wait, got " + actual + " and expeted " + expected);
        }
        
        
    }
    
    // Five doubles and return the smallest
    public static double getSmallest(double double1, double double2, double double3, double double4, double doubleToilAndTrouble) {
        double result = double1;
        
        if (result > double2) {
            result = double2;
        }
        
        if (result > double3) {
            result = double3;
        }
        
        if (result > double4) {
            result = double4;
        }
        
        if (result > doubleToilAndTrouble) {
            result = doubleToilAndTrouble;
        }
        
        return result;
    }
    
    // int, double, float and return with the most 2's
    public static int getMostTwos(int valueOne, double valueTwo, float valueThree) {
        int result = 1;
        int mostTwoCount = 0;
        int currentTwoCount = 0;
        
        currentTwoCount = countCharacter(String.valueOf(valueOne), '2');
        if (currentTwoCount > mostTwoCount) {
            mostTwoCount = currentTwoCount;
            result = 1;
        }
        
        currentTwoCount = countCharacter(String.valueOf(valueTwo), '2');
        if (currentTwoCount > mostTwoCount) {
            mostTwoCount = currentTwoCount;
            result = 2;
        }
        
        currentTwoCount = countCharacter(String.valueOf(valueThree), '2');
        if (currentTwoCount > mostTwoCount) {
            mostTwoCount = currentTwoCount;
            result = 3;
        }
        
        return result;
    }
    
    public static int countCharacter(String string, char character) {
        //TODO: Do the thing!
        
        return 0;
    }
    
    // Returns the cooler of the two names
    // Accepts a filename and returns a scanner initialized to that file
    // Accepts a bridge name and returns its weight
    // Takes in an airspeed and returns what kind of swallow it is
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.unittesting.sectionthree;

/**
 *
 * @author ahill
 */
public class ExampleWork {
    
    // When llamas get together they like to bounce on trampolines.
    // However, llamas are very particular about the proper number of trampolines, 
    // and are usually only happy if there are between 24 to 42 (inclusive!).
    // This only changes if the trampolines are made of ultra-bouncey NASA fabric.
    // In those cases, the llamas figure the more trampolines the better!
    // 
    // Return true if the llamas will be happy with their trampolines,
    // or false otherwise.
    //
    //
    // areTheLlamasHappy(false, 20) → false
    // areTheLlamasHappy(false, 30) → true
    // areTheLlamasHappy(true, 100) → true
    public static boolean areTheLlamasHappy(boolean ultraBouncy, int trampolines) {
        if (trampolines >= 24 && (trampolines <= 42 || ultraBouncy)) {
            return true;
        } else {
            return false;
        }
    }
    
    // Given a number of bones, return the number of barks your dog will make!
    // If there is an even number of bones, your dog will bark three times.
    // If there is an odd number, they'll bark twice!
    // If there are none, there is only a silence (ex: "..."), 
    // as it waits patiently for more bones!
    //
    // Similarly, there's no such thing as a negative number of bones,
    // and so make sure your dog keeps absolutely silent in those cases too!
    //
    // howManyWoofs(10) -> "Woof! Woof! Woof!"
    // howManyWoofs(5)  -> "Woof! Woof!"
    // howManyWoofs(0)  -> "..."
    public static String howManyWoofs(int numBones) {
        if (numBones < 1) {
            return "...";
        } else {
            // DON'T DO THIS UNLESS YOU ABSOLUTELY HAVE TO
            // TRY TO DO BETTER
//            switch(numBones) {
//                case 1:
//                case 3:
//                case 5:
//                case 7:
//                case 11:
//                    return "Woof! Woof!";
//                default:
//                    return "Woof! Woof! Wooof!";
//            }

            int leftOver = numBones % 2;
            if (leftOver == 0) {
                return "Woof! Woof! Woof!";
            } else {
                return "Woof! Woof!";
            }
        }
    }
    
}

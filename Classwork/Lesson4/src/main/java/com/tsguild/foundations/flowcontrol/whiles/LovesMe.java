/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.flowcontrol.whiles;

/**
 *
 * @author mike
 */
public class LovesMe {
    public static void main(String[] args) {
        int petals = 34;
        boolean love;
        
        System.out.println("Well here goes nothing...");
        
        while (true) {
            petals--;
            love = false;
            System.out.println("It loves me NOT!");
            if (petals <= 0) {
                break;
            }
            
            petals--;
            love = true;
            System.out.println("It LOVES me!");
            if (petals <= 0) {
                break;
            }
        }
        
        if (love == true) {
            System.out.println("I knew it! It LOVES me!");
        }
    }
}

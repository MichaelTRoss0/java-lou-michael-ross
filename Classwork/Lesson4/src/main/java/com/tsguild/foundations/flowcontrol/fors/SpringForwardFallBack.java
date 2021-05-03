/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.foundations.flowcontrol.fors;

/**
 *
 * @author mike
 */
public class SpringForwardFallBack {
    public static void main(String[] args) {
        System.out.println("It's Spring...!");
        for (int i = 0; i < 10; i++) {
            System.out.print((i + 1) + ", ");
        }
        
        System.out.println("\nOh no, it's Fall...");
        for (int i = 10; i > 0; i--) {
            System.out.print(i + ", ");
        }
    }
}

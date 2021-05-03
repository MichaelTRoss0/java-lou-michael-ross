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
public class MenuOfChampions {
    public static void main(String[] args) {
        // This program displays the menu for a restaurant.
        String item1 = "Lobster";
        int price1 = 120;
        
        String item2 = "Swordfish";
        int price2 = 300;
        
        String item3 = "Shark";
        int price3 = 1000;
        
        System.out.println(".oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.");
        System.out.println();
        System.out.println("		       WELCOME TO RUNESCAPE");
        System.out.println("			Today's Menu Is...");
        System.out.println();
        System.out.println(".oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.");
        System.out.println();
        
        System.out.println("\t" + item1 + "\t\t" + price1 + "gp");
        System.out.println("\t" + item2 + "\t" + price2 + "gp");
        System.out.println("\t" + item3 + "\t\t" + price3 + "gp");
    }
}

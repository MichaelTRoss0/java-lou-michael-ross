/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.helloworld;

import java.util.Random;

/**
 *
 * @author mike
 */
public class CombatRoller {
    public static void main(String[] args) {
        Random rng = new Random();
        
        // Set up an enemy
        int enemyHitPoints = 10;
        
        // Set up an attack
        int attackHitChance = 50;
        int attackDamage = 2;
        
        int numberStrikes = 0;
        
        // While the enemy has health
        while (enemyHitPoints > 0) {
            // Try to hit the enemy
            numberStrikes++;
            int hit = rng.nextInt(100);
            if (hit < attackHitChance) {
                enemyHitPoints -= attackDamage;
                System.out.println("POW! The enemy now has " + enemyHitPoints + " hit points!");
            } else {
                System.out.println("WHOOSH!");
            }
        }
        
        System.out.println("You killed it in " + numberStrikes + " strikes!");
    }
}

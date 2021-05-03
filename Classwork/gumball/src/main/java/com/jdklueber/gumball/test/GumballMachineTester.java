/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdklueber.gumball.test;

import com.jdklueber.gumball.model.GumballMachine;

/**
 *
 * @author jason
 */
public class GumballMachineTester {
    
    public boolean allTestValidate() {
        boolean initial = testInitialState();
        boolean addCoin1 = testAddCoinReady();
        boolean addCoin2 = testAddCoinHasCoin();
        boolean turnHandle1 = testTurnHandleReady();
        boolean turnHandle2 = testTurnHandleHasCoin();
        return initial && addCoin1 && addCoin2 && turnHandle1 && turnHandle2;
    }
    
    public boolean testInitialState() {
        System.out.println("testInitialState...");
        GumballMachine gbm = new GumballMachine();
        boolean test1 = assertEquals(GumballMachine.READY_FOR_COIN, gbm.getState(), "...Initial state correct");        
        System.out.println("testInitialState complete...\n");
        
        return test1;
    }
    
    public boolean testAddCoinReady() {
        System.out.println("testAddCoinReady...");
        GumballMachine gbm = new GumballMachine();
        boolean test1 = assertEquals(GumballMachine.SUCCESS, gbm.addCoin(), "...addCoin()");
        boolean test2 = assertEquals(GumballMachine.HAS_COIN, gbm.getState(), "...addCoin() result");
        System.out.println("testAddCoinReady complete...\n");
        
        return test1 && test2;
    }
    
    public boolean testAddCoinHasCoin() {
        System.out.println("testAddCoinHasCoin...");
        GumballMachine gbm = new GumballMachine();
        gbm.addCoin();
        boolean test1 = assertEquals(GumballMachine.HAS_COIN, gbm.getState(), "...initial state set");
        boolean test2 = assertEquals(GumballMachine.FAILURE, gbm.addCoin(), "...addCoin()");
        boolean test3 = assertEquals(GumballMachine.HAS_COIN, gbm.getState(), "...addCoin() result");
        System.out.println("testAddCoinHasCoin complete...\n");

        return test1 && test2 && test3;
    }
    
    public boolean testTurnHandleReady() {
        System.out.println("testTurnHandleReady...");
        GumballMachine gbm = new GumballMachine();
        boolean test1 = assertEquals(GumballMachine.READY_FOR_COIN, gbm.getState(), "...initial state set");
        boolean test2 = assertEquals(GumballMachine.FAILURE, gbm.turnHandle(), "...turnHandle()");
        boolean test3 = assertEquals(GumballMachine.READY_FOR_COIN, gbm.getState(), "...turnHandle() result");
        System.out.println("testTurnHandleReady complete...\n");
        
        return test1 && test2 && test3;        
    }
    
    public boolean testTurnHandleHasCoin() {
        System.out.println("testTurnHandleHasCoin...");
        GumballMachine gbm = new GumballMachine();
        gbm.addCoin();
        boolean test1 = assertEquals(GumballMachine.HAS_COIN, gbm.getState(), "...initial state set");
        boolean test2 = assertEquals(GumballMachine.SUCCESS, gbm.turnHandle(), "...turnHandle()");
        boolean test3 = assertEquals(GumballMachine.READY_FOR_COIN, gbm.getState(), "...turnHandle() result");
        System.out.println("testTurnHandleHasCoin complete...\n");        
        
        return test1 && test2 && test3;
    }
   
    public boolean assertEquals(int expected, int actual, String title) {
        if (expected != actual) {
            System.out.println(title + ": fail");
            return false;
        } else {
            System.out.println(title + ": success");
            return true;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdklueber.gumball;

import com.jdklueber.gumball.model.GumballMachine;
import com.jdklueber.gumball.test.GumballMachineTester;

/**
 *
 * @author jason
 */
public class App {
    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
    
    public void run() {
        GumballMachineTester tests = new GumballMachineTester();
        
        if (tests.allTestValidate()) {
            System.out.println("Great success!  The gumball machine works!");
        } else {
            System.out.println("Rats.  The gumball machine is still broken.");
        }
    }


}

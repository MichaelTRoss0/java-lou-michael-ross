/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.vendingmachine;

import com.mtross.vendingmachine.controller.VendingMachineController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author mike
 */
public class App {
    
    public static void main(String[] args) {
        
        ApplicationContext ctx =
           new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller =
           ctx.getBean("controller", VendingMachineController.class);
        
        controller.run();
    }
}

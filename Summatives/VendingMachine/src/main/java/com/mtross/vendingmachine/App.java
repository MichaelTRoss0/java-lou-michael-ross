/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.vendingmachine;

import com.mtross.vendingmachine.controller.VendingMachineController;
import com.mtross.vendingmachine.dao.VendingMachineAuditDao;
import com.mtross.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import com.mtross.vendingmachine.dao.VendingMachineDao;
import com.mtross.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.mtross.vendingmachine.servicelayer.VendingService;
import com.mtross.vendingmachine.servicelayer.VendingServiceFileImpl;
import com.mtross.vendingmachine.ui.UserIO;
import com.mtross.vendingmachine.ui.UserIOConsoleImpl;
import com.mtross.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author mike
 */
public class App {
    
    public static void main(String[] args) {
        
        UserIO myIo = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIo);
        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
        VendingService myService = new VendingServiceFileImpl(myDao, myAuditDao);
        VendingMachineController controller = new VendingMachineController(myService, myView);
        
        controller.run();
    }
}

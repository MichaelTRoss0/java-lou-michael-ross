/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.vendingmachine.dao;

import com.mtross.vendingmachine.daoexception.VendingPersistenceException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import static java.time.temporal.ChronoUnit.*;

/**
 *
 * @author mike
 */
public class VendingMachineAuditDaoFileImpl implements VendingMachineAuditDao {

    public static final String AUDIT_FILE = "audit.txt";
    
    @Override
    public void writeAuditEntry(String entry) throws VendingPersistenceException {
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new VendingPersistenceException(
                    "Could not persist audit information", e);
        }
        
        LocalDateTime timeStamp = LocalDateTime.now();
        out.println(timeStamp.truncatedTo(SECONDS).toString() + " : [  " + entry+ " ]");
        out.flush();
    }
    
}

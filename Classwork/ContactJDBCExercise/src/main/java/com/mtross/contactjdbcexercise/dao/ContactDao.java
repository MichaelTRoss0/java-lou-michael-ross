/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.contactjdbcexercise.dao;

import com.mtross.contactjdbcexercise.dto.Contact;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author mike
 */
public interface ContactDao {
    
    public void loadContacts()
            throws SQLException;
    
    public void saveContacts()
            throws SQLException;
    
    public Contact addContact();
    
    public Contact getContact();
    
    public List<Contact> getAllContacts();
    
    public Contact updateContact();
    
    public Contact removeContact();
    
}

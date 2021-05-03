/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.contactjdbcexercise.dao;

import com.mtross.contactjdbcexercise.dto.Contact;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 *
 * @author mike
 */
public class ContactDaoDBImpl implements ContactDao {

    private DataSource ds;

    public ContactDaoDBImpl(DataSource ds) {
        this.ds = ds;
    }

    private Map<Integer, Contact> contacts = new HashMap<>();

    @Override
    public void loadContacts() throws SQLException {
        readDBForContacts();
    }

    @Override
    public void saveContacts() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Contact addContact() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Contact getContact() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Contact> getAllContacts() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Contact updateContact() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Contact removeContact() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void readDBForContacts() throws SQLException {
        try (Connection conn = ds.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Contact");
            while (rs.next()) {
                Contact currentContact = new Contact();
                currentContact.setContactId(rs.getInt("ContactId"));
                currentContact.setFirstName(rs.getString("FirstName"));
                currentContact.setLastName(rs.getString("LastName"));
                currentContact.setEmail(rs.getString("Email"));
                currentContact.setPhone(rs.getString("Phone"));
                
                contacts.put(currentContact.getContactId(), currentContact);
            }
        }
    }

}

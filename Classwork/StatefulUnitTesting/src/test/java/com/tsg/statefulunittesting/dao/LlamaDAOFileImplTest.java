/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.statefulunittesting.dao;

import com.tsg.statefulunittesting.dtos.Llama;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author mike
 */
public class LlamaDAOFileImplTest {
    
    LlamaDAOFileImpl testDAO;
    
    public LlamaDAOFileImplTest() {
        testDAO = new LlamaDAOFileImpl();
    }
    
    @Test
    public void unmarshallLlamaTest() {
        // ARRANGE
        String llamaLine = "FLOOFYFACE::Brown&White::Beebop::15::Grain";
        
        // ACT
        Llama fromLine = testDAO.unmarshallLlama(llamaLine);
        
        // ASSERT
        Assertions.assertEquals("FLOOFYFACE", fromLine.getId(), "Id should be FLOOFYFACE");
        Assertions.assertEquals("Brown&White", fromLine.getColor(), "Color should be Brown&White");
        Assertions.assertEquals("Beebop", fromLine.getName(), "Name should be Beebop");
        Assertions.assertEquals(15, fromLine.getYearsOld(), "Age should be 15");
        Assertions.assertEquals("Grain", fromLine.getFavFood(), "Favorite Food should be Grain");
    }
    
    @Test
    public void marshallLlamaTest() {
        // ARRANGE
        Llama toTextify = new Llama("MYID", "My Color", "My Name", 10, "Pizza");
        
        // ACT
        String llamaAsText = testDAO.marshallLlama(toTextify);
        
        // ASEERT
        Assertions.assertEquals("MYID::My Color::My Name::10::Pizza", llamaAsText, "Lines should match!");
        
    }
    
    @Test
    public void circleOfMarshallingTest() {
        // ARRANGE
        Llama toTextify = new Llama("MYID", "My Color", "My Name", 10, "Pizza");
        
        // ACT
        String tempLlamaText = testDAO.marshallLlama(toTextify);
        Llama shouldLookLikeOriginal = testDAO.unmarshallLlama(tempLlamaText);
        
        // ASSERT
        Assertions.assertEquals(toTextify, shouldLookLikeOriginal, "Llamas should match");
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.statefulunittesting.dao;

import com.tsg.statefulunittesting.dtos.Llama;
import java.util.List;

/**
 *
 * @author austynhill
 */
public interface LlamaDAO {
     
   // C
    public Llama addLlama(String id, Llama toAdd)
            throws LlamaDAOException;
    
    // R
    public Llama getLlama(String id)
            throws LlamaDAOException;
    public List<Llama> getAllLlamas()
            throws LlamaDAOException;
    
    // U
    public void editLlama(String id, Llama toUpdate)
            throws LlamaDAOException;
    
    // D
    public Llama removeLlama(String id)
            throws LlamaDAOException;
    
}

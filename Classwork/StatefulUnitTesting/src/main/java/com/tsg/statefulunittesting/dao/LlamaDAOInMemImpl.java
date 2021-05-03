/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsg.statefulunittesting.dao;

import com.tsg.statefulunittesting.dtos.Llama;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author austynhill
 */
public class LlamaDAOInMemImpl implements LlamaDAO{
    
    private Map<String, Llama> llamaHerd;
    
    public LlamaDAOInMemImpl(){
        llamaHerd = new HashMap<>();
    }

    @Override
    public Llama addLlama(String id, Llama toAdd) {
        Llama wasStoredUnderThatId = llamaHerd.put(id, toAdd);
        return wasStoredUnderThatId;
    }

    @Override
    public Llama getLlama(String id) {
        Llama storedUnderThatId = llamaHerd.get(id);
        return storedUnderThatId;
    }

    @Override
    public List<Llama> getAllLlamas() {
        Collection<Llama> allDaLlamas = llamaHerd.values();
        List<Llama> orderedLlamas = new ArrayList<>(allDaLlamas);
        return orderedLlamas;
    }

    @Override
    public void editLlama(String id, Llama toUpdate) {
//        boolean hasALlamaStoredUnderThatId = llamaHerd.containsKey(id);
//        if(hasALlamaStoredUnderThatId){
//            llamaHerd.put(id, toUpdate);
//        }

        llamaHerd.replace(id, toUpdate);
    }

    @Override
    public Llama removeLlama(String id) {
        Llama removed = llamaHerd.remove(id);
        return removed;
    }
    
}

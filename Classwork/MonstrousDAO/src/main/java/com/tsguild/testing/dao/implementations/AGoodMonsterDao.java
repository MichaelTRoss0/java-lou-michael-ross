/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tsguild.testing.dao.implementations;

import com.tsguild.testing.dao.MonsterDao;
import com.tsguild.testing.model.Monster;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ahill
 */
public class AGoodMonsterDao implements MonsterDao {

    Map<Integer, Monster> monsters = new HashMap<>();
    
    @Override
    public Monster addMonster(int id, Monster m) {
        return monsters.put(id, m);
    }

    @Override
    public Monster getMonster(int id) {
       return monsters.get(id);
    }

    @Override
    public List<Monster> getAllMonsters() {
        return new ArrayList<>(monsters.values());
    }

    @Override
    public void updateMonster(int id, Monster m) {
        monsters.replace(id, m);
    }

    @Override
    public Monster removeMonster(int id) {
        return monsters.remove(id);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.supersightings.dao;

import com.mtross.supersightings.entity.Power;
import java.util.List;

/**
 *
 * @author mike
 */
public interface PowerDao {

    public Power addPower(Power power);

    public List<Power> getAllPowers();

    public Power getPower(int powerId);

    public void updatePower(Power power);

    public void deletePower(int powerId);

}

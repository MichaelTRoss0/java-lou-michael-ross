/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.supersightings.dao;

import com.mtross.supersightings.entity.Sighting;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author mike
 */
public interface SightingDao {

    public Sighting addSighting(Sighting sighting);

    public List<Sighting> getAllSightings();

    public List<Sighting> getSightingsForDate(LocalDateTime date);

    public Sighting getSighting(int sightingId);

    public void updateSighting(Sighting sighting);

    public void deleteSighting(int sightingId);

}

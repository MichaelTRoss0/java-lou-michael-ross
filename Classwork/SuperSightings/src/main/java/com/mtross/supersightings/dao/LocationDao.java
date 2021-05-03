/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.supersightings.dao;

import com.mtross.supersightings.entity.Location;
import com.mtross.supersightings.entity.Super;
import java.util.List;

/**
 *
 * @author mike
 */
public interface LocationDao {

    public Location addLocation(Location location);

    public List<Location> getAllLocations();

    public List<Location> getLocationsForSuper(Super aSuper);

    public Location getLocation(int locationId);

    public void updateLocation(Location location);

    public void deleteLocation(int locationId);

}

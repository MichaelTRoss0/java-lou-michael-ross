/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.supersightings.dao;

import com.mtross.supersightings.entity.Location;
import com.mtross.supersightings.entity.Organization;
import com.mtross.supersightings.entity.Sighting;
import com.mtross.supersightings.entity.Super;
import java.util.List;

/**
 *
 * @author mike
 */
public interface SuperDao {

    public Super addSuper(Super aSuper);

    public List<Super> getAllSupers();

    public List<Super> getSupersForLocation(Location location);

    public List<Super> getSupersForOrganization(Organization organization);

    public List<Super> getSupersForSighting(Sighting sighting);

    public Super getSuper(int superId);

    public void updateSuper(Super aSuper);

    public void deleteSuper(int superId);

}

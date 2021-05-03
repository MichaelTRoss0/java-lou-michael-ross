/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.supersightings.dao;

import com.mtross.supersightings.entity.Organization;
import com.mtross.supersightings.entity.Super;
import java.util.List;

/**
 *
 * @author mike
 */
public interface OrganizationDao {

    public Organization addOrganization(Organization organization);

    public List<Organization> getAllOrganizations();

    public List<Organization> getOrganizationsForSuper(Super aSuper);

    public Organization getOrganization(int organizationId);

    public void updateOrganization(Organization organization);

    public void deleteOrganization(int organizationId);

}

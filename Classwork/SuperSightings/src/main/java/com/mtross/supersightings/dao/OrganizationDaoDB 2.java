/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.supersightings.dao;

import com.mtross.supersightings.dao.SuperDaoDB.SuperMapper;
import com.mtross.supersightings.entity.Organization;
import com.mtross.supersightings.entity.Power;
import com.mtross.supersightings.entity.Sighting;
import com.mtross.supersightings.entity.Super;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author mike
 */
@Repository
public class OrganizationDaoDB implements OrganizationDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public Organization addOrganization(Organization organization) {
        final String INSERT_ORGANIZATION
                = "INSERT INTO Organization(name, description, address, phone, email, isHeroic, isVillainous) "
                + "VALUES(?,?,?,?,?,?,?)";
        int heroVal = 0;
        int villVal = 0;
        if (organization.isHeroic()) {
            heroVal = 1;
        }
        if (organization.isVillainous()) {
            villVal = 1;
        }
        jdbc.update(INSERT_ORGANIZATION,
                organization.getName(),
                organization.getDescription(),
                organization.getAddress(),
                organization.getPhone(),
                organization.getEmail(),
                heroVal,
                villVal);

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        organization.setOrganizationId(newId);
        insertSuperOrganization(organization);
        return organization;
    }

    private void insertSuperOrganization(Organization organization) {
        final String INSERT_SUPER_ORGANIZATION
                = "INSERT INTO SuperOrganization(superId, organizationId) "
                + "VALUES(?,?)";
        for (Super aSuper : organization.getSupers()) {
            jdbc.update(INSERT_SUPER_ORGANIZATION,
                    aSuper.getSuperId(),
                    organization.getOrganizationId());
        }
    }

    @Override
    public List<Organization> getAllOrganizations() {
        final String SELECT_ALL_ORGANIZATIONS = "SELECT * FROM Organization";
        List<Organization> organizations = jdbc.query(SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());
        associateSupers(organizations);
        return organizations;
    }

    private void associateSupers(List<Organization> organizations) {
        for (Organization organization : organizations) {
            organization.setSupers(getSupersForOrganization(organization.getOrganizationId()));
        }
    }

    @Override
    public List<Organization> getOrganizationsForSuper(Super aSuper) {
        final String SELECT_ALL_ORGANIZATIONS
                = "SELECT o.* FROM Organization o "
                + "JOIN SuperOrganization so ON so.organizationId = o.organizationId "
                + "WHERE so.superId = ?";
        List<Organization> organizations = jdbc.query(SELECT_ALL_ORGANIZATIONS, new OrganizationMapper(), aSuper.getSuperId());
        associateSupers(organizations);
        return organizations;
    }

    @Override
    public Organization getOrganization(int organizationId) {
        try {
            final String SELECT_ORGANIZATION
                    = "SELECT * FROM Organization "
                    + "WHERE organizationId = ?";
            Organization organization = jdbc.queryForObject(SELECT_ORGANIZATION, new OrganizationMapper(), organizationId);
            organization.setSupers(getSupersForOrganization(organizationId));
            return organization;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    private List<Super> getSupersForOrganization(int organizationId) {
        final String SELECT_SUPERS_FOR_ORGANIZATION
                = "SELECT su.* FROM Super su "
                + "JOIN SuperOrganization so ON so.superId = su.superId "
                + "WHERE so.organizationId = ?";
        List<Super> supers = jdbc.query(SELECT_SUPERS_FOR_ORGANIZATION, new SuperMapper(), organizationId);
        for (Super aSuper : supers) {
            aSuper.setPowers(getPowersForSuper(aSuper.getSuperId()));
            aSuper.setSightings(getSightingsForSuper(aSuper.getSuperId()));
        }
        return supers;
    }

    private List<Power> getPowersForSuper(int superId) {
        final String SELECT_POWERS_FOR_SUPER
                = "SELECT p.* from Power p "
                + "JOIN SuperPower sp ON sp.powerId = p.powerId "
                + "WHERE sp.superId = ?";
        List<Power> powers = jdbc.query(SELECT_POWERS_FOR_SUPER, new PowerDaoDB.PowerMapper(), superId);
        return powers;
    }

    private List<Sighting> getSightingsForSuper(int superId) {
        final String SELECT_SIGHTINGS_FOR_SUPER
                = "SELECT si.* from Sighting si "
                + "JOIN SuperSighting ss ON ss.sightingId = si.sightingId "
                + "WHERE ss.superId = ?";
        List<Sighting> sightings = jdbc.query(SELECT_SIGHTINGS_FOR_SUPER, new SightingDaoDB.SightingMapper(), superId);
        return sightings;
    }

    @Override
    @Transactional
    public void updateOrganization(Organization organization) {
        final String UPDATE_ORGANIZATION
                = "UPDATE Organization "
                + "SET name = ?, description = ?, address = ?, phone = ?, email = ?, isHeroic = ?, isVillainous = ? "
                + "WHERE organizationId = ?";
        int heroVal = 0;
        int villVal = 0;
        if (organization.isHeroic()) {
            heroVal = 1;
        }
        if (organization.isVillainous()) {
            villVal = 1;
        }
        jdbc.update(UPDATE_ORGANIZATION,
                organization.getName(),
                organization.getDescription(),
                organization.getAddress(),
                organization.getPhone(),
                organization.getEmail(),
                heroVal,
                villVal,
                organization.getOrganizationId());

        for (Super aSuper : organization.getSupers()) {
            final String DELETE_SUPER_ORGANIZATION
                    = "DELETE FROM SuperOrganization "
                    + "WHERE organizationId = ?";
            jdbc.update(DELETE_SUPER_ORGANIZATION, organization.getOrganizationId());
        }
        insertSuperOrganization(organization);
    }

    @Override
    @Transactional
    public void deleteOrganization(int organizationId) {
        final String DELETE_SUPER_ORGANIZATION
                = "DELETE FROM SuperOrganization "
                + "WHERE organizationId = ?";
        jdbc.update(DELETE_SUPER_ORGANIZATION, organizationId);

        final String DELETE_ORGANIZATION
                = "DELETE FROM Organization "
                + "WHERE organizationId = ?";
        jdbc.update(DELETE_ORGANIZATION, organizationId);
    }

    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException {
            Organization organization = new Organization();
            organization.setOrganizationId(rs.getInt("organizationId"));
            organization.setName(rs.getString("name"));
            organization.setDescription(rs.getString("description"));
            organization.setAddress(rs.getString("address"));
            organization.setPhone(rs.getString("phone"));
            organization.setEmail(rs.getString("email"));
            if (rs.getInt("isHeroic") == 0) {
                organization.setIsHeroic(false);
            } else {
                organization.setIsHeroic(true);
            }
            if (rs.getInt("isVillainous") == 0) {
                organization.setIsVillainous(false);
            } else {
                organization.setIsVillainous(true);
            }

            return organization;
        }
    }

}

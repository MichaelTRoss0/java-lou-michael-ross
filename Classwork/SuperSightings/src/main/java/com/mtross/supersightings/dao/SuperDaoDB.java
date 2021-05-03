/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.supersightings.dao;

import com.mtross.supersightings.dao.PowerDaoDB.PowerMapper;
import com.mtross.supersightings.dao.SightingDaoDB.SightingMapper;
import com.mtross.supersightings.entity.Location;
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
public class SuperDaoDB implements SuperDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public Super addSuper(Super aSuper) {
        final String INSERT_SUPER
                = "INSERT INTO Super(name, description, isHero, isVillain) "
                + "VALUES(?,?,?,?)";
        int heroVal = 0;
        int villVal = 0;
        if (aSuper.isHero()) {
            heroVal = 1;
        }
        if (aSuper.isVillain()) {
            villVal = 1;
        }
        jdbc.update(INSERT_SUPER,
                aSuper.getName(),
                aSuper.getDescription(),
                heroVal,
                villVal);

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        aSuper.setSuperId(newId);
        insertSuperPower(aSuper);
        insertSuperSighting(aSuper);
        return aSuper;
    }

    private void insertSuperPower(Super aSuper) {
        final String INSERT_SUPER_POWER
                = "INSERT INTO SuperPower(superId, powerId) "
                + "VALUES(?,?)";
        if (aSuper.getPowers() != null) {
            for (Power power : aSuper.getPowers()) {
                jdbc.update(INSERT_SUPER_POWER,
                        aSuper.getSuperId(),
                        power.getPowerId());
            }
        }
    }

    private void insertSuperSighting(Super aSuper) {
        final String INSERT_SUPER_SIGHTING
                = "INSERT INTO SuperSighting(superId, sightingId) "
                + "VALUES(?,?)";
        if (aSuper.getSightings() != null) {
            for (Sighting sighting : aSuper.getSightings()) {
                jdbc.update(INSERT_SUPER_SIGHTING,
                        aSuper.getSuperId(),
                        sighting.getSightingId());
            }
        }
    }

    @Override
    public List<Super> getAllSupers() {
        final String SELECT_ALL_SUPERS = "SELECT * FROM Super";
        List<Super> supers = jdbc.query(SELECT_ALL_SUPERS, new SuperMapper());
        associatePowersAndSightings(supers);
        return supers;
    }

    private void associatePowersAndSightings(List<Super> supers) {
        for (Super aSuper : supers) {
            aSuper.setPowers(this.getPowersForSuper(aSuper.getSuperId()));
            aSuper.setSightings(this.getSightingsForSuper(aSuper.getSuperId()));
        }
    }

    @Override
    public List<Super> getSupersForLocation(Location location) {
        final String SELECT_SUPERS_FROM_LOCATIOIN
                = "SELECT su.* FROM Super su "
                + "JOIN SuperSighting ss ON ss.superId = su.superId "
                + "JOIN Sighting si ON si.sightingId = ss.sightingId "
                + "WHERE si.locationId = ?";
        List<Super> supers = jdbc.query(SELECT_SUPERS_FROM_LOCATIOIN, new SuperMapper(), location.getLocationId());
        associatePowersAndSightings(supers);
        return supers;
    }

    @Override
    public List<Super> getSupersForOrganization(Organization organization) {
        final String SELECT_SUPERS_FROM_ORGANIZATION
                = "SELECT su.* FROM Super su "
                + "JOIN SuperOrganization o ON o.superId = su.superId "
                + "WHERE o.organizationId = ?";
        List<Super> supers = jdbc.query(SELECT_SUPERS_FROM_ORGANIZATION, new SuperMapper(), organization.getOrganizationId());
        associatePowersAndSightings(supers);
        return supers;
    }

    @Override
    public List<Super> getSupersForSighting(Sighting sighting) {
        final String SELECT_SUPERS_FROM_SIGHTING
                = "SELECT su.* FROM Super su "
                + "JOIN SuperSighting ss ON ss.superId = su.superId "
                + "WHERE ss.sightingId = ?";
        List<Super> supers = jdbc.query(SELECT_SUPERS_FROM_SIGHTING, new SuperMapper(), sighting.getSightingId());
        associatePowersAndSightings(supers);
        return supers;
    }

    @Override
    public Super getSuper(int superId) {
        try {
            final String SELECT_SUPER
                    = "SELECT * FROM Super "
                    + "WHERE superId = ?";
            Super aSuper = jdbc.queryForObject(SELECT_SUPER, new SuperMapper(), superId);
            aSuper.setPowers(getPowersForSuper(superId));
            aSuper.setSightings(getSightingsForSuper(superId));
            return aSuper;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    private List<Power> getPowersForSuper(int superId) {
        final String SELECT_POWERS_FOR_SUPER
                = "SELECT p.* from Power p "
                + "JOIN SuperPower sp ON sp.powerId = p.powerId "
                + "WHERE sp.superId = ?";
        List<Power> powers = jdbc.query(SELECT_POWERS_FOR_SUPER, new PowerMapper(), superId);
        return powers;
    }

    private List<Sighting> getSightingsForSuper(int superId) {
        final String SELECT_SIGHTINGS_FOR_SUPER
                = "SELECT si.* from Sighting si "
                + "JOIN SuperSighting ss ON ss.sightingId = si.sightingId "
                + "WHERE ss.superId = ?";
        List<Sighting> sightings = jdbc.query(SELECT_SIGHTINGS_FOR_SUPER, new SightingMapper(), superId);
        return sightings;
    }

    @Override
    @Transactional
    public void updateSuper(Super aSuper) {
        final String UPDATE_SUPER
                = "UPDATE Super SET name = ?, description = ?, isHero = ?, isVillain = ? "
                + "WHERE superId = ?";
        int heroVal = 0;
        int villVal = 0;
        if (aSuper.isHero()) {
            heroVal = 1;
        }
        if (aSuper.isVillain()) {
            villVal = 1;
        }
        jdbc.update(UPDATE_SUPER,
                aSuper.getName(),
                aSuper.getDescription(),
                heroVal,
                villVal,
                aSuper.getSuperId());

        final String DELETE_SUPER_POWER
                = "DELETE FROM SuperPower "
                + "WHERE superId = ?";
        jdbc.update(DELETE_SUPER_POWER, aSuper.getSuperId());
        insertSuperPower(aSuper);

        final String DELETE_SUPER_SIGHTING
                = "DELETE FROM SuperSighting "
                + "WHERE superId = ?";
        jdbc.update(DELETE_SUPER_SIGHTING, aSuper.getSuperId());
        insertSuperSighting(aSuper);
    }

    @Override
    @Transactional
    public void deleteSuper(int superId) {
        final String DELETE_SUPER_POWER
                = "DELETE FROM SuperPower "
                + "WHERE superId = ?";
        jdbc.update(DELETE_SUPER_POWER, superId);

        final String DELETE_SUPER_SIGHTING
                = "DELETE FROM SuperSighting "
                + "WHERE superId = ?";
        jdbc.update(DELETE_SUPER_SIGHTING, superId);

        final String DELETE_SUPER_ORGANIZATION
                = "DELETE FROM SuperOrganization "
                + "WHERE superId = ?";
        jdbc.update(DELETE_SUPER_ORGANIZATION, superId);

        final String DELETE_SUPER
                = "DELETE FROM Super "
                + "WHERE superId = ?";
        jdbc.update(DELETE_SUPER, superId);
    }

    public static final class SuperMapper implements RowMapper<Super> {

        @Override
        public Super mapRow(ResultSet rs, int index) throws SQLException {
            Super aSuper = new Super();
            aSuper.setSuperId(rs.getInt("superId"));
            aSuper.setName(rs.getString("name"));
            aSuper.setDescription(rs.getString("description"));
            if (rs.getInt("isHero") == 0) {
                aSuper.setIsHero(false);
            } else {
                aSuper.setIsHero(true);
            }
            if (rs.getInt("isVillain") == 0) {
                aSuper.setIsVillain(false);
            } else {
                aSuper.setIsVillain(true);
            }

            return aSuper;
        }
    }

}

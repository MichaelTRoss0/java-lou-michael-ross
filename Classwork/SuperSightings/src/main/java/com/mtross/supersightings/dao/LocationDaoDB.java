/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.supersightings.dao;

import com.mtross.supersightings.entity.Location;
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
public class LocationDaoDB implements LocationDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public Location addLocation(Location location) {
        final String INSERT_LOCATION
                = "INSERT INTO Location(name, description, address, latitude, longitude) "
                + "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        location.setLocationId(newId);
        return location;
    }

    @Override
    public List<Location> getAllLocations() {
        final String SELECT_ALL_LOCATIONS = "SELECT * FROM Location";
        return jdbc.query(SELECT_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    public List<Location> getLocationsForSuper(Super aSuper) {
        final String SELECT_SUPER_LOCATIONS
                = "SELECT Location.* FROM Location "
                + "JOIN Sighting ON Sighting.locationId = Location.locationId "
                + "JOIN SuperSighting ss ON ss.sightingId = Sighting.sightingId "
                + "WHERE ss.superId = ?";
        return jdbc.query(SELECT_SUPER_LOCATIONS, new LocationMapper(), aSuper.getSuperId());
    }

    @Override
    public Location getLocation(int locationId) {
        try {
            final String SELECT_LOCATION = "SELECT * FROM Location WHERE locationId = ?";
            Location location = jdbc.queryForObject(SELECT_LOCATION, new LocationMapper(), locationId);
            return location;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public void updateLocation(Location location) {
        final String UPDATE_LOCATION
                = "UPDATE Location "
                + "SET name = ?, description = ?, address = ?, latitude = ?, longitude = ? "
                + "WHERE locationId = ?";
        jdbc.update(UPDATE_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude(),
                location.getLocationId());
    }

    @Override
    @Transactional
    public void deleteLocation(int locationId) {
        final String DELETE_SUPERSIGHTINGS
                = "DELETE SuperSighting FROM SuperSighting "
                + "JOIN Sighting ON Sighting.sightingId = SuperSighting.sightingId "
                + "WHERE Sighting.locationId = ?";
        jdbc.update(DELETE_SUPERSIGHTINGS, locationId);

        final String DELETE_SIGHTINGS
                = "DELETE FROM Sighting "
                + "WHERE locationId = ?";
        jdbc.update(DELETE_SIGHTINGS, locationId);

        final String DELETE_LOCATION
                = "DELETE FROM Location "
                + "WHERE locationId = ?";
        jdbc.update(DELETE_LOCATION, locationId);
    }

    private static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location location = new Location();
            location.setLocationId(rs.getInt("locationId"));
            location.setName(rs.getString("name"));
            location.setDescription(rs.getString("description"));
            location.setAddress(rs.getString("address"));
            location.setLatitude(rs.getString("latitude"));
            location.setLongitude(rs.getString("longitude"));

            return location;
        }
    }

}

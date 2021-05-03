/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.supersightings.dao;

import com.mtross.supersightings.entity.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
public class SightingDaoDB implements SightingDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Sighting addSighting(Sighting sighting) {
        final String INSERT_SIGHTING
                = "INSERT INTO Sighting(locationId, date) "
                + "VALUES(?,?)";
        jdbc.update(INSERT_SIGHTING,
                sighting.getLocationId(),
                Timestamp.valueOf(sighting.getDate()));

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setSightingId(newId);
        return sighting;
    }

    @Override
    public List<Sighting> getAllSightings() {
        final String SELECT_ALL_SIGHTING = "SELECT * FROM Sighting";
        return jdbc.query(SELECT_ALL_SIGHTING, new SightingMapper());
    }

    @Override
    public List<Sighting> getSightingsForDate(LocalDateTime date) {
        final String SELECT_SIGHTINGS_FROM_DATE
                = "SELECT * FROM Sighting "
                + "WHERE date = ?";
        List<Sighting> sightings
                = jdbc.query(SELECT_SIGHTINGS_FROM_DATE, new SightingMapper(), Timestamp.valueOf(date));
        return sightings;
    }

    @Override
    public Sighting getSighting(int sightingId) {
        try {
            final String SELECT_SIGHTING = "SELECT * FROM Sighting WHERE sightingId = ?";
            return jdbc.queryForObject(SELECT_SIGHTING, new SightingMapper(), sightingId);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public void updateSighting(Sighting sighting) {
        final String UPDATE_STUDENT
                = "UPDATE Sighting SET locationId = ?, date = ? "
                + "WHERE sightingId = ?";
        jdbc.update(UPDATE_STUDENT,
                sighting.getLocationId(),
                Timestamp.valueOf(sighting.getDate()),
                sighting.getSightingId());
    }

    @Override
    @Transactional
    public void deleteSighting(int sightingId) {
        final String DELETE_SUPER_SIGHTING
                = "DELETE FROM SuperSighting "
                + "WHERE sightingId = ?";
        jdbc.update(DELETE_SUPER_SIGHTING, sightingId);

        final String DELETE_SIGHTING
                = "DELETE FROM Sighting "
                + "WHERE sightingId = ?";
        jdbc.update(DELETE_SIGHTING, sightingId);
    }

    public static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setSightingId(rs.getInt("sightingId"));
            sighting.setLocationId(rs.getInt("locationId"));
            sighting.setDate(rs.getTimestamp("date").toLocalDateTime());

            return sighting;
        }
    }

}

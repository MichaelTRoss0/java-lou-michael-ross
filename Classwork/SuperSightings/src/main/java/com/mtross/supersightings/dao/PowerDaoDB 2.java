/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.supersightings.dao;

import com.mtross.supersightings.entity.Power;
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
public class PowerDaoDB implements PowerDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    @Transactional
    public Power addPower(Power power) {
        final String INSERT_POWER
                = "INSERT INTO Power(name, description) "
                + "VALUES(?,?)";
        jdbc.update(INSERT_POWER,
                power.getName(),
                power.getDescription());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        power.setPowerId(newId);
        return power;
    }

    @Override
    public List<Power> getAllPowers() {
        final String SELECT_ALL_POWERS = "SELECT * FROM Power";
        return jdbc.query(SELECT_ALL_POWERS, new PowerMapper());
    }

    @Override
    public Power getPower(int powerId) {
        try {
            final String SELECT_POWER
                    = "SELECT * FROM Power "
                    + "WHERE powerId = ?";
            return jdbc.queryForObject(SELECT_POWER, new PowerMapper(), powerId);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public void updatePower(Power power) {
        final String UPDATE_POWER
                = "UPDATE Power SET name = ?, description = ? "
                + "WHERE powerId = ?";
        jdbc.update(UPDATE_POWER,
                power.getName(),
                power.getDescription(),
                power.getPowerId());
    }

    @Override
    @Transactional
    public void deletePower(int powerId) {
        final String DELETE_SUPER_POWER
                = "DELETE FROM SuperPower "
                + "WHERE powerId = ?";
        jdbc.update(DELETE_SUPER_POWER, powerId);

        final String DELETE_POWER
                = "DELETE FROM Power "
                + "WHERE powerId = ?";
        jdbc.update(DELETE_POWER, powerId);
    }

    public static final class PowerMapper implements RowMapper<Power> {

        @Override
        public Power mapRow(ResultSet rs, int index) throws SQLException {
            Power power = new Power();
            power.setPowerId(rs.getInt("powerId"));
            power.setName(rs.getString("name"));
            power.setDescription(rs.getString("description"));

            return power;
        }
    }

}

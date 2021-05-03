/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.supersightings.dao;

import com.mtross.supersightings.entity.Location;
import com.mtross.supersightings.entity.Organization;
import com.mtross.supersightings.entity.Power;
import com.mtross.supersightings.entity.Sighting;
import com.mtross.supersightings.entity.Super;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author mike
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PowerDaoDBTest {

    @Autowired
    SuperDao superDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    PowerDao powerDao;

    @Autowired
    LocationDao locationDao;

    @Autowired
    SightingDao sightingDao;

    public PowerDaoDBTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        List<Super> supers = superDao.getAllSupers();
        for (Super aSuper : supers) {
            superDao.deleteSuper(aSuper.getSuperId());
        }

        List<Organization> organizations = organizationDao.getAllOrganizations();
        for (Organization organization : organizations) {
            organizationDao.deleteOrganization(organization.getOrganizationId());
        }

        List<Power> powers = powerDao.getAllPowers();
        for (Power power : powers) {
            powerDao.deletePower(power.getPowerId());
        }

        List<Location> locations = locationDao.getAllLocations();
        for (Location location : locations) {
            locationDao.deleteLocation(location.getLocationId());
        }

        List<Sighting> sightings = sightingDao.getAllSightings();
        for (Sighting sighting : sightings) {
            sightingDao.deleteSighting(sighting.getSightingId());
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddAndGetPower() {
        Power power = new Power();
        power.setName("Test Power Name");
        power.setDescription("Test Power Description");
        power = powerDao.addPower(power);

        Power fromDao = powerDao.getPower(power.getPowerId());

        assertEquals(power, fromDao);
    }

    @Test
    public void testGetAllPowers() {
        Power power1 = new Power();
        power1.setName("Test Power Name");
        power1.setDescription("Test Power Description");
        power1 = powerDao.addPower(power1);

        Power power2 = new Power();
        power2.setName("Test Power Name");
        power2.setDescription("Test Power Description");
        power2 = powerDao.addPower(power2);

        List<Power> fromDao = powerDao.getAllPowers();

        assertEquals(2, fromDao.size());
        assertTrue(fromDao.contains(power1));
        assertTrue(fromDao.contains(power2));
    }

    @Test
    public void testUpdatePower() {
        Power power = new Power();
        power.setName("Test Power Name");
        power.setDescription("Test Power Description");
        power = powerDao.addPower(power);

        Power fromDao = powerDao.getPower(power.getPowerId());

        assertEquals(power, fromDao);

        power.setName("New Test Power Name");
        powerDao.updatePower(power);

        assertNotEquals(power, fromDao);

        fromDao = powerDao.getPower(power.getPowerId());

        assertEquals(power, fromDao);
    }

    @Test
    public void testDeletePower() {
        Power power = new Power();
        power.setName("Test Power Name");
        power.setDescription("Test Power Description");
        power = powerDao.addPower(power);

        List<Power> powers = new ArrayList<>();
        powers.add(power);

        Location location = new Location();
        location.setName("Test Location Name");
        location.setDescription("Test Location Description");
        location.setAddress("Test Location Address");
        location.setLatitude(" 23.56789");
        location.setLongitude(" 23.56789");
        location = locationDao.addLocation(location);

        Sighting sighting = new Sighting();
        sighting.setLocationId(location.getLocationId());
        sighting.setDate(LocalDateTime.now());
        sighting = sightingDao.addSighting(sighting);

        List<Sighting> sightings = new ArrayList<>();
        sightings.add(sighting);

        Super testSuper = new Super();
        testSuper.setName("Test Super Name");
        testSuper.setDescription("Test Super Description");
        testSuper.setIsHero(true);
        testSuper.setIsVillain(false);
        testSuper.setPowers(powers);
        testSuper.setSightings(sightings);
        testSuper = superDao.addSuper(testSuper);

        List<Super> supers = new ArrayList<>();
        supers.add(testSuper);

        Organization organization = new Organization();
        organization.setName("Test Organization Name");
        organization.setDescription("Test Organization Description");
        organization.setAddress("Test Organization Address");
        organization.setPhone("(XXX) XXX-XXXX");
        organization.setEmail("test@organization.email");
        organization.setIsHeroic(true);
        organization.setIsVillainous(false);
        organization.setSupers(supers);
        organizationDao.addOrganization(organization);

        Power fromDao = powerDao.getPower(power.getPowerId());

        assertEquals(power, fromDao);

        powerDao.deletePower(power.getPowerId());

        fromDao = powerDao.getPower(power.getPowerId());

        assertNull(fromDao);
    }

}

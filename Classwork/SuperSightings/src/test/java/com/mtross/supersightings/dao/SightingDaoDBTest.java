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
public class SightingDaoDBTest {

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

    public SightingDaoDBTest() {
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
    public void testAddAndGetSighting() {
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

        Sighting fromDao = sightingDao.getSighting(sighting.getSightingId());

        assertNotNull(sighting.getDate());
        assertEquals(sighting, fromDao);
    }

    @Test
    public void testGetAllSightings() {
        Location location = new Location();
        location.setName("Test Location Name");
        location.setDescription("Test Location Description");
        location.setAddress("Test Location Address");
        location.setLatitude(" 23.56789");
        location.setLongitude(" 23.56789");
        location = locationDao.addLocation(location);

        Sighting sighting1 = new Sighting();
        sighting1.setLocationId(location.getLocationId());
        sighting1.setDate(LocalDateTime.now());
        sighting1 = sightingDao.addSighting(sighting1);

        Sighting sighting2 = new Sighting();
        sighting2.setLocationId(location.getLocationId());
        sighting2.setDate(LocalDateTime.now());
        sighting2 = sightingDao.addSighting(sighting2);

        List<Sighting> fromDao = sightingDao.getAllSightings();

        assertEquals(2, fromDao.size());
        assertTrue(fromDao.contains(sighting1));
        assertTrue(fromDao.contains(sighting2));
    }

    @Test
    public void testGetSightingsForDate() {
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

        List<Sighting> fromDao = sightingDao.getSightingsForDate(sighting.getDate());

        assertEquals(1, fromDao.size());
        assertTrue(fromDao.contains(sighting));
    }

    @Test
    public void testUpdateSighting() {
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

        Sighting fromDao = sightingDao.getSighting(sighting.getSightingId());

        assertEquals(sighting, fromDao);

        Location location2 = new Location();
        location2.setName("Test Location Name 2");
        location2.setDescription("Test Location Description 2");
        location2.setAddress("Test Location Address 2");
        location2.setLatitude(" 23.56789");
        location2.setLongitude(" 23.56789");
        location2 = locationDao.addLocation(location2);
        sighting.setLocationId(location2.getLocationId());
        sightingDao.updateSighting(sighting);

        assertNotEquals(sighting, fromDao);

        fromDao = sightingDao.getSighting(sighting.getSightingId());

        assertEquals(sighting, fromDao);
    }

    @Test
    public void testDeleteSighting() {
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

        Sighting fromDao = sightingDao.getSighting(sighting.getSightingId());

        assertEquals(sighting, fromDao);

        sightingDao.deleteSighting(sighting.getSightingId());

        fromDao = sightingDao.getSighting(sighting.getSightingId());

        assertNull(fromDao);
    }

}

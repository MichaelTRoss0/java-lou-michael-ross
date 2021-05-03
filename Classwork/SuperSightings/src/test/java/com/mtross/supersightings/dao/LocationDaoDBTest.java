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
public class LocationDaoDBTest {

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

    public LocationDaoDBTest() {
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
    public void testAddAndGetLocation() {
        Location location = new Location();
        location.setName("Test Location Name");
        location.setDescription("Test Location Description");
        location.setAddress("Test Location Address");
        location.setLatitude(" 23.56789");
        location.setLongitude(" 23.56789");
        location = locationDao.addLocation(location);

        Location fromDao = locationDao.getLocation(location.getLocationId());

        assertEquals(location, fromDao);
    }

    @Test
    public void testGetAllLocations() {
        Location location1 = new Location();
        location1.setName("Test Location Name");
        location1.setDescription("Test Location Description");
        location1.setAddress("Test Location Address");
        location1.setLatitude(" 23.56789");
        location1.setLongitude(" 23.56789");
        location1 = locationDao.addLocation(location1);

        Location location2 = new Location();
        location2.setName("Test Location Name");
        location2.setDescription("Test Location Description");
        location2.setAddress("Test Location Address");
        location2.setLatitude(" 23.56789");
        location2.setLongitude(" 23.56789");
        location2 = locationDao.addLocation(location2);

        List<Location> fromDao = locationDao.getAllLocations();

        assertEquals(2, fromDao.size());
        assertTrue(fromDao.contains(location1));
        assertTrue(fromDao.contains(location2));
    }

    @Test
    public void testGetLocationsForSuper() {
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

        List<Location> fromDao = locationDao.getLocationsForSuper(testSuper);

        assertEquals(1, fromDao.size());
        assertTrue(fromDao.contains(location));
    }

    @Test
    public void testUpdateLocation() {
        Location location = new Location();
        location.setName("Test Location Name");
        location.setDescription("Test Location Description");
        location.setAddress("Test Location Address");
        location.setLatitude(" 23.56789");
        location.setLongitude(" 23.56789");
        location = locationDao.addLocation(location);

        Location fromDao = locationDao.getLocation(location.getLocationId());

        assertEquals(location, fromDao);

        location.setName("New Test Location Name");
        locationDao.updateLocation(location);

        assertNotEquals(location, fromDao);

        fromDao = locationDao.getLocation(location.getLocationId());

        assertEquals(location, fromDao);
    }

    @Test
    public void testDeleteLocation() {
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

        Location fromDao = locationDao.getLocation(location.getLocationId());

        assertEquals(location, fromDao);

        locationDao.deleteLocation(location.getLocationId());

        fromDao = locationDao.getLocation(location.getLocationId());

        assertNull(fromDao);
    }

}

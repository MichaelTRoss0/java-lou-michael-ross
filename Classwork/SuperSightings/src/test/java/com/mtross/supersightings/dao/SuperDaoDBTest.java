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
public class SuperDaoDBTest {

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

    public SuperDaoDBTest() {
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
    public void testAddAndGetSuper() {
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

        Super fromDao = superDao.getSuper(testSuper.getSuperId());

        assertEquals(testSuper, fromDao);
    }

    @Test
    public void testGetAllSupers() {
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

        Super testSuper1 = new Super();
        testSuper1.setName("Test Super First");
        testSuper1.setDescription("Test Super Description");
        testSuper1.setIsHero(true);
        testSuper1.setIsVillain(false);
        testSuper1.setPowers(powers);
        testSuper1.setSightings(sightings);

        Super testSuper2 = new Super();
        testSuper2.setName("Test Super Name 1");
        testSuper2.setDescription("Test Super Name 2");
        testSuper2.setIsHero(true);
        testSuper2.setIsVillain(false);
        testSuper2.setPowers(powers);
        testSuper2.setSightings(sightings);

        testSuper1 = superDao.addSuper(testSuper1);
        testSuper2 = superDao.addSuper(testSuper2);

        List<Super> supers = superDao.getAllSupers();

        assertEquals(2, supers.size());
        assertTrue(supers.contains(testSuper1));
        assertTrue(supers.contains(testSuper2));
    }

    @Test
    public void testGetSupersForLocation() {
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

        List<Super> fromDao = superDao.getSupersForLocation(location);

        assertEquals(1, fromDao.size());
        assertTrue(fromDao.contains(testSuper));
    }

    @Test
    public void testGetSupersForOrganization() {
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
        organization.setName("Test Organization Name.");
        organization.setDescription("Test Organization Description");
        organization.setAddress("Test Organization Address");
        organization.setPhone("(XXX) XXX-XXXX");
        organization.setEmail("test@organization.email");
        organization.setIsHeroic(true);
        organization.setIsVillainous(false);
        organization.setSupers(supers);
        organization = organizationDao.addOrganization(organization);

        List<Super> fromDao = superDao.getSupersForOrganization(organization);

        assertEquals(1, fromDao.size());
        assertTrue(fromDao.contains(testSuper));
    }

    @Test
    public void testGetSupersForSighting() {
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

        List<Super> fromDao = superDao.getSupersForSighting(sighting);

        assertEquals(1, fromDao.size());
        assertTrue(fromDao.contains(testSuper));
    }

    @Test
    public void testUpdateSuper() {
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

        Super fromDao = superDao.getSuper(testSuper.getSuperId());

        assertEquals(testSuper, fromDao);

        testSuper.setName("New Test Super Name");
        Sighting sighting2 = new Sighting();
        sighting2.setLocationId(location.getLocationId());
        sighting2.setDate(LocalDateTime.now());
        sighting2 = sightingDao.addSighting(sighting2);
        sightings.add(sighting2);
        testSuper.setSightings(sightings);

        superDao.updateSuper(testSuper);

        assertNotEquals(testSuper, fromDao);

        fromDao = superDao.getSuper(testSuper.getSuperId());

        assertEquals(testSuper, fromDao);
    }

    @Test
    public void testDeleteSuper() {
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

        Super fromDao = superDao.getSuper(testSuper.getSuperId());

        assertEquals(testSuper, fromDao);

        superDao.deleteSuper(testSuper.getSuperId());

        fromDao = superDao.getSuper(testSuper.getSuperId());

        assertNull(fromDao);
    }

}

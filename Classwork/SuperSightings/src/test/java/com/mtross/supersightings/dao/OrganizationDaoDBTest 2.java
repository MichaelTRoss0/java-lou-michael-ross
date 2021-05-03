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
public class OrganizationDaoDBTest {

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

    public OrganizationDaoDBTest() {
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
    public void testAddAndGetOrganization() {
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
        organization = organizationDao.addOrganization(organization);

        Organization fromDao = organizationDao.getOrganization(organization.getOrganizationId());

        assertEquals(organization, fromDao);
    }

    @Test
    public void testGetAllOrganizations() {
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

        Organization organization1 = new Organization();
        organization1.setName("Test Organization Name 1");
        organization1.setDescription("Test Organization Description");
        organization1.setAddress("Test Organization Address");
        organization1.setPhone("(XXX) XXX-XXXX");
        organization1.setEmail("test@organization.email");
        organization1.setIsHeroic(true);
        organization1.setIsVillainous(false);
        organization1.setSupers(supers);

        Organization organization2 = new Organization();
        organization2.setName("Test Organization Name 2");
        organization2.setDescription("Test Organization Description");
        organization2.setAddress("Test Organization Address");
        organization2.setPhone("(XXX) XXX-XXXX");
        organization2.setEmail("test@organization.email");
        organization2.setIsHeroic(true);
        organization2.setIsVillainous(false);
        organization2.setSupers(supers);

        organization1 = organizationDao.addOrganization(organization1);
        organization2 = organizationDao.addOrganization(organization2);

        List<Organization> fromDao = organizationDao.getAllOrganizations();

        assertEquals(2, fromDao.size());
        assertTrue(fromDao.contains(organization1));
        assertTrue(fromDao.contains(organization2));
    }

    @Test
    public void testGetOrganizationsForSuper() {
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
        organization = organizationDao.addOrganization(organization);

        List<Organization> fromDao = organizationDao.getOrganizationsForSuper(testSuper);

        assertEquals(1, fromDao.size());
        assertTrue(fromDao.contains(organization));
    }

    @Test
    public void testUpdateOrganization() {
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
        organization = organizationDao.addOrganization(organization);

        Organization fromDao = organizationDao.getOrganization(organization.getOrganizationId());

        assertEquals(organization, fromDao);

        organization.setName("New Test Organization Name");
        Super testSuper2 = new Super();
        testSuper2.setName("Test Super Name 2");
        testSuper2.setDescription("Test Super Description 2");
        testSuper2.setIsHero(false);
        testSuper2.setIsVillain(true);
        testSuper2.setPowers(powers);
        testSuper2.setSightings(sightings);
        testSuper2 = superDao.addSuper(testSuper2);
        supers.add(testSuper2);
        organization.setSupers(supers);

        organizationDao.updateOrganization(organization);

        assertNotEquals(organization, fromDao);

        fromDao = organizationDao.getOrganization(organization.getOrganizationId());

        assertEquals(organization, fromDao);
    }

    @Test
    public void testDeleteOrganization() {
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
        organization = organizationDao.addOrganization(organization);

        Organization fromDao = organizationDao.getOrganization(organization.getOrganizationId());

        assertEquals(organization, fromDao);

        organizationDao.deleteOrganization(organization.getOrganizationId());

        fromDao = organizationDao.getOrganization(organization.getOrganizationId());

        assertNull(fromDao);
    }
}

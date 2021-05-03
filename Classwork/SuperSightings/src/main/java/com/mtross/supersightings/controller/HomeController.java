/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mtross.supersightings.controller;

import com.mtross.supersightings.dao.LocationDao;
import com.mtross.supersightings.dao.OrganizationDao;
import com.mtross.supersightings.dao.PowerDao;
import com.mtross.supersightings.dao.SightingDao;
import com.mtross.supersightings.dao.SuperDao;
import com.mtross.supersightings.entity.Location;
import com.mtross.supersightings.entity.Sighting;
import com.mtross.supersightings.entity.Super;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author mike
 */
@Controller
public class HomeController {

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

    @GetMapping("home")
    public String displayRecentSightings(Model model) {
        List<Sighting> sightings = sightingDao.getAllSightings();
        while (sightings.size() > 10) {
            sightings.remove(0);
        }
        model.addAttribute("sightings", sightings);

        Map<Sighting, String> mapOfLocations = new HashMap<>();
        Map<Sighting, List<String>> mapOfSupers = new HashMap<>();

        for (Sighting sighting : sightings) {
            Location location = locationDao.getLocation(sighting.getLocationId());
            String name = location.getName();
            mapOfLocations.put(sighting, name);

            List<Super> supers = superDao.getSupersForSighting(sighting);
            List<String> names = new ArrayList<>();
            for (Super aSuper : supers) {
                names.add(aSuper.getName());
            }
            mapOfSupers.put(sighting, names);
        }

        model.addAttribute("mapofLocations", mapOfLocations);
        model.addAttribute("mapOfSupers", mapOfSupers);

        return "home";
    }

    @GetMapping("")
    public String getIndex(Model model) {
        return displayRecentSightings(model);
    }

}

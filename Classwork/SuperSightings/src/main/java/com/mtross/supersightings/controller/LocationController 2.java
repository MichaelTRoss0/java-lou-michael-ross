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
import com.mtross.supersightings.entity.Super;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author mike
 */
@Controller
public class LocationController {

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

    Set<ConstraintViolation<Location>> violations = new HashSet<>();

    @GetMapping("locations")
    public String displayLocations(Model model) {
        List<Location> locations = locationDao.getAllLocations();
        Map<Location, List<Super>> mapOfSupers = new HashMap<>();

        for (Location location : locations) {
            List<Super> supers = superDao.getSupersForLocation(location);
            List<Super> matchedSupers = new ArrayList<>();

            for (Super aSuper : supers) {
                matchedSupers.add(aSuper);
            }

            mapOfSupers.put(location, matchedSupers);
        }

        model.addAttribute("locations", locations);
        model.addAttribute("mapOfSupers", mapOfSupers);
        model.addAttribute("errors", violations);
        return "locations";
    }

    @PostMapping("addLocation")
    public String addLocation(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String address = request.getParameter("address");
        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");

        Location location = new Location();
        location.setName(name);
        location.setDescription(description);
        location.setAddress(address);
        location.setLatitude(latitude);
        location.setLongitude(longitude);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(location);

        if (violations.isEmpty()) {
            locationDao.addLocation(location);
        }

        return "redirect:/locations";
    }

    @GetMapping("deleteLocation")
    public String deleteLocation(HttpServletRequest request) {
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        locationDao.deleteLocation(locationId);

        return "redirect:/locations";
    }

    @GetMapping("editLocation")
    public String editLocation(HttpServletRequest request, Model model) {
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        Location location = locationDao.getLocation(locationId);

        model.addAttribute("location", location);
        return "editLocation";
    }

    @PostMapping("editLocation")
    public String performEditLocation(@Valid Location location, BindingResult result, HttpServletRequest request, Model model) {
        location.setName(request.getParameter("name"));
        location.setDescription(request.getParameter("description"));
        location.setAddress(request.getParameter("address"));
        location.setLatitude(request.getParameter("latitude"));
        location.setLongitude(request.getParameter("longitude"));

        if (result.hasErrors()) {
            model.addAttribute("location", location);
            return "editLocation";
        }

        locationDao.updateLocation(location);

        return "redirect:/locations";
    }

}

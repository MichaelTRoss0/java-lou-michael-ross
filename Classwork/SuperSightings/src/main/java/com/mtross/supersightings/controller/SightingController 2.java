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
import java.time.LocalDateTime;
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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author mike
 */
@Controller
public class SightingController {

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

    Set<ConstraintViolation<Sighting>> violations = new HashSet<>();

    @GetMapping("sightings")
    public String displaySightings(Model model) {
        List<Sighting> sightings = sightingDao.getAllSightings();
        List<Location> locations = locationDao.getAllLocations();
        List<Super> supers = superDao.getAllSupers();
        Map<Sighting, Location> mapOfLocations = new HashMap<>();
        Map<Sighting, List<Super>> mapOfSupers = new HashMap<>();

        int locationId;
        for (Sighting sighting : sightings) {
            locationId = sighting.getLocationId();
            mapOfLocations.put(sighting, locationDao.getLocation(locationId));
            mapOfSupers.put(sighting, superDao.getSupersForSighting(sighting));
        }

        model.addAttribute("sightings", sightings);
        model.addAttribute("locations", locations);
        model.addAttribute("supers", supers);
        model.addAttribute("mapOfLocations", mapOfLocations);
        model.addAttribute("mapOfSupers", mapOfSupers);
        model.addAttribute("errors", violations);

        return "sightings";
    }

    @PostMapping("addSighting")
    public String addSighting(HttpServletRequest request) {
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        LocalDateTime date = LocalDateTime.parse(request.getParameter("date") + ":00");
        String[] superIds = request.getParameterValues("supers");

        List<Super> supers = new ArrayList<>();
        for (String superId : superIds) {
            supers.add(superDao.getSuper(Integer.parseInt(superId)));
        }

        Sighting sighting = new Sighting();
        sighting.setLocationId(locationId);
        sighting.setDate(date);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(sighting);

        if (violations.isEmpty()) {
            sightingDao.addSighting(sighting);

            for (Super aSuper : supers) {
                List<Sighting> superSightings = aSuper.getSightings();
                superSightings.add(sighting);
                aSuper.setSightings(superSightings);
                superDao.updateSuper(aSuper);
            }
        }

        return "redirect:/sightings";
    }

    @GetMapping("deleteSighting")
    public String deleteSighting(HttpServletRequest request) {
        int sightingId = Integer.parseInt(request.getParameter("sightingId"));
        sightingDao.deleteSighting(sightingId);

        return "redirect:/sightings";
    }

    @GetMapping("editSighting")
    public String editSighting(HttpServletRequest request, Model model) {
        int sightingId = Integer.parseInt(request.getParameter("sightingId"));
        Sighting sighting = sightingDao.getSighting(sightingId);

        int locationId = sighting.getLocationId();
        Location matchedLocation = locationDao.getLocation(locationId);

        List<Super> matchedSupers = superDao.getSupersForSighting(sighting);

        List<Location> locations = locationDao.getAllLocations();

        List<Super> supers = superDao.getAllSupers();

        model.addAttribute("sighting", sighting);
        model.addAttribute("matchedLocation", matchedLocation);
        model.addAttribute("matchedSupers", matchedSupers);
        model.addAttribute("locations", locations);
        model.addAttribute("supers", supers);

        return "editSighting";
    }

    @PostMapping("editSighting")
    public String performEditSighting(@Valid Sighting sighting, BindingResult result, HttpServletRequest request, Model model) {
        int locationId = Integer.parseInt(request.getParameter("locationId"));
        String dateAsString = request.getParameter("date") + ":00";
        LocalDateTime date = LocalDateTime.parse(dateAsString);
        String[] superIds = request.getParameterValues("supers");

        List<Super> newSuperList = new ArrayList<>();
        if (superIds != null) {
            for (String superId : superIds) {
                newSuperList.add(superDao.getSuper(Integer.parseInt(superId)));
            }
        } else {
            FieldError error = new FieldError("sighting", "matchedSupers", "Must include at least one super.");
            result.addError(error);
        }

        sighting.setLocationId(locationId);
        sighting.setDate(date);

        if (result.hasErrors()) {
            model.addAttribute("sighting", sighting);
            model.addAttribute("matchedLocation", locationDao.getLocation(locationId));
            model.addAttribute("matchedSupers", newSuperList);
            model.addAttribute("locations", locationDao.getAllLocations());
            model.addAttribute("supers", superDao.getAllSupers());
            return "editSighting";
        }

        sightingDao.updateSighting(sighting);

        List<Super> oldSuperList = superDao.getSupersForSighting(sighting);
        updateSupers(newSuperList, oldSuperList, sighting);

        return "redirect:/sightings";
    }

    private void updateSupers(List<Super> newSuperList, List<Super> oldSuperList, Sighting sighting) {
        List<Super> addedSupers = new ArrayList<>();
        List<Super> removedSupers = new ArrayList<>();

        List<Super> allSupers = superDao.getAllSupers();
        for (Super aSuper : allSupers) {
            if (newSuperList.contains(aSuper) && !oldSuperList.contains(aSuper)) {
                addedSupers.add(aSuper);
            }
            if (oldSuperList.contains(aSuper) && !newSuperList.contains(aSuper)) {
                removedSupers.add(aSuper);
            }
        }

        for (Super aSuper : addedSupers) {
            List<Sighting> superSightings = aSuper.getSightings();
            superSightings.add(sighting);
            aSuper.setSightings(superSightings);
            superDao.updateSuper(aSuper);
        }

        for (Super aSuper : removedSupers) {
            List<Sighting> superSightings = aSuper.getSightings();
            superSightings.remove(sighting);
            aSuper.setSightings(superSightings);
            superDao.updateSuper(aSuper);
        }
    }

}

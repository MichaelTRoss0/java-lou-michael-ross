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
import com.mtross.supersightings.entity.Organization;
import com.mtross.supersightings.entity.Power;
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
public class SuperController {

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

    Set<ConstraintViolation<Super>> violations = new HashSet<>();

    @GetMapping("supers")
    public String displaySupers(Model model) {
        List<Super> supers = superDao.getAllSupers();
        Map<Super, String> isHero = new HashMap<>();
        Map<Super, String> isVillain = new HashMap<>();
        Map<Super, List<Power>> mapOfPowers = new HashMap<>();
        Map<Super, List<Location>> mapOfLocations = new HashMap<>();
        Map<Super, List<Organization>> mapOfOrganizations = new HashMap<>();

        for (Super aSuper : supers) {
            if (aSuper.isHero()) {
                isHero.put(aSuper, "Yes");
            } else {
                isHero.put(aSuper, "No");
            }

            if (aSuper.isVillain()) {
                isVillain.put(aSuper, "Yes");
            } else {
                isVillain.put(aSuper, "No");
            }

            mapOfPowers.put(aSuper, aSuper.getPowers());
            mapOfLocations.put(aSuper, locationDao.getLocationsForSuper(aSuper));
            mapOfOrganizations.put(aSuper, organizationDao.getOrganizationsForSuper(aSuper));
        }

        List<Power> powers = powerDao.getAllPowers();

        model.addAttribute("supers", supers);
        model.addAttribute("isHero", isHero);
        model.addAttribute("isVillain", isVillain);
        model.addAttribute("mapOfLocations", mapOfLocations);
        model.addAttribute("mapOfOrganizations", mapOfOrganizations);
        model.addAttribute("powers", powers);
        model.addAttribute("errors", violations);

        return "supers";
    }

    @PostMapping("addSuper")
    public String addSuper(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String isHeroString = request.getParameter("isHero");
        String isVillainString = request.getParameter("isVillain");
        String[] powerIds = request.getParameterValues("powers");

        Boolean isHero;
        if (isHeroString.equalsIgnoreCase("Yes")) {
            isHero = true;
        } else {
            isHero = false;
        }

        Boolean isVillain;
        if (isVillainString.equalsIgnoreCase("Yes")) {
            isVillain = true;
        } else {
            isVillain = false;
        }

        List<Power> powers = new ArrayList<>();
        for (String powerId : powerIds) {
            powers.add(powerDao.getPower(Integer.parseInt(powerId)));
        }

        Super aSuper = new Super();
        aSuper.setName(name);
        aSuper.setDescription(description);
        aSuper.setIsHero(isHero);
        aSuper.setIsVillain(isVillain);
        aSuper.setPowers(powers);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(aSuper);

        if (violations.isEmpty()) {
            superDao.addSuper(aSuper);
        }

        return "redirect:/supers";
    }

    @GetMapping("deleteSuper")
    public String deleteSuper(HttpServletRequest request) {
        int superId = Integer.parseInt(request.getParameter("superId"));
        superDao.deleteSuper(superId);

        return "redirect:/supers";
    }

    @GetMapping
    public String editSuper(HttpServletRequest request, Model model) {
        int superId = Integer.parseInt(request.getParameter("superId"));
        Super aSuper = superDao.getSuper(superId);

        List<Power> allPowers = powerDao.getAllPowers();

        model.addAttribute("super", aSuper);
        model.addAttribute("allPowers", allPowers);

        return "editSuper";
    }

    @PostMapping
    public String performEditSuper(@Valid Super aSuper, BindingResult result, HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String isHeroString = request.getParameter("isHero");
        String isVillainString = request.getParameter("isVillain");
        String[] powerIds = request.getParameterValues("powerId");

        Boolean isHero;
        if (isHeroString.equalsIgnoreCase("Yes")) {
            isHero = true;
        } else {
            isHero = false;
        }

        Boolean isVillain;
        if (isVillainString.equalsIgnoreCase("Yes")) {
            isVillain = true;
        } else {
            isVillain = false;
        }

        List<Power> powers = new ArrayList<>();
        for (String powerId : powerIds) {
            powers.add(powerDao.getPower(Integer.parseInt(powerId)));
        }

        aSuper.setName(name);
        aSuper.setDescription(description);
        aSuper.setIsHero(isHero);
        aSuper.setIsVillain(isVillain);
        aSuper.setPowers(powers);

        if (result.hasErrors()) {
            model.addAttribute("super", aSuper);
            model.addAttribute("allPowers", powerDao.getAllPowers());
            return "editSuper";
        }

        superDao.updateSuper(aSuper);

        return "redirect:/supers";
    }

}

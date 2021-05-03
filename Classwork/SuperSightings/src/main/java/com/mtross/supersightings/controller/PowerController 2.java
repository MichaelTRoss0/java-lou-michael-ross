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
public class PowerController {

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

    Set<ConstraintViolation<Power>> violations = new HashSet<>();

    @GetMapping("powers")
    public String displayPowerInfo(Model model) {
        List<Power> powers = powerDao.getAllPowers();

        Map<Power, List<Super>> mapOfSupers = new HashMap<>();

        List<Super> supers = superDao.getAllSupers();
        for (Power power : powers) {
            List<Super> matchedSupers = new ArrayList<>();
            for (Super aSuper : supers) {
                if (aSuper.getPowers().contains(power)) {
                    matchedSupers.add(aSuper);
                }
            }
            mapOfSupers.put(power, matchedSupers);
        }

        model.addAttribute("powers", powers);
        model.addAttribute("mapOfSupers", mapOfSupers);
        model.addAttribute("errors", violations);

        return "powers";
    }

    @PostMapping("addPower")
    public String addPower(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        Power power = new Power();
        power.setName(name);
        power.setDescription(description);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(power);

        if (violations.isEmpty()) {
            powerDao.addPower(power);
        }

        return "redirect:/powers";
    }

    @GetMapping("deletePower")
    public String deletePower(HttpServletRequest request) {
        int powerId = Integer.parseInt(request.getParameter("powerId"));
        powerDao.deletePower(powerId);

        return "redirect:/powers";
    }

    @GetMapping("editPower")
    public String editPower(HttpServletRequest request, Model model) {
        int powerId = Integer.parseInt(request.getParameter("powerId"));
        Power power = powerDao.getPower(powerId);

        model.addAttribute("power", power);
        return "editPower";
    }

    @PostMapping("editPower")
    public String performEditPower(@Valid Power power, BindingResult result, HttpServletRequest request, Model model) {
        power.setName(request.getParameter("name"));
        power.setDescription(request.getParameter("description"));

        if (result.hasErrors()) {
            model.addAttribute("power", power);
            return "editPower";
        }

        powerDao.updatePower(power);

        return "redirect:/powers";
    }

}

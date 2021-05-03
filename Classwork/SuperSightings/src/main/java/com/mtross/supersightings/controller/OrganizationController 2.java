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
import com.mtross.supersightings.entity.Organization;
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
public class OrganizationController {

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

    Set<ConstraintViolation<Organization>> violations = new HashSet<>();

    @GetMapping("organizations")
    public String displayOrganizations(Model model) {
        List<Organization> organizations = organizationDao.getAllOrganizations();
        List<Super> allSupers = superDao.getAllSupers();
        Map<Organization, String> isHeroic = new HashMap<>();
        Map<Organization, String> isVillainous = new HashMap<>();
        Map<Organization, List<Super>> mapOfSupers = new HashMap<>();

        for (Organization organization : organizations) {
            if (organization.isHeroic()) {
                isHeroic.put(organization, "Yes");
            } else {
                isHeroic.put(organization, "No");
            }

            if (organization.isVillainous()) {
                isVillainous.put(organization, "Yes");
            } else {
                isVillainous.put(organization, "No");
            }

            mapOfSupers.put(organization, superDao.getSupersForOrganization(organization));
        }

        model.addAttribute("organizations", organizations);
        model.addAttribute("allSupers", allSupers);
        model.addAttribute("isHeroic", isHeroic);
        model.addAttribute("isVillainous", isVillainous);
        model.addAttribute("mapOfSupers", mapOfSupers);
        model.addAttribute("errors", violations);

        return "organizations";
    }

    @PostMapping("addOrganization")
    public String addOrganization(HttpServletRequest request) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String isHeroicString = request.getParameter("isHeroic");
        String isVillainousString = request.getParameter("isVillainous");
        String[] superIds = request.getParameterValues("supers");

        Boolean isHeroic;
        if (isHeroicString.equalsIgnoreCase("Yes")) {
            isHeroic = true;
        } else {
            isHeroic = false;
        }

        Boolean isVillainous;
        if (isVillainousString.equalsIgnoreCase("Yes")) {
            isVillainous = true;
        } else {
            isVillainous = false;
        }

        List<Super> supers = new ArrayList<>();
        for (String superId : superIds) {
            supers.add(superDao.getSuper(Integer.parseInt(superId)));
        }

        Organization organization = new Organization();
        organization.setName(name);
        organization.setDescription(description);
        organization.setAddress(address);
        organization.setPhone(phone);
        organization.setEmail(email);
        organization.setIsHeroic(isHeroic);
        organization.setIsVillainous(isVillainous);
        organization.setSupers(supers);

        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
        violations = validate.validate(organization);

        if (violations.isEmpty()) {
            organizationDao.addOrganization(organization);
        }

        return "redirect:/organizations";
    }

    @GetMapping("deleteOrganization")
    public String deleteOrganization(HttpServletRequest request) {
        int organizationId = Integer.parseInt(request.getParameter("organizationId"));
        organizationDao.deleteOrganization(organizationId);

        return "redirect:/organizations";
    }

    @GetMapping("editOrganization")
    public String editOrganization(HttpServletRequest request, Model model) {
        int organizationId = Integer.parseInt(request.getParameter("organizationId"));
        Organization organization = organizationDao.getOrganization(organizationId);

        List<Super> matchedsupers = organization.getSupers();

        List<Super> allSupers = superDao.getAllSupers();

        model.addAttribute("organization", organization);
        model.addAttribute("matchedSupers", matchedsupers);
        model.addAttribute("allSupers", allSupers);

        return "editOrganization";
    }

    @PostMapping("editOrganization")
    public String performEditOrganization(@Valid Organization organization, BindingResult result, HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String isHeroicString = request.getParameter("isHeroic");
        String isVillainousString = request.getParameter("isVillainous");
        String[] superIds = request.getParameterValues("superId");

        Boolean isHeroic;
        if (isHeroicString.equalsIgnoreCase("Yes")) {
            isHeroic = true;
        } else {
            isHeroic = false;
        }

        Boolean isVillainous;
        if (isVillainousString.equalsIgnoreCase("Yes")) {
            isVillainous = true;
        } else {
            isVillainous = false;
        }

        List<Super> matchedSupers = new ArrayList<>();
        for (String superId : superIds) {
            matchedSupers.add(superDao.getSuper(Integer.parseInt(superId)));
        }

        organization.setName(name);
        organization.setDescription(description);
        organization.setAddress(address);
        organization.setPhone(phone);
        organization.setEmail(email);
        organization.setIsHeroic(isHeroic);
        organization.setIsVillainous(isVillainous);
        organization.setSupers(matchedSupers);

        if (result.hasErrors()) {
            model.addAttribute("organization", organization);
            model.addAttribute("matchedSupers", matchedSupers);
            model.addAttribute("allSupers", superDao.getAllSupers());
            return "editOrganization";
        }

        organizationDao.updateOrganization(organization);

        return "redirect:/organizations";
    }

}

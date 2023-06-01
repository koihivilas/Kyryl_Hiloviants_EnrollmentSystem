package org.application.controllers;

import jakarta.servlet.http.HttpSession;
import org.application.models.Specialization;
import org.application.models.User;
import org.application.services.SpecializationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpecializationController {
    private final SpecializationService specializationService;

    public SpecializationController(SpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    @RequestMapping("/specializations")
    public String specializations(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        if (user.getRole().getCode().equals("STUDENT")) {
            model.addAttribute("specializations", specializationService.getAllOpenedSpecializations());
        } else if(user.getRole().getCode().equals("ADMIN")) {
            model.addAttribute("specializations", specializationService.getAllSpecializations());
        }

        return "specializations";
    }

    @RequestMapping("/specializations/{code}")
    public String specialization(@PathVariable int code, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        Specialization spec = specializationService.getSpecializationByCode(code);

        if (spec != null) {
            model.addAttribute("spec", spec);
            return "specialization";
        }

        return "specializationnotfound";
    }
}

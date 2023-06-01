package org.application.controllers;

import jakarta.servlet.http.HttpSession;
import org.application.models.Subject;
import org.application.models.User;
import org.application.services.SubjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SubjectController {
    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @RequestMapping("/subjects")
    public String subjects(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        if (user.getRole().getCode().equals("STUDENT")) {
            model.addAttribute("subjects", subjectService.getSubjectsByUser(user.getUserId()));
        } else if(user.getRole().getCode().equals("ADMIN")) {
            model.addAttribute("subjects", subjectService.getAllSubjects());
        }

        return "subjects";
    }

    @RequestMapping("/subjects/{id}")
    public String user(@PathVariable long id, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        Subject s = subjectService.getSubjectById(id);

        if(!user.getRole().getCode().equals("ADMIN") && s.isClosed()) {
            return "subjectnotfound";
        } else {
            model.addAttribute("s", s);
            return "subject";
        }
    }

    @PostMapping("/subjects/change_score/{id}")
    public String changeScore(@PathVariable long id, @RequestParam int score, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        subjectService.changeScore(user.getUserId(), id, score);
        return "redirect:/subjects";
    }

}

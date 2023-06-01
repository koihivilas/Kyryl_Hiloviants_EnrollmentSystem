package org.application.controllers;

import jakarta.servlet.http.HttpSession;
import org.application.models.User;
import org.application.services.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        User user = userService.loginUser(username, password);

        if(user != null) {
            session.setAttribute("user", user);
            return "redirect:/";
        }

        model.addAttribute("error", "Invalid username or password");
        return "login";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        User user = userService.createNewUser(userForm);

        if(user != null) {
            session.setAttribute("user", user);
            return "redirect:/";
        }

        model.addAttribute("error", "User with this username, email or phone already exists");
        return "signup";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("/signup")
    public String signUpPage() {
        return "signup";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }

    @RequestMapping("/users")
    public String usersPage() {
        return "redirect:/users/1";
    }

    @RequestMapping("/users/{page}")
    public String users(@PathVariable int page, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        if (user.getRole().getCode().equals("ADMIN")) {
            int pageSize = 4;

            Page<User> usersPage = userService.getAllUsers(page, pageSize);
            List<User> users = usersPage.getContent();
            model.addAttribute("users", users);
            model.addAttribute("page", page);
            model.addAttribute("totalPages", usersPage.getTotalPages());
            model.addAttribute("startPage", Math.max(1, page - 2));
            model.addAttribute("endPage", Math.min(usersPage.getTotalPages(), page + 2));

            return "users";
        }

        return "redirect:/";
    }

    @RequestMapping("/u/{username}")
    public String user(@PathVariable String username, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        User u = userService.getUserByUsername(username);
        if (u != null) {
            model.addAttribute("u", u);

            return "user";
        }

        return "usernotfound";
    }

    @PostMapping("/users/change_role/{id}")
    public String changeRole(@PathVariable long id, @RequestParam String roleCode, @RequestParam int page) {
        userService.changeRole(id, roleCode);
        return "redirect:/users/" + page;
    }

    @PostMapping("/users/change_status/{id}")
    public String changeStatus(@PathVariable long id, @RequestParam String statusCode, @RequestParam int page) {
        userService.changeStatus(id, statusCode);
        return "redirect:/users/" + page;
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable long id, @RequestParam int page) {
        userService.deleteUser(id);
        return "redirect:/users/" + page;
    }
}

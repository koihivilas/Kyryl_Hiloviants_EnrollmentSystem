package org.application.commands.implementations;

import org.application.commands.CommandController;
import org.application.entities.User;
import org.application.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommand implements CommandController {
    @Override
    public boolean execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(req.getSession().getAttribute("User") != null)
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,"You already logged-in");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserService userService = new UserService();
        User newUser = userService.loginUser(username, password);
        if (newUser != null) {
            req.getSession().setAttribute("user", newUser);
            return true;
        }
        return false;
    }
}
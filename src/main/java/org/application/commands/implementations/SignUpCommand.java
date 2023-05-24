package org.application.commands.implementations;

import org.application.commands.CommandController;
import org.application.entities.Role;
import org.application.entities.Specialization;
import org.application.entities.User;
import org.application.entities.UserStatus;
import org.application.services.RoleService;
import org.application.services.UserService;
import org.application.services.UserStatusService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpCommand implements CommandController {

    @Override
    public boolean execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(req.getSession().getAttribute("User") != null)
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED,"You already logged-in");
        String username = req.getParameter("username");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String password = req.getParameter("password");

        String roleCode = "STUDENT";
        Role role = new RoleService().getRoleByCode(roleCode);
        String userStatusCode = "REG";
        UserStatus userStatus = new UserStatusService().getStatusByCode(userStatusCode);
        User user = new User(username, password, firstName, lastName, email, phone, role, userStatus, new Specialization());
        if (role != null && userStatus != null) {
            User newUser = new UserService().createNewUser(user);
            if (newUser != null) {
                req.getSession().setAttribute("user", newUser);
                return true;
            }
        }
        return false;
    }
}
package org.application.commands.implementations;

import org.application.commands.CommandController;
import org.application.entities.Specialization;
import org.application.entities.User;
import org.application.services.SpecializationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SpecializationsCommand implements CommandController {
    @Override
    public boolean execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
            {
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                throw new ServletException();
            }
            SpecializationService specializationService = new SpecializationService();
            List<Specialization> specializations = null;
            if (user.getRole().getCode().equals("STUDENT")) {
                specializations = specializationService.getAllSpecializations();
                specializations.remove(0); // Should be made inside service?
            } else if(user.getRole().getCode().equals("ADMIN")) {
                specializations = specializationService.getAllSpecializations();
            }
            req.getSession().setAttribute("specializations", specializations);
            resp.sendRedirect("/html/specializations.jsp");
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}

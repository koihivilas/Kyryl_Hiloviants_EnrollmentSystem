package org.application.commands.implementations;

import org.application.commands.CommandController;
import org.application.entities.Subject;
import org.application.entities.User;
import org.application.services.SubjectService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SubjectsCommand implements CommandController {
    @Override
    public boolean execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            User user = (User) req.getSession().getAttribute("user");
            if (user == null)
            {
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                throw new ServletException();
            }
            SubjectService subjectService = new SubjectService();
            List<Subject> subjects = null;
            if (user.getRole().getCode().equals("STUDENT")) {
                subjects = subjectService.getAllOpenedSubjects();
            } else if(user.getRole().getCode().equals("ADMIN")) {
                subjects = subjectService.getAllSubjects();
            }
            req.getSession().setAttribute("subjects", subjects);
            resp.sendRedirect("/html/subjects.jsp");
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}

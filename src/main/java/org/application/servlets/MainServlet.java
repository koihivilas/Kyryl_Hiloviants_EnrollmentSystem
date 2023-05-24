package org.application.servlets;

import org.application.commands.CommandController;
import org.application.commands.implementations.LoginCommand;
import org.application.commands.implementations.SignUpCommand;
import org.application.commands.implementations.SpecializationsCommand;
import org.application.commands.implementations.SubjectsCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/html/login", "/html/signup", "/html/logout", "/html/subjects", "/html/specializations"})
public class MainServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(MainServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        logger.info("Command: " + command);
        switch (command) {
            case "subjects" -> {
                CommandController subjectsCommand = new SubjectsCommand();
                subjectsCommand.execute(req, resp);
            }
            case "specializations" -> {
                CommandController specializationsCommand = new SpecializationsCommand();
                specializationsCommand.execute(req, resp);
            }
            default -> {
            }
            }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        HttpSession session = req.getSession();
        logger.info("Command: " + command);

        switch (command) {
            case "login" -> {
                CommandController loginCommand = new LoginCommand();
                if (loginCommand.execute(req, resp)) {
                    resp.sendRedirect("/html/home.jsp");
                } else {
                    req.setAttribute("error", "Invalid username or password");
                    req.getRequestDispatcher("/html/login.jsp").forward(req, resp);
                }
            }
            case "signup" -> {
                CommandController signUpCommand = new SignUpCommand();
                if (signUpCommand.execute(req, resp)) {
                    resp.sendRedirect("/html/home.jsp");
                } else {
                    req.setAttribute("error", "Your username, email or phone is already taken");
                    req.getRequestDispatcher("/html/signup.jsp").forward(req, resp);
                }
            }
            case "logout" -> {
                session.setAttribute("user", null);
                session.setAttribute("subjects", null);
                session.setAttribute("specializations", null);
                resp.sendRedirect("/html/home.jsp");
            }
            default -> {
            }
        }

    }
}
package by.gsu.epamlab.filters;

import by.gsu.epamlab.controllers.Constants;
import by.gsu.epamlab.model.beans.Role;
import by.gsu.epamlab.model.beans.User;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by alex on 01.07.2016.
 */
public class SecurityFilter extends AbstractFilter {

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(Constants.KEY_USER);
        if (user == null) {
            user = new User();
            user.setRole(Role.GUEST);
            session.setAttribute(Constants.KEY_USER, user);
            RequestDispatcher dispatcher = req.getServletContext()
                    .getRequestDispatcher(Constants.JUMP_MAIN);
            dispatcher.forward(req, resp);
            return;
        }
        chain.doFilter(req, resp);
    }
}

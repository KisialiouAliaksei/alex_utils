package by.gsu.epamlab.filters;

import by.gsu.epamlab.controllers.Constants;
import by.gsu.epamlab.controllers.ConstantsJSP;
import by.gsu.epamlab.model.beans.Role;
import by.gsu.epamlab.model.beans.User;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by alex on 25.06.2016.
 */
public class FilterRegistration extends AbstractFilter {


    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User userSession = (User) session.getAttribute(ConstantsJSP.KEY_USER);
        if(Role.GUEST == userSession.getRole() || Role.ADMIN == userSession.getRole()){
            chain.doFilter(req, resp);
            return;
        }
        else
        {
            RequestDispatcher dispatcher = req.getServletContext()
                    .getRequestDispatcher(Constants.JUMP_MAIN);
            dispatcher.forward(req, resp);
        }

    }



}

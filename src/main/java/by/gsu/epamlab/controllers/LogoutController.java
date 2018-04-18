package by.gsu.epamlab.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Created by alex on 11.06.2016.
 */
public class LogoutController extends AbstractController {
    protected void performTask(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute(Constants.KEY_TICKETS);
        request.getSession(true).invalidate();
        jumpRedirect(Constants.JUMP_START,response);
    }

}

package by.gsu.epamlab.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by alex on 09.06.2016.
 */


public abstract class AbstractController extends HttpServlet {

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            performTask(request, response);
        }


        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            performTask(request, response);
        }

        protected void jumpAndMessage(String url, String message, HttpServletRequest request,
                                      HttpServletResponse response) throws ServletException, IOException {
            request.setAttribute(Constants.KEY_MESSAGE, message);
            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
            rd.forward(request, response);
        }

        protected void jumpPage(String url, HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {
            jumpAndMessage(url, Constants.KEY_EMPTY, request, response);
        }
        protected void jumpRedirect(String url,HttpServletResponse response) throws IOException {
            response.sendRedirect(Constants.ROOT_PROJECT + url);
        }

        protected abstract void performTask(HttpServletRequest request,
                                            HttpServletResponse response) throws ServletException, IOException;




}

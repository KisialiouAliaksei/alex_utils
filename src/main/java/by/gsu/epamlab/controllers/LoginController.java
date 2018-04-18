package by.gsu.epamlab.controllers;

import by.gsu.epamlab.ifaces.IUserDAO;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.exceptions.DaoException;
import by.gsu.epamlab.model.exceptions.DataBaseConnectionException;
import by.gsu.epamlab.model.exceptions.ValidateException;
import by.gsu.epamlab.model.factory.UserFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by alex on 09.06.2016.
 */
public class LoginController extends NoGetAbstractController {
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submit = request.getParameter(ConstantsJSP.KEY_SUBMIT_PRESSED);
        if (submit == null) {
            jumpPage(Constants.JUMP_MAIN, request, response);
            return;
        }
        try {
            String login = request.getParameter(ConstantsJSP.KEY_LOGIN);
            String password = request.getParameter(ConstantsJSP.KEY_PASSWORD);
            checkInput(login,password);
            IUserDAO userDAO = UserFactory.getClassFromUserFActory();
            User user = userDAO.getUser(login, password);
            if (user.getRole() == null) {
                throw new ValidateException(Constants.ERROR_PASS_WRONG);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute(Constants.KEY_USER, user);
            }
            jumpPage(Constants.JUMP_MAIN, request, response);
            return;

        } catch (ValidateException |DataBaseConnectionException | DaoException e) {
            jumpAndMessage(Constants.JUMP_JSP_MAIN,e.getMessage(), request, response);
            return;
        }
    }
    private void checkInput(String login, String password)
            throws ServletException, IOException, ValidateException, DaoException
    {
        if (login == null || password == null) {
            throw new ValidateException(Constants.ERROR_NULL);
        }
        login = login.trim();
        if (Constants.KEY_EMPTY.equals(login)) {
            throw new ValidateException(Constants.ERROR_EMPTY_LOGIN);
        }
    }
}

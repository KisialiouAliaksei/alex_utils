package by.gsu.epamlab.controllers;

import by.gsu.epamlab.ifaces.IUserDAO;
import by.gsu.epamlab.model.beans.Role;
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
 * Created by alex on 11.06.2016.
 */

public class RegistrationController extends AbstractController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submit = request.getParameter(ConstantsJSP.KEY_SUBMIT_REGISTR);
        if(submit == null){
            jumpPage(Constants.JUMP_JSP_REGISTRATION,request, response);
            return;
        }
        String password = request.getParameter(ConstantsJSP.KEY_PASSWORD);
        String password2 = request.getParameter(ConstantsJSP.KEY_PASSWORD_2);
        String login = request.getParameter(ConstantsJSP.KEY_LOGIN);
        String phone = request.getParameter(ConstantsJSP.KEY_PHONE);
        IUserDAO userDAO = UserFactory.getClassFromUserFActory();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.KEY_USER);
        if( user != null && Role.ADMIN == user.getRole()){
            try {
                checkInput(userDAO, login, password, password2, phone);
                String role = Role.COURIER.toString();
                if (userDAO.addUser(login, password,role, phone)) {
                    jumpRedirect(Constants.JUMP_MAIN, response);
                }
                else throw new ValidateException(Constants.ERROR_LOGIN_ADD);

            }catch (ValidateException |DataBaseConnectionException | DaoException e) {
                jumpAndMessage(Constants.JUMP_JSP_REGISTRATION,e.getMessage(),
                        request, response);
            }
        }
        else{
            try {
                checkInput(userDAO, login, password, password2, phone);
                String role = Role.USER.toString();
                if (!userDAO.addUser(login, password, role,phone)) {
                    throw new ValidateException(Constants.ERROR_LOGIN_ADD);
                }
                User userSession = new User(login, Role.USER);
                session.setAttribute(Constants.KEY_USER, userSession);
                jumpRedirect(Constants.JUMP_MAIN, response);

            } catch (ValidateException |DataBaseConnectionException | DaoException e) {
                jumpAndMessage(Constants.JUMP_JSP_REGISTRATION,e.getMessage(),
                        request, response);
                return;
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submit = request.getParameter(ConstantsJSP.KEY_SUBMIT_PRESSED);
        if(submit == null){
            jumpPage(Constants.JUMP_JSP_REGISTRATION,request, response);
            return;
        }
        else {
            jumpPage(Constants.JUMP_MAIN,request, response);
            return;
        }

    }

    private void checkInput(IUserDAO userDAO, String login, String password, String password2, String phone)
            throws ServletException, IOException, ValidateException, DaoException, DataBaseConnectionException {

        if (login == null || password == null || password2 == null || phone == null) {
            throw new ValidateException(Constants.ERROR_NULL);
        }

        login = login.trim();
        if (Constants.KEY_EMPTY.equals(login)) {
            throw new ValidateException(Constants.ERROR_EMPTY_LOGIN);
        }
        if (Constants.KEY_EMPTY.equals(password)) {
            throw new ValidateException(Constants.ERROR_EMPTY_PASSWORD);
        }
        if (Constants.KEY_EMPTY.equals(password2)) {
            throw new ValidateException(Constants.ERROR_EMPTY_PASSWORD);
        }
        if (Constants.KEY_EMPTY.equals(phone)) {
            throw new ValidateException(Constants.ERROR_EMPTY_PASSWORD);
        }
        if(!password2.equals(password)) {
            throw new ValidateException(Constants.ERROR_PASS_REPEAT);
        }
        if(!userDAO.isLoginNew(login)) {
            throw new ValidateException(Constants.ERROR_LOGIN_EXIST);
        }

    }

}

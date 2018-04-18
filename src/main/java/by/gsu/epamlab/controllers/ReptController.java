package by.gsu.epamlab.controllers;

import by.gsu.epamlab.ifaces.ITicketDAO;
import by.gsu.epamlab.ifaces.IUserDAO;
import by.gsu.epamlab.model.beans.TicketPurchased;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.exceptions.DaoException;
import by.gsu.epamlab.model.exceptions.DataBaseConnectionException;
import by.gsu.epamlab.model.factory.TicketFactory;
import by.gsu.epamlab.model.factory.UserFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by alex on 30.06.2016.
 */
public class ReptController extends AbstractController {
    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.KEY_USER);
        String dateMakeOrder = request.getParameter(Constants.KEY_DATE_FOR_COURIER);
        ITicketDAO iTicketDAO = TicketFactory.getClassFromTicketFactory();
        IUserDAO iUserDAO = UserFactory.getClassFromUserFActory();
        if(dateMakeOrder != null){
            try {
                Map<String, String> mapPhone = iUserDAO.getPhone(user);
                List<TicketPurchased>  tickets = iTicketDAO.getTickets(dateMakeOrder);
                request.setAttribute(Constants.KEY_DATE, dateMakeOrder);
                request.setAttribute(Constants.KEY_TICKETS_REPT, tickets);
                request.setAttribute(Constants.KEY_PHONES, mapPhone);
                jumpPage(Constants.JUMP_JSP_REPORT,request,response);
                return;
            } catch (DataBaseConnectionException | DaoException e) {
                jumpAndMessage(Constants.JUMP_JSP_REPORT,e.getMessage(),
                        request, response);
                return;
            }
        } else{
            jumpPage(Constants.JUMP_MAIN, request, response);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ITicketDAO iTicketDAO = TicketFactory.getClassFromTicketFactory();
        String dateForRept = request.getParameter(Constants.KEY_DATE_FOR_REPT);
        String paid = request.getParameter(ConstantsJSP.KEY_PAID);
        if (paid != null){
            String[] purchasedticket = request.getParameterValues(ConstantsJSP.KEY_TICKETS_PAID);
            if (purchasedticket == null) {
                jumpAndMessage(Constants.JUMP_JSP_MAIN,Constants.ERROR_TICKETS_NOT_SELECT, request, response);
                return;
            }
            try {
                if(iTicketDAO.setPurchasedTicked(purchasedticket)){
                    jumpRedirect(Constants.JUMP_MAIN,response);
                }

            } catch (DataBaseConnectionException | DaoException e) {
                jumpAndMessage(Constants.JUMP_JSP_REPORT,e.getMessage(),
                        request, response);
                return;
            }
        } else if(dateForRept != null){
            ITicketDAO reptDAO = TicketFactory.getClassFromTicketFactory();
            try {
                reptDAO.createRept(dateForRept);
                jumpRedirect(Constants.JUMP_MAIN,response);
            } catch (DaoException | DataBaseConnectionException e) {
                jumpAndMessage(Constants.JUMP_MAIN,e.getMessage(),request, response);
                return;
            }
        }
        else {
            jumpPage(Constants.JUMP_MAIN,request, response);
            return;
        }

    }
}

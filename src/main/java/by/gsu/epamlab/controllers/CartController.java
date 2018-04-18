package by.gsu.epamlab.controllers;

import by.gsu.epamlab.ifaces.ITicketDAO;
import by.gsu.epamlab.model.beans.Premiere;
import by.gsu.epamlab.model.beans.Ticket;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.exceptions.DaoException;
import by.gsu.epamlab.model.exceptions.DataBaseConnectionException;
import by.gsu.epamlab.model.exceptions.ValidateException;
import by.gsu.epamlab.model.factory.TicketFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 22.06.2016.
 */
public class CartController extends AbstractController {
    private List<Ticket> ticketList;

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ITicketDAO iTicketDAO = TicketFactory.getClassFromTicketFactory();
        ticketList = (List<Ticket>) session.getAttribute(Constants.KEY_TICKETS);
        if(ticketList == null){
            ticketList = new ArrayList<>();
        }
        String makeOrder = request.getParameter(ConstantsJSP.KEY_SUBMIT_ORDERS);
        if(makeOrder == null){
            List<Premiere> premiereList = (List<Premiere>) session.getAttribute(Constants.KEY_PREMIERES);
            String seat = request.getParameter(Constants.KEY_SEAT);
            User user = (User)session.getAttribute(Constants.KEY_USER);
            String category = request.getParameter(ConstantsJSP.KEY_CATEGORY_SEAT);
            String date = request.getParameter(Constants.KEY_DATE);
            String premiere = request.getParameter(Constants.KEY_PREMIERE);
            String price = request.getParameter(Constants.KEY_PRICE);

            try {
                CheckInputForControllers.checkPremieres(date, premiere, premiereList);
                if(!iTicketDAO.isTicketBuy(Integer.valueOf(seat),date, new Premiere(premiere),category)){
                    jumpAndMessage(Constants.JUMP_JSP_MAIN,Constants.ERROR_TICKET_PAID,request, response);
                    return;
                }
            } catch (DataBaseConnectionException | DaoException | ValidateException e) {
                jumpAndMessage(Constants.JUMP_JSP_MAIN,e.getMessage(),request, response);
                return;
            }
            Ticket ticket = new Ticket(user.getLogin(),seat, category,date,premiere, price);
            if(!ticketList.contains(ticket)){
                ticketList.add(ticket);
            }
            session.setAttribute(Constants.KEY_TICKETS, ticketList);
            jumpPage(Constants.JUMP_JSP_CART, request, response);
            return;
        } else{
            jumpPage(Constants.JUMP_MAIN, request, response);
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String makeOrder = request.getParameter(ConstantsJSP.KEY_SUBMIT_ORDERS);
        String clear = request.getParameter(ConstantsJSP.KEY_CLEAR);
        if("clear".equals(clear)){
            if(ticketList != null){
                ticketList.clear();
            }
            jumpPage(Constants.JUMP_JSP_CART, request, response);
            return;
        }
        if(makeOrder != null) {
            ITicketDAO iTicketDAO = TicketFactory.getClassFromTicketFactory();
            String[] tickets = request.getParameterValues(ConstantsJSP.KEY_PURCHASES);
            if (tickets == null) {
                jumpAndMessage(Constants.JUMP_JSP_CART, Constants.ERROR_EMPTY_TICKETS, request, response);
                return;
            }
            try {
                List<Ticket> listClear = iTicketDAO.addTicket(tickets);
                ticketList.removeAll(listClear);
                jumpRedirect(Constants.JUMP_MAIN, response);

            } catch (DaoException | DataBaseConnectionException e) {
                jumpAndMessage(Constants.JUMP_JSP_MAIN, e.getMessage(), request, response);
                return;
            }
        } else{
            jumpPage(Constants.JUMP_MAIN, request, response);
            return;
        }
    }
}

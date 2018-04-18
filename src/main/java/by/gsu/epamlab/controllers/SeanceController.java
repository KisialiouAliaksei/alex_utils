package by.gsu.epamlab.controllers;

import by.gsu.epamlab.ifaces.ISeatDAO;
import by.gsu.epamlab.ifaces.ITicketDAO;
import by.gsu.epamlab.model.beans.Premiere;
import by.gsu.epamlab.model.beans.SeatClass;
import by.gsu.epamlab.model.beans.Seance;
import by.gsu.epamlab.model.exceptions.DaoException;
import by.gsu.epamlab.model.exceptions.DataBaseConnectionException;
import by.gsu.epamlab.model.exceptions.ValidateException;
import by.gsu.epamlab.model.factory.SeatClassFactory;
import by.gsu.epamlab.model.factory.TicketFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 15.06.2016.
 */
public class SeanceController extends AbstractController {
    private List<SeatClass> seats;

    @Override
    public void init() throws ServletException {
        String realPath = getServletConfig().getServletContext().getRealPath(Constants.SLASH) +
                Constants.RES_PACKAGE + Constants.RES_SEAT_CLASS;
        ISeatDAO seatDAO = SeatClassFactory.getClassFromSeatClassFactory();
        try {
            seats = seatDAO.getSeats(realPath);
        } catch (DaoException e) {
            System.err.println(e);
        }
    }

    @Override
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(seats == null){
            seats = new ArrayList<>();
        }
        HttpSession session = request.getSession();
        List<Premiere> premiereList = (List<Premiere>) session.getAttribute(Constants.KEY_PREMIERES);
        String date = request.getParameter(Constants.KEY_DATE);
        String prem = request.getParameter(Constants.KEY_PREMIERE);
        ITicketDAO ticketDAO = TicketFactory.getClassFromTicketFactory();
        try {
            checkInput(date, prem);
            CheckInputForControllers.checkPremieres(date, prem, premiereList);
            Seance seance = ticketDAO.showAviableTickets(date, prem, seats);
            request.setAttribute(ConstantsJSP.KEY_SEANCE, seance);
        } catch (DaoException | DataBaseConnectionException | ValidateException e) {
            jumpAndMessage(Constants.JUMP_JSP_MAIN,e.getMessage(), request, response);
        }
        jumpPage(Constants.JUMP_JSP_SEANCE, request, response);
    }

    private void checkInput(String data, String prem)
            throws ServletException, IOException, ValidateException, DaoException
    {
        if (prem == null || data == null) {
            throw new ValidateException(Constants.ERROR_NULL_PREMIERE);
        }
        prem = prem.trim();
        data = data.trim();

        if (Constants.KEY_EMPTY.equals(prem) || Constants.KEY_EMPTY.equals(data)) {
            throw new ValidateException(Constants.ERROR_EMPTY_PREMIERE);
        }

    }
}

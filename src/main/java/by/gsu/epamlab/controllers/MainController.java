package by.gsu.epamlab.controllers;

import by.gsu.epamlab.ifaces.IPrimiereDAO;
import by.gsu.epamlab.model.beans.Premiere;
import by.gsu.epamlab.model.exceptions.DaoException;
import by.gsu.epamlab.model.factory.PremiereFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex on 13.06.2016.
 */
public class MainController extends AbstractController{
    private List<Premiere> premieres;
    @Override
    public void init() throws ServletException {
        String realPath = getServletConfig().getServletContext().getRealPath(Constants.SLASH) +
                Constants.RES_PACKAGE + Constants.RES_PREMIERES;
        IPrimiereDAO primiereDAO = PremiereFactory.getClassFromPrimiereFctory();
        try {
            premieres = primiereDAO.getPremieres(realPath);
        } catch (DaoException e) {
            System.err.println(e);
        }
    }

    protected void performTask(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {
        if(premieres == null){
            premieres = new ArrayList<>();
        }
        HttpSession session = request.getSession();
        session.setAttribute(Constants.KEY_PREMIERES, premieres);
        jumpPage(Constants.JUMP_JSP_MAIN, request, response);
    }
}

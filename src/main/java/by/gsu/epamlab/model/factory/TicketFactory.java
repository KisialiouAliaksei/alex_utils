package by.gsu.epamlab.model.factory;

import by.gsu.epamlab.ifaces.ITicketDAO;
import by.gsu.epamlab.model.impl.DBTicketImpl;

/**
 * Created by alex on 17.06.2016.
 */
public class TicketFactory {
    public static ITicketDAO getClassFromTicketFactory(){
        return new DBTicketImpl();
    }
}

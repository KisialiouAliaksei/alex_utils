package by.gsu.epamlab.model.factory;

import by.gsu.epamlab.ifaces.ISeatDAO;
import by.gsu.epamlab.model.impl.XMLseatClassesImpl;

/**
 * Created by alex on 13.06.2016.
 */
public class SeatClassFactory {
    public static ISeatDAO getClassFromSeatClassFactory(){
        return new XMLseatClassesImpl();

    }

}

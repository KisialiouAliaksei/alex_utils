package by.gsu.epamlab.model.factory;

import by.gsu.epamlab.ifaces.IPrimiereDAO;
import by.gsu.epamlab.model.impl.XMLprimiereImpl;

/**
 * Created by alex on 13.06.2016.
 */
public class PremiereFactory {
    public static IPrimiereDAO getClassFromPrimiereFctory(){
        return new XMLprimiereImpl();
    }

}

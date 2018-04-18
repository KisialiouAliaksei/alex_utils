package by.gsu.epamlab.model.factory;

import by.gsu.epamlab.ifaces.IUserDAO;
import by.gsu.epamlab.model.impl.DBUserImpl;

/**
 * Created by alex on 09.06.2016.
 */
public class UserFactory {
    public static IUserDAO getClassFromUserFActory(){
        return new DBUserImpl();
        //return new MemoryUserImpl();
    }
}

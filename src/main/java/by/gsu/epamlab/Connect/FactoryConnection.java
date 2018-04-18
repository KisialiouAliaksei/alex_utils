package by.gsu.epamlab.Connect;

import by.gsu.epamlab.model.exceptions.DataBaseConnectionException;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by alex on 11.07.2016.
 */
public class FactoryConnection {
    public static Connection getConnectionFromFactory() throws DataBaseConnectionException, SQLException, NamingException {
        return ConnectionService.getConnection();
        //return ConnectionPullService.getConnection();

    }
}

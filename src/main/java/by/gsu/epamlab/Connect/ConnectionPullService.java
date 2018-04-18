package by.gsu.epamlab.Connect;

import by.gsu.epamlab.controllers.Constants;
import by.gsu.epamlab.model.exceptions.DataBaseConnectionException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Created by alex on 11.06.2016.
 */

public class ConnectionPullService {

    private ConnectionPullService() {
    }

    public static Connection getConnection() throws SQLException, DataBaseConnectionException {
        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/PullDAO");
            return ds.getConnection();
        }
        catch (NamingException e) {
           throw new DataBaseConnectionException(e.getMessage());
        }
    }
    public static void closeConnection(Connection cn) {
        try {
            if (cn != null) {
                cn.close();
            }
        } catch (SQLException e) {
            System.err.println(Constants.ERROE_CLOSING_RESURS + e);

        }
    }

    public static void closeStatement(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            System.err.println(Constants.ERROE_CLOSING_RESURS + e);

        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.err.println(Constants.ERROE_CLOSING_RESURS + e);

        }
    }

    public static void closeService(Connection cn, Statement st, ResultSet rs) {
        closeResultSet(rs);
        closeStatement(st);
        closeConnection(cn);
    }

    public static void closeService(Connection cn, Statement st) {
        closeStatement(st);
        closeConnection(cn);
    }
}

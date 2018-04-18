package by.gsu.epamlab.Connect;

import by.gsu.epamlab.controllers.Constants;
import by.gsu.epamlab.model.exceptions.DataBaseConnectionException;

import java.sql.*;

/**
 * Created by alex on 09.06.2016.
 */
public class ConnectionService {
    private final static String DB_URL = "jdbc:mysql://localhost:3306/jeeproject";
    private final static String USER = "root";
    private final static String PASSWORD = "root";
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws DataBaseConnectionException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch(SQLException e) {
            throw new DataBaseConnectionException(Constants.CONNECTION_ERROR);
        }
        return connection;
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

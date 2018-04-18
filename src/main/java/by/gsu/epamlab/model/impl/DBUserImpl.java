package by.gsu.epamlab.model.impl;

import by.gsu.epamlab.Connect.ConnectionService;
import by.gsu.epamlab.Connect.FactoryConnection;
import by.gsu.epamlab.controllers.Constants;
import by.gsu.epamlab.ifaces.IUserDAO;
import by.gsu.epamlab.model.beans.Role;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.exceptions.DaoException;
import by.gsu.epamlab.model.exceptions.DataBaseConnectionException;
import javax.naming.NamingException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 09.06.2016.
 */
public class DBUserImpl implements IUserDAO {
    public static final String TABLE_NAME_USERS = "jeeproject.users";
    private static final String NAME_FIELD = "name";
    private static final String ROLE_FIELD = "role";
    private static final String PASSWORD_FIELD = "password";
    private static final String PHONE_FIELD = "phone";

    @Override
    public User getUser(String login, String password) throws DaoException, DataBaseConnectionException {
        Role role = null;
        ResultSet rs = null;
        Statement st = null;
        Connection cn = null;
        String query = "select * from " + TABLE_NAME_USERS + " where " + NAME_FIELD
                + "='" + login + "' and " + PASSWORD_FIELD + "='" + password + "';";
        try {
            cn = FactoryConnection.getConnectionFromFactory();
            st = cn.createStatement();
            synchronized (DBUserImpl.class){
                rs = st.executeQuery(query);
                if(rs.next()) {
                    role = Role.valueOf(rs.getString(ROLE_FIELD).toUpperCase());
                }
            }
            return new User(login, role);
        } catch(SQLException | NamingException e) {
            throw new DaoException(e.getMessage());
        } finally {
            ConnectionService.closeService(cn, st, rs);
        }
    }
    @Override
    public boolean isLoginNew(String login) throws DaoException, DataBaseConnectionException {
        Statement st = null;
        ResultSet rs = null;
        boolean isNew = true;
        Connection cn = null;

        String query = "select * from " + TABLE_NAME_USERS + " where " + NAME_FIELD
                + "='" + login + "';";
        try {
            cn = FactoryConnection.getConnectionFromFactory();
            st = cn.createStatement();
            synchronized (DBUserImpl.class) {
                rs = st.executeQuery(query);
                if (rs.next()) {
                    isNew = false;
                }
            }
            return isNew;
        }catch(SQLException | NamingException e) {
            throw new DaoException(Constants.ERROR_LOGIN_EXIST);
        }
        finally {
            ConnectionService.closeService(cn, st, rs);
        }
    }

    @Override
    public  boolean addUser(String login, String password, String role, String phone) throws DaoException, DataBaseConnectionException {
        Statement st = null;
        Connection cn = null;
        String query = "insert into "  + TABLE_NAME_USERS + "(" + NAME_FIELD + "," + PASSWORD_FIELD +
        "," + ROLE_FIELD + "," + PHONE_FIELD + ")" +
                " values ('" + login + "','" + password + "','" +
                role + "','" + phone + "');";
        if(isLoginNew(login)) {
            try {
                cn = FactoryConnection.getConnectionFromFactory();
                st = cn.createStatement();
                synchronized (DBUserImpl.class){
                    st.executeUpdate(query);
                }
                return true;
            } catch(SQLException | NamingException e) {
                throw new DaoException(e.getMessage());
            }
            finally {
                ConnectionService.closeService(cn, st);
            }
        }
        return false;
    }

    @Override
    public Map<String, String> getPhone(User user) throws DaoException, DataBaseConnectionException {
        Map<String, String> mapPhone = new HashMap<>();
        ResultSet rs = null;
        Statement st = null;
        Connection cn = null;
        String query = "select " +NAME_FIELD + "," + PHONE_FIELD +  " from " + TABLE_NAME_USERS + ";";
        try {
            cn = FactoryConnection.getConnectionFromFactory();
            st = cn.createStatement();
            synchronized (DBUserImpl.class){
                rs = st.executeQuery(query);
                while(rs.next()) {
                   mapPhone.put(rs.getString(1),rs.getString(2));
                }
            }
            return mapPhone;
        } catch(SQLException | NamingException e) {
            throw new DaoException(e.getMessage());
        } finally {
            ConnectionService.closeService(cn, st, rs);
        }
    }
}

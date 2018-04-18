package by.gsu.epamlab.ifaces;

import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.exceptions.DaoException;
import by.gsu.epamlab.model.exceptions.DataBaseConnectionException;

import java.util.Map;

/**
 * Created by alex on 09.06.2016.
 */
public interface IUserDAO {
    User getUser(String name, String password) throws DaoException, DataBaseConnectionException;
    boolean isLoginNew(String login) throws DaoException, DataBaseConnectionException;
    boolean addUser(String login, String password, String role,String phone) throws DaoException, DataBaseConnectionException;
    Map<String, String> getPhone(User user) throws DaoException, DataBaseConnectionException;


}

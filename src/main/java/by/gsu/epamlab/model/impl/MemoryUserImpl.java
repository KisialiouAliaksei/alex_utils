package by.gsu.epamlab.model.impl;

import by.gsu.epamlab.ifaces.IUserDAO;
import by.gsu.epamlab.model.beans.Role;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.exceptions.DaoException;
import by.gsu.epamlab.model.exceptions.DataBaseConnectionException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 09.06.2016.
 */
public class MemoryUserImpl implements IUserDAO {
    private static Map<String, String> user = new HashMap<>();
    static {
        user.put("alex", "123");
        user.put("petr", "321");
    }

    @Override
    public boolean isLoginNew(String login) {
        return !(user.containsKey(login));
    }

    @Override
    public User getUser(String login, String password) {
        if (!isLoginNew(login))  return new User(login,Role.USER);
        else return null;
    }

    @Override
    public boolean addUser(String login, String password, String role, String phone) {
        if (isLoginNew(login)){
            user.put(login, password);
            return true;
        }
        else return false;
    }

    @Override
    public Map<String, String> getPhone(User user) throws DaoException, DataBaseConnectionException {
        return null;
    }
}

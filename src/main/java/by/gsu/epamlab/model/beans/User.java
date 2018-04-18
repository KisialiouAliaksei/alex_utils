package by.gsu.epamlab.model.beans;

/**
 * Created by alex on 09.06.2016.
 */
public class User {
    private String login;
    private Role role;

    public User() {
    }

    public User(String login, Role role) {
        this.login = login;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }



    public Role getRole() {
        return role;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", role=" + role +
                '}';
    }
}

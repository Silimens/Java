package beans.model;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String login;
    private String fullName;
    private String password;

    public User() {}

    public User(int id, String login, String fullName, String password) {
        this.id = id;
        this.login = login;
        this.fullName = fullName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User {id=" + getId() +
            ",login=" + getLogin() +
            ",fullName=" + getFullName() +
            "}";
    }
}

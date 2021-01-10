package model;

import java.util.Objects;

public class User {
    private String login;
    private String password;

    public User(String login, String password)
    {
        this.login = login;
        this.password = password;
    }

    public String getUserLogin() {
        return login;
    }

    public void setUserLogin(String login) {
        this.login = login;
    }

    public String getUserPassword() {
        return password;
    }

    public void setUserPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "  login ='" + login + '\'' +
                "  password ='" + password +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return  Objects.equals(getUserLogin(), user.getUserLogin()) &&
                Objects.equals(getUserPassword(),user.getUserPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserLogin(), getUserPassword());
    }
}

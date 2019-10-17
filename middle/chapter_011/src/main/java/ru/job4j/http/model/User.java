package ru.job4j.http.model;

import java.util.Date;
import java.util.Objects;

/**
 * Создать модель User c полями id, name, login, email, createDate. Это модель данных.
 * <p>
 * Created by roman.pogorelov on 30.09.2019
 */
public class User {
    public static long globalId;
    private long id;
    private String name;
    private String login;
    private String password;
    private String email;
    private String role;
    private String country;
    private String city;
    private Date createDate;

    public User() {
    }

    public User(String name, String login, String email) {
        this(++globalId, name, login, email, new Date());
    }

    public User(long id, String name, String login, String email, Date createDate) {
        this(id, name, login, "password", email, "everyone", createDate, "Russia", "Msc");
    }

    @SuppressWarnings({"ParameterNumber"})
    public User(long id, String name, String login, String password, String email, String role, Date createDate, String country, String city) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
        this.createDate = createDate;
        this.country = country;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + ", email='" + email + '\''
                + ", country='" + country + '\''
                + ", city='" + city + '\''
                + ", role='" + role + '\''
                + ", createDate=" + createDate
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

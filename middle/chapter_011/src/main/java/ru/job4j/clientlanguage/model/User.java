package ru.job4j.clientlanguage.model;

public class User {
    private String name;
    private String lastName;
    private String sex;
    private String description;

    public User() {
    }

    public User(String name, String lastName, String description, String sex) {
        this.name = name;
        this.lastName = lastName;
        this.description = description;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", lastName='" + lastName + '\''
                + ", description='" + description + '\''
                + ", sex='" + sex + '\''
                + '}';
    }
}

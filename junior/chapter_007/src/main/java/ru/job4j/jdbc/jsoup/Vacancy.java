package ru.job4j.jdbc.jsoup;

/**
 * Created by roman.pogorelov on 20.09.2019
 */
public class Vacancy {
    private long id;
    private String name;
    private String description;
    private String link;

    public Vacancy() {
    }

    public Vacancy(String name, String description, String link) {
        this.name = name;
        this.description = description;
        this.link = link;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}

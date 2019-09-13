package ru.job4j.jdbc.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * TODO Description
 * Created by roman.pogorelov on 12.09.2019
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Entries {
    @XmlElement(name = "entry")
    private List<Entry> entries;

    public Entries() {
    }

    public Entries(List<Entry> entries) {
        this.entries = entries;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}

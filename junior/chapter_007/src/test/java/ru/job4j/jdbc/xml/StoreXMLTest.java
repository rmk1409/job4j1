package ru.job4j.jdbc.xml;

import org.junit.AfterClass;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TODO Description
 * Created by roman.pogorelov on 13.09.2019
 */
public class StoreXMLTest {
    public static final File FILE = new File("toXml.txt");

    @AfterClass
    public static void finalization() {
        StoreXMLTest.FILE.deleteOnExit();
    }

    @Test
    public void save() throws JAXBException, IOException {
        List<Entry> entries = List.of(new Entry(1), new Entry(2), new Entry(3));
        new StoreXML(StoreXMLTest.FILE)
                .save(entries);
        List<String> actual = Files.readAllLines(Paths.get(StoreXMLTest.FILE.getAbsolutePath()));
        List<String> expected = List.of("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "<entries>", "    <entry>", "        <id>1</id>", "    </entry>", "    <entry>", "        <id>2</id>", "    </entry>", "    <entry>", "        <id>3</id>", "    </entry>", "</entries>");
        assertThat(actual, is(expected));
    }
}
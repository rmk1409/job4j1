package ru.job4j.jdbc.xml;

import org.junit.AfterClass;
import org.junit.Test;

import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * TODO Description
 * Created by roman.pogorelov on 13.09.2019
 */
public class ConvertXSQTTest {
    public static final File FILE = new File("convertedXml.txt");

    @AfterClass
    public static void finalization() {
        ConvertXSQTTest.FILE.deleteOnExit();
    }

    @Test
    public void convert() throws IOException, TransformerException {
        new ConvertXSQT()
                .convert(new File(this.getClass().getClassLoader().getResource("toXml.txt").getFile()), ConvertXSQTTest.FILE, new File(this.getClass().getClassLoader().getResource("scheme.xsl").getFile()));
        List<String> actual = Files.readAllLines(Paths.get(ConvertXSQTTest.FILE.getAbsolutePath()));
        List<String> expected = List.of("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "<entries>", "    <entry field=\"0\"/>", "    <entry field=\"1\"/>", "    <entry field=\"2\"/>", "</entries>");
        assertThat(actual, is(expected));
    }
}
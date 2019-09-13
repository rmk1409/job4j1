package ru.job4j.jdbc.xml;

import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TODO Description
 * Created by roman.pogorelov on 13.09.2019
 */
public class SaxParserTest {

    @Test
    public void getSum() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SaxParser saxp = new SaxParser();
        File file = new File(this.getClass().getClassLoader().getResource("convertedXml.txt").getFile());
        parser.parse(file, saxp);
        assertThat(saxp.getSum(), is(3L));
    }
}
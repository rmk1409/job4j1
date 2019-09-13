package ru.job4j.jdbc.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * TODO Description
 * Created by roman.pogorelov on 13.09.2019
 */
public class SaxParser extends DefaultHandler {
    private long sum;

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        this.sum = 0;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        for (int i = 0; i < attributes.getLength(); i++) {
            sum += Long.parseLong(attributes.getValue(i));
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SaxParser saxp = new SaxParser();
        parser.parse(new File("C:\\projects\\job4j\\hi2.txt"), saxp);
    }

    public long getSum() {
        return sum;
    }
}

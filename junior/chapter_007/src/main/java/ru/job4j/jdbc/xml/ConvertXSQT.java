package ru.job4j.jdbc.xml;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

/**
 * Created by roman.pogorelov on 12.09.2019
 */
public class ConvertXSQT {
    /**
     * Читает файл source и преобразовывает его в файл dest за счет XSTL схемы scheme.
     *
     * @param source is used to read
     * @param dest   is used to write
     * @param scheme way to transformation
     */
    public void convert(File source, File dest, File scheme) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(new StreamSource(scheme));
        transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.transform(new StreamSource(source), new StreamResult(System.out));
        transformer.transform(new StreamSource(source), new StreamResult(dest));
    }

    public static void main(String[] args) throws TransformerException {
        new ConvertXSQT()
                .convert(new File("C:\\projects\\job4j\\hi.txt"), new File("hi2.txt"), new File("C:\\projects\\job4j\\junior\\chapter_007\\src\\main\\resources\\scheme.xsl"));
    }
}

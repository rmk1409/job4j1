package ru.job4j.jdbc.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

/**
 * Created by roman.pogorelov on 12.09.2019
 */
public class StoreXML {
    /**
     * Файл куда будет сохраняться данные.
     */
    private File target;

    public StoreXML(File target) {
        this.target = target;
    }

    /**
     * Сохраняет данные из list в файл target.
     *
     * @param list данные для сохранения
     */
    public void save(List<Entry> list) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Entries.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Entries container = new Entries(list);
//        jaxbMarshaller.marshal(container, System.out);
        jaxbMarshaller.marshal(container, this.target);
    }

    public static void main(String[] args) throws JAXBException {
        List<Entry> entries = new StoreSQL(new Config())
                .load();
        new StoreXML(new File("toXml.txt"))
                .save(entries);
    }
}

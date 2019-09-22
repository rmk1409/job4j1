package ru.job4j.jdbc.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Getting news from yandex.ru
 * <p>
 * Created by roman.pogorelov on 16.09.2019
 */
public class HelloJsoup {

    public static void main(String[] args) throws IOException {
//        Document doc = Jsoup.connect("http://eclipse.org").get();
//        String title = doc.title();
//        System.out.println("Title : " + title);


        Document doc = Jsoup.connect("https://yandex.ru").get();
        Elements elements = doc.select("#news_panel_news");

        for (Element element : elements.select("a")) {
            System.out.println(element.text());
        }
    }

}
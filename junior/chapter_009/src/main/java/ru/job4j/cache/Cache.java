package ru.job4j.cache;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 1. Создать структуру данных типа кеш. Кеш должен быть абстрактный.
 * То есть необходимо, что бы можно было задать ключ получения объекта кеша и в случае если его нет в памяти, задать поведение загрузки этого объекта в кеш.
 * <p>
 * 2. Создать программу эмулирующее поведение данного кеша. Программа должна считывать текстовые файлы из системы и выдавать текст при запросе имени файла.
 * Если в кеше файла нет. Кеш должен загрузить себе данные. По умолчанию в кеше нет ни одного файла. Текстовые файл должны лежать в одной директории.
 * Пример. Names.txt, Address.txt - файлы в системе. При запросе по ключу Names.txt - кеш должен вернуть содержимое файла Names.txt.
 * <p>
 * Created by roman.pogorelov on 19.09.2019
 */
public class Cache {
    private static final Logger LOG = Logger.getLogger(Cache.class.getName());
    private Map<String, SoftReference<String>> innerStorage = new HashMap<>();

    public Map<String, SoftReference<String>> getInnerStorage() {
        return innerStorage;
    }

    public String getValue(String key) {
        String result = "";
        SoftReference<String> cached = innerStorage.get(key);
        if (cached != null && cached.get() != null) {
            result = cached.get();
        } else {
            result = this.readFile(key);
            this.innerStorage.put(key, new SoftReference<>(result));
        }
        return result;
    }

    private String readFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(Cache.class.getClassLoader().getResource(fileName).toURI()));
        } catch (IOException | URISyntaxException e) {
            LOG.log(Level.ALL, e.getMessage(), e);
        }
        return String.join(System.lineSeparator(), lines);
    }
}

package ru.job4j.io;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 4. Архивировать проект. [#176770]
 * <p>
 * 1. Задана директория проекта, например: c:\project\job4j\
 * 2. В качестве ключа передается  расширения файлов, которые не нужно включать в архив.
 * 3. Архив должен сохранять структуру проекта.
 * 4. Запуск проекта java -jar pack.jar -d c:\project\job4j\ -e *.java -o project.zip
 * <p>
 * java -jar pack.jar - Это собранный jar.
 * <p>
 * -d - directory - которую мы хотим архивировать
 * -e - exclude - исключить файлы *.xml
 * -o - output - во что мы архивируем.
 * <p>
 * У вас должен быть класс new Args(agrs)
 * <p>
 * с методами directory() excule() output();
 * <p>
 * 5. Для архивации использовать классы ZipOutputStream.java.
 * 6. Создайте класс. Zip.
 * в нем должно быть два методы
 * <p>
 * List<File> seekBy(String root, String ext)
 * void pack(List<File> sources, File target)
 * <p>
 * Created by roman.pogorelov on 08.09.2019
 */
public class Zip {
    public static void main(String[] args) {
        String directory = "";
        String exclude = "";
        String output = "";
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-d":
                    directory = args[++i];
                    break;
                case "-e":
                    exclude = args[++i];
                    break;
                case "-o":
                    output = args[++i];
                    break;
                default:
                    break;
            }
        }
        Zip zip = new Zip();
        List<File> files = zip.seekBy(directory, exclude);
        zip.pack(files, new File(output));
    }

    public void pack(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            sources.forEach(
                    file -> {
                        try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                            zip.putNextEntry(new ZipEntry(file.getPath()));
                            zip.write(out.readAllBytes());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<File> seekBy(String root, Predicate<Path> predicate) {
        List<File> result = new ArrayList<>();

        try {
            result = Files.walk(Paths.get(root))
                    .filter(Files::isRegularFile)
                    .filter(predicate)
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<File> seekBy(String root, String ext) {
        return this.seekBy(root, i -> !FileSystems.getDefault().getPathMatcher(ext).matches(i));
    }
}

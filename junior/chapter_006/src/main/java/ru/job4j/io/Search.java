package ru.job4j.io;

import java.io.File;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 3. Сканирование файловой системы.[#176767]
 * <p>
 * Файловая система представляет собой древовидную структуру. В модуле "Коллекции Про" рассматривался алгоритм обхода дерева.
 * Этот алгоритм обхода в ширину.
 * В этом задании нужно написать метод, который возвращает список всех файлов с конкретным расширением.
 * Метод должен заходить во всех каталоги.
 * Для этого нужно использовать алгоритм обхода дерева в ширину.
 * <p>
 * class Search {
 * List<File> files(String parent, List<String> exts);
 * <p>
 * }
 * <p>
 * String parent - это путь до каталога, с которого нужно осуществлять поиск.
 * List<String> exts - это расширения файлов, которые мы хотим получить.
 * <p>
 * В этой задаче нужно использовать методы.
 * File file = new File(path);
 * file.listFiles() - возвращает список всех каталогов и файлов находящихся в папке  file.
 * file.isDirectory() - проверяет, что файл является директорией.
 * file.getName() - возвращает имя файла с расширением.
 * <p>
 * В этой задаче нужно написать тесты. Для тестов нужно создать временную структуру с файлами.
 * <p>
 * Структуру нужно создавать в папке System.getProperty("java.io.tmpdir")
 * <p>
 * Здесь нельзя использовать FileVisitor. Это задание на работу с деревом каталогов.
 * <p>
 * Created by roman.pogorelov on 07.09.2019
 */
class Search {
    public List<File> files(String parent, List<String> exts) {
        List<File> result = new ArrayList<>();
        Queue<File> queue = new LinkedList<>();
        queue.offer(new File(parent));
        while (!queue.isEmpty()) {
            File current = queue.poll();
            if (current.isDirectory()) {
                this.getDirectoryFiles(current, file -> true)
                        .forEach(queue::offer);
            } else if (checkExtension(current, exts)) {
                result.add(current);
            }
        }
        return result;
    }

    private List<File> getDirectoryFiles(File directory, Predicate<File> predicate) {
        return Arrays.stream(Objects.requireNonNull(directory.listFiles()))
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public boolean checkExtension(File file, List<String> exts) {
        boolean result = false;
        String name = file.getName();
        int i = name.lastIndexOf('.');
        if (i > 0) {
            String extension = name.substring(i + 1);
            boolean accordance = exts.stream().anyMatch(cur -> Objects.equals(extension, cur));
            if (accordance) {
                result = true;
            }
        }
        return result;
    }
}
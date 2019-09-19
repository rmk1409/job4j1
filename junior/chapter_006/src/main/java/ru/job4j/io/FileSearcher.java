package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Поиск файлов по критерию[#176776]
 * <p>
 * 1. Создать программу для поиска файла.
 * 2. Программа должна искать данные в заданном каталоге и подкаталогах.
 * 3. Имя файла может задаваться, целиком, по маске, по регулярному выражение(не обязательно).
 * 4. Программа должна собираться в jar и запускаться через java -jar find.jar -d c:/ -n *.txt -m -o log.txt
 * Ключи
 * -d - директория в которая начинать поиск.
 * -n - имя файл, маска, либо регулярное выражение.
 * -m - искать по маске, либо -f - полное совпадение имени. -r регулярное выражение.
 * -o - результат записать в файл.
 * 5. Программа должна записывать результат в файл.
 * 6. В программе должна быть валидация ключей и подсказка.
 * <p>
 * Created by roman.pogorelov on 19.09.2019
 */
public class FileSearcher {
    private static Zip zipper = new Zip();

    /**
     * Get list of files by full file str, regex or mask.
     *
     * @param full      whether search by full file str
     * @param regex     whether search by regex
     * @param mask      whether search by mask
     * @param directory start directory
     * @param str       file str
     * @return list of files
     */
    private List<File> getFiles(boolean full, boolean regex, boolean mask, String directory, String str) {
        List<File> result = new ArrayList<>();
        if (full) {
            result = zipper.seekBy(directory, i -> Objects.equals(str, i.getFileName().toString()));
        } else if (regex) {
            result = zipper.seekBy(directory, i -> i.getFileName().toString().matches(str));
        } else if (mask) {
            String fromMaskToRegEx = str.replace("?", ".?").replace("*", ".*?");
            result = zipper.seekBy(directory, i -> i.getFileName().toString().matches(fromMaskToRegEx));
        }
        return result;
    }

    /**
     * Reads files and returns list of all file data.
     *
     * @param files files to read
     * @return list with data of all files
     */
    private List<String> getData(List<File> files) {
        List<String> result = new ArrayList<>();
        files.forEach(i -> {
            try {
                result.addAll(Files.readAllLines(Paths.get(i.getPath())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return result;
    }

    /**
     * Scan directory, find all files, read them and combine all info in one output file.
     *
     * @param directory start directory
     * @param str       input string
     * @param full      whether search by full file name
     * @param regex     whether search by regex
     * @param mask      whether search by mask
     * @param output    output file name
     */
    public void search(String directory, String str, boolean full, boolean regex, boolean mask, String output) {
        if (this.validateKeys(full, regex, mask)) {
            List<File> files = this.getFiles(full, regex, mask, directory, str);
            List<String> data = this.getData(files);
            try {
                Files.write(Paths.get(output), data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Check keys.
     *
     * @param full
     * @param regex
     * @param mask
     * @return whether the necessary key present or not
     */
    private boolean validateKeys(boolean full, boolean regex, boolean mask) {
        boolean result = false;
        if (full || regex || mask) {
            result = true;
        } else {
            System.out.println(new StringJoiner(System.lineSeparator())
                    .add("Don't enough keys, should be one of the following:")
                    .add("'-m' for mask,")
                    .add("'-r' for regex,")
                    .add("'-f' for full name searching.")
                    .toString());
        }
        return result;
    }

    public static void main(String[] args) {
        String directory = "";
        String name = "";
        String output = "";
        boolean mask = false;
        boolean full = false;
        boolean regex = false;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-d":
                    directory = args[++i];
                    break;
                case "-o":
                    output = args[++i];
                    break;
                case "-n":
                    name = args[++i];
                    break;
                case "-m":
                    mask = true;
                    break;
                case "-f":
                    full = true;
                    break;
                case "-r":
                    regex = true;
                    break;
                default:
                    break;
            }
        }
        new FileSearcher().search(directory, name, full, regex, mask, output);
        new File(output).deleteOnExit();
    }
}

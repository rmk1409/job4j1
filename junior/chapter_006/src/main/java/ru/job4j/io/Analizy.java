package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Задание.
 * <p>
 * 1. Реализуйте метод unavailable.
 * source - имя файла лога
 * target - имя файла после анализа.
 * <p>
 * 2. Метод anavailable должен находить диапазоны, когда сервер не работал.
 * Сервер не работал. если status = 400 или 500.
 * Диапазон считается последовательность статусов 400 и 500
 * Например:
 * 200 10:56:01
 * 500 10:57:01
 * 400 10:58:01
 * 200 10:59:01
 * 500 11:01:02
 * 200 11:02:02
 * тут два периода - 10:57:01 до 10:59:01 и 11:01:02 до 11:02:02
 * Начальное время - это время когда статус 400 или 500. конечное время это когда статус меняется с 400 или 500 на 200 300.
 * <p>
 * 3. Результат анализа нужно записать в файл target.
 * Формат файла
 * начала периода;конец периода;
 * <p>
 * 4. Записать тесты.
 * <p>
 * Created by roman.pogorelov on 07.09.2019
 */
public class Analizy {
    public void unavailable(String source, String target) {
        String path = this.getResourcePath(source);
        try (
                BufferedReader read = new BufferedReader(new FileReader(path));
                PrintWriter out = new PrintWriter(new FileOutputStream(target))
        ) {
            List<String> lines = read.lines()
                    .collect(Collectors.toList());
            String start = "";
            String finish;
            boolean started = false;
            for (String str : lines) {
                String[] split = str.split(" ");
                if (!started && (Objects.equals("400", split[0]) || Objects.equals("500", split[0]))) {
                    start = split[1];
                    started = true;
                }
                if (started && (Objects.equals("200", split[0]) || Objects.equals("300", split[0]))) {
                    finish = split[1];
                    out.println(String.format("%s;%s", start, finish));
                    started = false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getResourcePath(String source) {
        return Analizy.class.getClassLoader().getResource(source).getFile();
    }

    public static void main(String[] args) {
//        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
//            out.println("15:01:30;15:02:32");
//            out.println("15:10:30;23:12:32");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        new Analizy().unavailable("server.lg", "unavailable.csv");
    }
}

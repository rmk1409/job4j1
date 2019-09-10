package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Создать программу 'Консольный чат'. Пользователь вводит слово-фразу, программа берет случайную фразу из текстового файла и выводит в ответ.
 * <p>
 * Программа замолкает если пользователь вводит слово «стоп», при этом он может продолжать отправлять сообщения в чат.
 * Если пользователь вводит слово «продолжить», программа снова начинает отвечать. При вводе слова «закончить» программа прекращает работу.
 * Запись диалога включая, слова-команды стоп/продолжить/закончить записать в текстовый лог.
 * <p>
 * Так делать не надо:
 * while (true) { - консольный чат должен явно выходить из цикла, не делайте вечный цикл.
 * <p>
 * Created by roman.pogorelov on 10.09.2019
 */
public class Chat {
    private String path;
    private String destination;
    public static final String STOP = "стоп";
    public static final String CONTINUE = "продолжить";
    public static final String END = "закончить";

    public Chat(String source, String destination) {
        this.path = Chat.class.getClassLoader().getResource(source).getFile();
        this.destination = destination;
    }

    public void run() {
        List<String> phrases = this.getAllPhrases(this.path);
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean stop = false;
        boolean exit;
        List<String> log = new ArrayList<>();
        do {
            input = scanner.nextLine();
            log.add(input);
            stop = Objects.equals(Chat.STOP, input)
                    || (stop && !Objects.equals(Chat.CONTINUE, input));
            exit = Objects.equals(Chat.END, input);
            if (!stop && !exit) {
                String phrase = phrases.get(new Random().nextInt(phrases.size()));
                log.add(phrase);
                System.out.println(phrase);
            }
        } while (!exit);
        this.writeToFile(log);
    }

    private void writeToFile(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileOutputStream(this.destination))) {
            log.forEach(writer::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private List<String> getAllPhrases(String path) {
        List<String> result = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(path));) {
            result = read.lines()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        new Chat("phrases.txt", "conversation.txt").run();
    }
}

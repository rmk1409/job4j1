package ru.job4j.solid.tdd;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 1. Генератор должен получать входную строку с ключами в тексте и список значений по этим ключам.
 * Например. Входящая строка String template = "I am a ${name}, Who are ${subject}? " и список значений ассоциированных по ключу name -> "Petr", subject -> "you"
 * На выходе должна быть строка - "I am Petr, Who are you?"
 * Другой пример. " Help, ${sos}, ${sos}, ${sos}", sos -> "Aaaa". Должно получится " Help, Ааа, Ааа, Ааа"
 * <p>
 * 2. Программа должна учитывать. что ключей нет в карте и кидать исключение.
 * <p>
 * 3. Программа должна учитываться. что в карте есть лишние ключи и тоже кидать исключение.
 * В качестве карты можно использовать Map<String, String>, либо Pair[] - Pair - объект из библиотеки guava.
 * <p>
 * Примечание. В этой задаче нужно использовать RegExp
 * private final Pattern KEYS = Pattern.compile(...); это должно быть поле. В задаче нужно объяснить почему.
 * <p>
 * Created by roman.pogorelov on 18.09.2019
 */
public class SimpleGenerator {
    private final static Pattern KEYS = Pattern.compile("\\$\\{\\w+\\}");

    public String generate(String template, Map<String, String> values) {
        Map<String, String> extraData = new HashMap<>(values);
        String result = template;
        Matcher matcher = SimpleGenerator.KEYS.matcher(result);
        while (matcher.find()) {
            String found = matcher.group();
            String key = found.substring(2, found.length() - 1);
            String value = values.get(key);
            if (value == null) {
                throw new IllegalArgumentException("No proper value in the map");
            }
            extraData.remove(key);
            result = result.replace(found, value);
            matcher = KEYS.matcher(result);
        }
        if (extraData.size() > 0) {
            throw new IllegalArgumentException("Map has extra data");
        }
        return result;
    }

    public static void main(String[] args) {
        new SimpleGenerator().generate("I am a ${name}, Who are ${subject}? ${name}", Map.of("name", "Petr", "subject", "you"));
    }
}

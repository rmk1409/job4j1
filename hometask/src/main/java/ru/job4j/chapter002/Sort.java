package ru.job4j.chapter002;

import java.util.*;

/**
 * В организации было решено ввести справочник подразделений. Коды подразделений представлены в виде массива строк вида:
 * <p>
 * “K1\SK1”
 * “K1\SK2”
 * “K1\SK1\SSK1”
 * “K1\SK1\SSK2”
 * “K2”
 * “K2\SK1\SSK1”
 * “K2\SK1\SSK2”
 * <p>
 * , где каждая строка имеет следующую структуру: каждая строка включает в себя код данного подразделения, а также все коды подразделений, которые включают в свою структуру данное подразделение (к примеру департамент K1 включает в себя службу SK1, включающую в себя отдел SSK1). Подразделения в одной строке разделены знаком “\”. Возможны случаи, когда в массиве отсутствуют строки с кодом верхнеуровнего подразделения (в показанном выше массиве есть строки с подразделением K1, но данный код подразделения не представлен отдельной строкой “K1”, аналогичный случай с кодом K2\SK1), в таком случае необходимо добавить строку с кодом данного подразделения и учитывать ее при сортировке.
 * <p>
 * Задача:
 * Реализовать возможность сортировки массива кодов подразделений по возрастанию и убыванию, при которых сохранялась бы иерархическая структура (показано далее в примерах сортировки), т.к. отсортированный массив используется для отображения категорий пользователю. Отсортированный тестовый массив должен иметь вид:
 * <p>
 * Сортировка по возрастанию:
 * <p>
 * “K1”
 * “K1\SK1”
 * “K1\SK1\SSK1”
 * “K1\SK1\SSK2”
 * “K1\SK2”
 * “K2”
 * “K2\SK1”
 * “K2\SK1\SSK1”
 * “K2\SK1\SSK2”
 * <p>
 * Сортировка по убыванию:
 * <p>
 * “K2”
 * “K2\SK1”
 * “K2\SK1\SSK2”
 * “K2\SK1\SSK1”
 * “K1”
 * “K1\SK2”
 * “K1\SK1”
 * “K1\SK1\SSK2”
 * “K1\SK1\SSK1”
 * <p>
 * Created by roman.pogorelov on 04.09.2019
 */
public class Sort {
    public List<String> sortAsc(String[] data) {
        Set<String> unique = new HashSet<>(Arrays.asList(data));
        addParents(unique, data);
        List<String> result = new ArrayList<>(unique);
        Collections.sort(result);
        return result;
    }

    public List<String> sortDesc(String[] data) {
        Set<String> unique = new HashSet<>(Arrays.asList(data));
        addParents(unique, data);
        List<String> result = new ArrayList<>(unique);
        result.sort((o1, o2) -> {
            int res = 0;
            char[] first = o1.toCharArray();
            char[] second = o2.toCharArray();
            int i = 0, j = 0;
            while (i < first.length && j < second.length) {
                char ch1 = first[i++];
                char ch2 = second[j++];
                if (ch1 != ch2) {
                    res = ch1 > ch2 ? -1 : 1;
                    break;
                }
            }
            if (res == 0) {
                if (i < first.length) {
                    res = 1;
                } else if (j < second.length) {
                    res = -1;
                }
            }
            return res;
        });
        return result;
    }

    /**
     * Method add the missing parents.
     *
     * @param result container to add
     * @param data   source data
     */
    private void addParents(Set<String> result, String[] data) {
        Arrays.stream(data)
                .forEach(str -> this.addParent(str, result));
    }

    /**
     * Method adds a missing parent.
     *
     * @param str    is used to check
     * @param result container to add
     */
    private void addParent(String str, Set<String> result) {
        int index = str.lastIndexOf('\\');
        while (index != -1) {
            String parent = str.substring(0, index);
            result.add(parent);
            index = parent.lastIndexOf('\\');
        }
    }
}

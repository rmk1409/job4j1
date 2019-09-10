package ru.job4j.chapter006;

import java.util.*;

/**
 * Задача список скриптов с указанием их зависимосей.
 * <p>
 * 1 - [2, 3], 2 - [4], 3 - [4, 5], 4 - [], 5 - []
 * <p>
 * Необходим написать метод, который возвращает список всех скриптов, которые нужно для загрузки входящего скрипта.
 * <p>
 * Например, чтобы выполнить скрипт 1. нужно выполнить скрипт (2, 3), которые в свою очередь зависят от 4 и 5 скрипта.
 * <p>
 * List load(Map<Integer, List ds, Integer scriptId)
 * <p>
 * Created by roman.pogorelov on 10.09.2019
 */
public class ScriptBuilder {
    public List<Integer> loadRecursion(Map<Integer, List<Integer>> map, Integer scriptId) {
        Set<Integer> unique = new LinkedHashSet<>();
        this.postOrderRecursion(map, scriptId, unique);
        unique.remove(scriptId);
        return new ArrayList<>(unique);
    }

    private void postOrderRecursion(Map<Integer, List<Integer>> map, Integer node, Set<Integer> result) {
        map.get(node).forEach(i -> this.postOrderRecursion(map, i, result));
        result.add(node);
    }

    public List<Integer> loadNoRecursion(Map<Integer, List<Integer>> map, Integer scriptId) {
        Set<Integer> unique = new LinkedHashSet<>();
        this.stack(map, scriptId, unique);
        unique.remove(scriptId);
        List<Integer> order = new ArrayList<>(unique);
        Collections.reverse(order);
        return order;
    }

    public void stack(Map<Integer, List<Integer>> map, Integer node, Set<Integer> set) {
        Stack<Integer> stack = new Stack<>();
        stack.push(node);
        Integer el;
        while (!stack.isEmpty()) {
            el = stack.pop();
            List<Integer> leaves = map.get(el);
            if (leaves.size() > 0) {
                leaves.stream()
                        .filter(i -> !stack.contains(i))
                        .forEach(i -> {
                            stack.push(i);
                            set.add(i);
                        });
            }
            set.add(el);
        }
    }
}

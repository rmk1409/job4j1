package ru.job4j.list;

import java.util.HashMap;
import java.util.List;

/**
 * Created by roman.pogorelov on 01.09.2019
 */
public class UserConvert {
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        list.forEach(item -> result.put(item.getId(), item));
        return result;
    }
}

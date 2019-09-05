package ru.job4j.generic;

/**
 * TODO Description
 * Created by roman.pogorelov on 05.09.2019
 */
public interface Store<T extends Base> {

    void add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    T findById(String id);
}
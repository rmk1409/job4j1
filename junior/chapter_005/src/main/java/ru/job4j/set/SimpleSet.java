package ru.job4j.set;

import ru.job4j.list.DynamicSizeArray;

import java.util.Iterator;

/**
 * Реализовать коллекцию SimpleSet. Коллекция должна обеспечивать void add(E e) и реализовывать Iterable<E>.
 * Коллекция не должна хранить дубликаты.
 * Set - внутри для хранения данных использует обычные массивы.
 * <p>
 * <p>
 * Код будет идентичным, как и в SimpleList(Это код из задания реализации списка на массиве).
 * как можно за счет композиции сократить количества кода?
 * Здесь нужно использовать SimpleList в реализации SimpleSet.
 * Created by roman.pogorelov on 05.09.2019
 */
public class SimpleSet<E> implements Iterable<E> {
    private DynamicSizeArray<E> container = new DynamicSizeArray(16);

    public void add(E e) {
        if (checkUniqueness(e)) {
            container.add(e);
        }
    }

    private boolean checkUniqueness(E e) {
        Iterator<E> iterator = container.iterator();
        boolean uniqueness = true;
        while (iterator.hasNext()) {
            if (e.equals(iterator.next())) {
                uniqueness = false;
                break;
            }
        }
        return uniqueness;
    }

    @Override
    public Iterator<E> iterator() {
        return container.iterator();
    }
}

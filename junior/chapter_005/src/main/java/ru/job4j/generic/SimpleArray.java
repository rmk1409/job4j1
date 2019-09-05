package ru.job4j.generic;

import java.util.Iterator;

/**
 * В этом задании необходимо сделать универсальную обертку над массивом.
 * <p>
 * Создать класс:
 * public class SimpleArray<T>
 * <p>
 * Также, реализуйте интерфейс Iterable<T> - метод iterator() возвращает итератор, предназначенный для обхода данной структуры.
 * <p>
 * Объект должен принимать количество ячеек. Структура не должна быть динамической. Если идет переполнение надо выкинуть ошибку.
 * <p>
 * Created by roman.pogorelov on 05.09.2019
 */
public class SimpleArray<T> implements Iterable<T> {
    private Object[] array;
    private int index = 0;

    public SimpleArray(int size) {
        this.array = new Object[size];
    }

    /**
     * добавляет указанный элемент (model) в первую свободную ячейку.
     *
     * @param model эл-т для добавления
     */
    public void add(T model) {
        if (this.index < this.array.length) {
            this.array[this.index++] = model;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * заменяет указанным элементом (model) элемент, находящийся по индексу index.
     *
     * @param index индекс замены
     * @param model новый эл-т
     * @return is setting successful or not
     */
    public boolean set(int index, T model) {
        if (index < this.index) {
            this.array[index] = model;
        } else {
            throw new IllegalArgumentException();
        }
        return true;
    }

    /**
     * удаляет элемент по указанному индексу, все находящиеся справа элементы при этом необходимо сдвинуть на единицу влево (в середине массива не должно быть пустых ячеек).
     *
     * @param index индекс удаления
     * @return is deleting successful or not
     */
    public boolean remove(int index) {
        if (index < this.index) {
            System.arraycopy(this.array, index + 1, this.array, index, this.index-- - index);
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
        return true;
    }

    /**
     * возвращает элемент, расположенный по указанному индексу;
     *
     * @param index индекс для возврата эл-та
     * @return эл-т
     */
    public T get(int index) {
        if (index < this.index) {
            return (T) this.array[index];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * проверяет выход за границы
     *
     * @param index индекс для проверки
     * @return false, если вышли за пределы
     */
    private boolean checkIndex(int index) {
        return index < this.array.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < index;
            }

            @Override
            public T next() {
                return (T) array[i++];
            }
        };
    }
}

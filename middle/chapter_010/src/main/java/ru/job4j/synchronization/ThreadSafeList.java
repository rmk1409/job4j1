package ru.job4j.synchronization;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.DynamicSizeArray;

import java.util.Iterator;

/**
 * Необходимо создать многопоточную коллекцию данных.
 * <p>
 * Возьмите код задания из модуля коллекции, где была реализована коллекция динамический список и за счет композиции сделайте многопоточную коллекцию.
 * Копировать коллекции не надо. Вам нужно подключить модуль. Почитай про шаблон Wrapper.
 * <dependency>
 * <groupId>ru.job4j</groupId>
 * <artifactId>chapter_005</artifactId>
 * <version>2.0</version>
 * </dependency>
 * <p>
 * В обязательном порядке используйте jcip аннотации.
 * <p>
 * Итератор для многопоточной коллекции.
 * Следуя шаблону Wrapper мы получить следующий метод для итератора.
 * Override
 * public synchronized Iterator<E> iterator() {
 * return array.iterator();
 * }
 * Этот код работает не верно. Он отдает ссылку на оригинальный итератор. Если мы будем работать с ним, то мы получаем ошибку в share visibility.
 * Самый простой и надежный способов сделать копию данных - snapshots.
 * И уже с него сделать итератор.
 * Override
 * public synchronized Iterator<E> iterator() {
 * return copy(this.array).iterator();
 * }
 * Метод copy - вы должны написать самостоятельно.
 * <p>
 * Этот итератор будет работает в режиме fail-safe - все изменения после получения коллекции не будут отображаться в итераторе.
 * fail-fast - другой режим. при изменении данных во время итерации, коллекция выкинет исключение  ConcurrentModificationException.
 * <p>
 * Created by roman.pogorelov on 26.09.2019
 */
@ThreadSafe
public class ThreadSafeList<T> extends DynamicSizeArray<T> {
    @GuardedBy("this")
    private DynamicSizeArray<T> inner;

    public ThreadSafeList(int size, DynamicSizeArray<T> inner) {
        super(size);
        this.inner = inner;
    }

    @Override
    public synchronized Object[] getContainer() {
        return this.inner.getContainer();
    }

    @Override
    public synchronized void add(T element) {
        this.inner.add(element);
    }

    @Override
    public synchronized T get(int index) {
        return this.inner.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return this.copy(this.inner).iterator();
    }

    private DynamicSizeArray<T> copy(DynamicSizeArray<T> inner) {
        DynamicSizeArray<T> result = new DynamicSizeArray<>(inner.getContainer().length);
        inner.forEach(result::add);
        return result;
    }
}

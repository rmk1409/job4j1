package ru.job4j.nonblocking;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 1. Необходимо сделать кеш для хранение моделей. в кеше должны быть методы
 * add(Base model), update(Base model) delete(Base model),
 * <p>
 * 2. Для хранения данных в кеше нужно использовать ConcurrentHashMap<Integer, Base>.
 * В качестве ключа используйте int id. в качестве значения Base модель
 * <p>
 * 3. В кеше должна быть возможность проверять актуальность данных. Для этого в модели данных должно быть после int version.
 * Это посе должно увеличиваться на единицу каждый раз, когда произвели изменения данных в модели.
 * Например. Два пользователя прочитали данные task_1
 * первый пользователь изменил поле имя и второй сделал тоже самое. нужно перед обновлением данных проверить. что текущий пользователь не затер данные другого пользователя.
 * Если данные затерты то выбросить OptimisticException - такая реализация достигается за счет введение в модель поля version.
 * Перед обновлением данных необходимо проверять текущую версию и ту что обновляем и увеличивать на единицу каждый раз, когда произошло обновление. Если версии не равны -  кидать исключение.
 * <p>
 * Исключение - OptimisticException нужно сделать самостоятельно.
 * public class OptimisticException extends RuntimeException {
 * }
 * <p>
 * Исключение должно быть RuntimeException, так как обработчик для BiFunction не может кидать исключение.
 * Использовать метод https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentHashMap.html#computeIfPresent-K-java.util.function.BiFunction-
 * <p>
 * Пример.
 * <p>
 * Нить 1 изменила объект 1, тогда version должно стать 1.
 * Нить 2 в это же время изменила объект 1, тут тоже самое version станет 1.
 * <p>
 * Объекты 1 - создаются в разной области памяти. По сути эти два разных объекта с одинаковыми полями.
 * Когда нить 1 будет обновлять данные, обновление пройдет успешно. потому что данные в кеше будут на единицу отличаться.
 * С другой стороны нить 2 выкинет исключение. потому, что версия в кеше не будет соответствовать текущей версии.
 * <p>
 * Тесты.
 * Если мы запускаем нить в тесте, то ошибки в этой нити не будут влиять на результат теста.
 * Тест выполняется успешно. Это связано с тем, что главная нить не видит, что происходит во второстепенной нити.
 * Чтобы это поправить нам нужно передать исключение к главную нить.
 * Теперь мы можем проверить, что такой код падает.
 * <p>
 * Created by roman.pogorelov on 27.09.2019
 */
public class NonBlockingCache {
    private ConcurrentHashMap<Integer, Base> container = new ConcurrentHashMap<>();

    public void add(Base model) {
        this.container.put(model.id, model);
    }

    /**
     * Check versions, if the same throw OptimisticException else update data.
     *
     * @param model is used like new data
     */
    public void update(Base model) {
        this.container.computeIfPresent(model.id, (k, v) -> {
            if (v.version == (++model.version)) {
                throw new OptimisticException("Optimistic lock is working");
            } else {
                Thread.yield();
                return model;
            }
        });
    }

    public void delete(Base model) {
        this.container.remove(model.id);
    }
}
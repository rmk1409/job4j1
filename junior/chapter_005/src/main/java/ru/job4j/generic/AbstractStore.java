package ru.job4j.generic;

/**
 * TODO Description
 * Created by roman.pogorelov on 05.09.2019
 */
public class AbstractStore<T extends Base> implements Store<T> {
    private SimpleArray<T> data;

    public AbstractStore(int size) {
        this.data = new SimpleArray<>(size);
    }

    /**
     * Method adds new model.
     *
     * @param model new data
     */
    public void add(T model) {
        this.data.add(model);
    }

    /**
     * Method check equality of id and el's id.
     *
     * @param id control data
     * @param el is used to check
     * @return equally or not
     */
    private boolean checkId(String id, T el) {
        return id.equals(el.getId());
    }

    /**
     * Method replaces data by new model.
     *
     * @param id    is used to search
     * @param model new data
     * @return whether it is successful or not
     */
    public boolean replace(String id, T model) {
        boolean result = false;
        int i = 0;
        for (T el : data) {
            if (checkId(id, el)) {
                result = data.set(i, model);
                break;
            }
            i++;
        }
        return result;
    }

    /**
     * Method delete element by id.
     *
     * @param id is used to search
     * @return whether it is successful or not
     */
    public boolean delete(String id) {
        boolean result = false;
        int i = 0;
        for (T el : data) {
            if (checkId(id, el)) {
                result = data.remove(i);
                break;
            }
            i++;
        }
        return result;
    }

    /**
     * Method find element by id.
     *
     * @param id is used to search
     * @return element or null
     */
    public T findById(String id) {
        T result = null;
        for (T el : data) {
            if (checkId(id, el)) {
                result = el;
                break;
            }
        }
        return result;
    }
}

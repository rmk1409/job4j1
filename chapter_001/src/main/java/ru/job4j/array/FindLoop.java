package ru.job4j.array;

public class FindLoop {
    public int indexOf(int[] data, int el) {
        return indexOf(data, el, 0, data.length);
    }

    public int indexOf(int[] data, int el, int start, int finish) {
        int rst = -1; // если элемента нет в массиве, то возвращаем -1.

        for (int i = start; i < finish; i++) {
            if (data[i] == el) {
                rst = i;
                break;
            }
        }

        return rst;
    }
}

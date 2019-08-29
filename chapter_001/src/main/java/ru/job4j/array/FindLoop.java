package ru.job4j.array;

public class FindLoop {
    public int indexOf(int[] data, int el) {
        return indexOf(data, el, 0, data.length);
    }

    public int indexOf(int[] data, int el, int start, int finish) {
        int rst = -1;
        for (int i = start; i < finish; i++) {
            if (data[i] == el) {
                rst = i;
                break;
            }
        }
        return rst;
    }

    /**
     * Sort array by Selection Sort algorithm
     * <p>
     * Explanation
     * Selection Sort divides the array into a sorted and unsorted subarray.
     * The sorted subarray is formed by inserting the minimum element of the unsorted subarray at the end of the sorted array, by swapping.
     *
     * @param data - array to sort
     * @return sorted array
     */
    public int[] sort(int[] data) {
        for (int i = 0; i < data.length; i++) {
            int index = i;
            boolean flag = false;
            for (int j = i + 1; j < data.length; j++) {
                if (data[index] > data[j]) {
                    index = j;
                    flag = true;
                }
            }
            if (flag) {
                int tmp = data[i];
                data[i] = data[index];
                data[index] = tmp;
            } else {
                break;
            }
        }
        return data;
    }
}

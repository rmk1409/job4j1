package ru.job4j.condition;

public class Max {
    public int max(int left, int right) {
        return left > right ? left : right;
    }

    /**
     * Return one max number from 3 numbers.
     *
     * @param first
     * @param second
     * @param third
     * @return max
     */
    public int max(int first, int second, int third) {
        return this.max(this.max(first, second), this.max(second, third));
    }

    /**
     * Return one max number from 4 numbers.
     *
     * @param first
     * @param second
     * @param third
     * @param fourth
     * @return max
     */
    public int max(int first, int second, int third, int fourth) {
        return this.max(this.max(first, second, third), fourth);
    }
}
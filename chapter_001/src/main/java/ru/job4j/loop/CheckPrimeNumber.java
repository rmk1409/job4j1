package ru.job4j.loop;

public class CheckPrimeNumber {
    public boolean check(int num) {
        boolean result = true;

        for (int i = 2; i < num; i++) {
            if ((num % i) == 0) {
                result = false;
                break;
            }
        }

        return result;
    }
}

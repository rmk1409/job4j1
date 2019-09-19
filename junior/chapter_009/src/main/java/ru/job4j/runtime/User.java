package ru.job4j.runtime;

import java.util.stream.IntStream;

/**
 * TODO Description
 * Created by roman.pogorelov on 18.09.2019
 */
public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + '}';
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Finalize is invoking....");
    }

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        double kb = 1024;
        System.out.println("Total: " + runtime.totalMemory() / kb);
        System.out.println("Free: " + runtime.freeMemory() / kb);
        double before = (runtime.totalMemory() - runtime.freeMemory()) / kb;
        System.out.println("Used: " + before);
        var n = 1_000;
//        List<User> list = new ArrayList<>(n);
        IntStream.range(0, n).forEach(i -> new User("" + i));
        double after = (runtime.totalMemory() - runtime.freeMemory()) / kb;
        System.out.println("Used: " + after);
        double oneUserKb = (after - before) / n;
        System.out.println("Total: " + runtime.totalMemory() / kb);
        System.out.println("One user uses: " + oneUserKb + " kb, or " + oneUserKb * 8 + " bytes.");
    }
}

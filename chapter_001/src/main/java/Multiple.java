public class Multiple {
    public static void main(String[] args) {
        int maxMultiply = 10;
        for (int i = 1; i <= maxMultiply; i++) {
            System.out.println(String.format("1 * %1$d = %1$d", i));
        }
    }
}

public class Multiple {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(String.format("1 * %1$d = %1$d", i));
        }
    }
}

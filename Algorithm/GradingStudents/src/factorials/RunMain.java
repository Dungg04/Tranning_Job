package factorials;

import java.util.Scanner;

public class RunMain {
    public static long F(int n) {
        long f = 1;
        if (n == 0 || n == 1) {
            return f;
        } else {
            for (int i = 2; i <= n; i++) {
                f *= i;
            }
            return f;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        do {
            n = scanner.nextInt();
        } while (n<1 || n>100);

        System.out.println(F(n));
    }
}

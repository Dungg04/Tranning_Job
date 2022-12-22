package BetweenTwoSets;

import java.util.Scanner;

public class RunMain {
    public static int gcd(int a, int b) {
        while (a > 0 && b > 0) {

            if (a >= b) {
                a = a % b;
            }
            else {
                b = b % a;
            }
        }

        return a + b;
    }

    public static int lcm(int a, int b) {
        return (a / gcd(a, b)) * b;
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();
        int x = 1, y = 0;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scan.nextInt();
            x = lcm(x, a[i]);
            if(x > 100) {
                System.out.println(0);
                return;
            }
        }
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = scan.nextInt();
            y = gcd(y, b[i]);
        }
        if(y%x!=0) {
            System.out.println(0);
            return;
        }

        int output = 0;
        for (int i = 1; i * i <= y; ++i) {
            if (y % i == 0) {
                ++output;
                if (i * i != y)
                    ++output;
            }
        }

        System.out.println(output);
    }
}

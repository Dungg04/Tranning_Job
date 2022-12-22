package AppleAndOrange;

import java.util.Scanner;

public class RunMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int s, t, a, b, n, m, d, ans1 = 0, ans2 = 0;

        s = scanner.nextInt();
        t = scanner.nextInt();
        a = scanner.nextInt();
        b = scanner.nextInt();
        n = scanner.nextInt();
        m = scanner.nextInt();

        for(int i=0; i<m; i++) {
            d = scanner.nextInt();
            d += a;
            if(d>=s && d<=t) {
                ans1++;
            }
        }

        for(int i=0; i<n; i++) {
            d = scanner.nextInt();
            d += b;
            if(d>=s && d<=t) {
                ans2++;
            }
        }

        System.out.println(ans1);
        System.out.println(ans2);

    }
}

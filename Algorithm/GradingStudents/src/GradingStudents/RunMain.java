package GradingStudents;

import java.util.Scanner;

public class RunMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        do{
            n = scanner.nextInt();
        }while (n<1 || n>60);
         int[] gds = new int[n];
        for (int i = 0; i<gds.length; i++) {
            do {
                gds[i] = scanner.nextInt();
            } while (gds[i]<0 || gds[i]>100);
        }

        for (int i=0; i<gds.length; i++){
            if((gds[i] + 1)%5==0 && (gds[i] + 1) >= 40) {
                gds[i]++;
            }
            if((gds[i] + 2)%5==0 && (gds[i] + 2) >= 40) {
                gds[i] += 2;
            }
            System.out.println(gds[i]);
        }
    }
}

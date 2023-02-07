package td1.exo2;

import java.util.Scanner;

public class Fibbo {
    private static long compteur = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("entrer le nombre: ");
        int num = sc.nextInt();
        Fibbo fib = new Fibbo();
        System.out.printf("Fib(%d)=%d en %d appels%n", num, fib.fib(num), compteur);
    }

    public long fib(int n) {
        compteur++;
        if (n < 2) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);
    }
}

package td1.exo2;

import java.util.Scanner;

public class Fibbo2 {

    private long element = 0;
    private long suivant = 1;

    private final int nombre;

    public Fibbo2(int nombre) {
        this.nombre = nombre;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("entrer le nombre: ");
        int num = sc.nextInt();
        Fibbo2 fib = new Fibbo2(num);
        System.out.printf("Fib(%d)=%d %n", num, fib.fib());
    }

    public long fib() {
        return nombre < 2 ? nombre : innerFib();
    }

    private long innerFib() {
        for (int i = 2; i <= nombre; i++) {
            var tmp = element + suivant;
            element = suivant;
            suivant = tmp;
        }
        return suivant;
    }
}

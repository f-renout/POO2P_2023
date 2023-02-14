package td2.morpion;

import org.apache.commons.io.input.CloseShieldInputStream;

import java.util.Scanner;

public class Humain extends Joueur {
    public Humain(Type x, Grille grille) {
        super(x, grille);
    }

    @Override
    protected Coup prochainCoup() {
        int x;
        int y;
        do {
            x = readCoord("entrer la ligne");
            y = readCoord("entrer la colonne");
            var result = new Coup(x, y, getJeton());
            if (grille.isValide(result)) {
                return result;
            }
            System.out.println("Coup invalide, merci de jouer dans un emplacement disponible");
        } while (true);
    }

    private int readCoord(String text) {
        try (Scanner sc = new Scanner(CloseShieldInputStream.wrap(System.in))) {
            while (true) {
                try {
                    System.out.print(text + " (0, 1 ou 2): ");
                    int num = sc.nextInt();
                    if (num >= 0 && num <= 2) {
                        return num;
                    }
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println("Merci de saisir un nombre entre 0 et 2");
                    sc.nextLine();
                }
            }
        }
    }
}

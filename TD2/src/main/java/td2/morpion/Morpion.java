package td2.morpion;

public class Morpion {
    private final Grille grille = new Grille();
    private final Joueur joueur1;
    private final Joueur joueur2;

    public Morpion(JOUEUR type1, JOUEUR type2, int difficulte) {
        joueur1 = createJoueur(type1, Type.X, difficulte);
        joueur2 = createJoueur(type2, Type.O, difficulte);
    }

    public static void main(String[] args) {
        Morpion m = new Morpion(JOUEUR.HUMAN, JOUEUR.IA, 4);
        m.deroule();
    }

    private void deroule() {
        Joueur prochainJoueur = joueur2;
        while (!grille.finie()) {
            if (prochainJoueur == joueur1) {
                prochainJoueur = joueur2;
            } else {
                prochainJoueur = joueur1;
            }
            Coup c = prochainJoueur.jouer();
            System.out.println(c);
            System.out.println(grille);
        }
        final var gagne = grille.gagnant();
        if (gagne != null) {
            System.out.printf("le joueur avec les %s gagne%n", gagne);
        } else {
            System.out.println("match nul");
        }
    }

    private Joueur createJoueur(JOUEUR typeJoueur, Type x, int difficulte) {
        return switch (typeJoueur) {
            case HUMAN -> new Humain(x, grille);
            case IA -> new IA(x, grille, difficulte);
            case IA_EVOLUEE -> new IAevoluee(x, grille, difficulte);
        };
    }

    private enum JOUEUR {HUMAN, IA, IA_EVOLUEE}
}

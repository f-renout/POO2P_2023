package td2.morpion;

public abstract class Joueur {
    protected final Type type;
    protected final Grille grille;

    protected Joueur(Type type, Grille grille) {
        this.type = type;
        this.grille = grille;
    }

    public Jeton getJeton() {
        return new Jeton(type);
    }

    public Coup jouer() {
        Coup c = prochainCoup();
        grille.remplir(c);
        return c;
    }

    protected abstract Coup prochainCoup();
}

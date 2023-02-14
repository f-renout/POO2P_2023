package td2.morpion;

public record Coup(int ligne, int colonne, Jeton jeton) {
    @Override
    public String toString() {
        return "Coup{" + "ligne=" + ligne + ", colonne=" + colonne + ", jeton=" + jeton + '}';
    }
}

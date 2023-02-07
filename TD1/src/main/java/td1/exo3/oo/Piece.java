package td1.exo3.oo;

public record Piece(int taille) {
    public String print() {
        String motif = "*";
        return motif.repeat(2 * taille - 1);
    }

    @Override
    public String toString() {
        return "Piece [taille=" + taille + "]";
    }
}

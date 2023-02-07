package td1.exo3.oo;

import java.util.Arrays;

public enum Position {
    GAUCHE, CENTRE, DROITE;

    public static Position intermediaire(Position depart, Position arrivee) {
        if (depart == null || arrivee == null) {
            throw new IllegalArgumentException("les parametres ne doivent pas être nulls");
        }
        return Arrays.stream(Position.values())
                .filter(p -> p != depart)
                .filter(p -> p != arrivee)
                .findFirst()
                .orElseThrow(()->new IllegalArgumentException("impossible de trouver le restant p/ à "+depart+" et "+arrivee));
    }
}

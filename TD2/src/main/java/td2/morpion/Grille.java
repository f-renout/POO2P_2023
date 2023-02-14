package td2.morpion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Grille {
    private final Jeton[][] matrice;

    public Grille() {
        matrice = new Jeton[3][3];
    }

    public void remplir(Coup coup) {
        if (matrice[coup.ligne()][coup.colonne()] != null) {
            throw new RuntimeException("coup invalide");
        }
        matrice[coup.ligne()][coup.colonne()] = coup.jeton();
    }

    public boolean isValide(Coup coup) {
        return matrice[coup.ligne()][coup.colonne()] == null;
    }

    public void annuler(Coup coup) {
        matrice[coup.ligne()][coup.colonne()] = null;
    }

    @Override
    public String toString() {
        String toFormat = """
                -------
                |%s|%s|%s|
                |%s|%s|%s|
                |%s|%s|%s|
                -------
                """;
        List<String> params = Arrays.stream(matrice).flatMap(Arrays::stream).map(j -> j == null ? " " : j.type().toString()).toList();
        return toFormat.formatted(params.toArray());
    }

    private Type verticalWinner() {
        for (int colonne = 0; colonne < 3; colonne++) {
            if (match(colonne(colonne))) {
                return matrice[0][colonne].type();
            }
        }
        return null;
    }

    private Type horizontalWinner() {
        for (int ligne = 0; ligne < 3; ligne++) {
            if (match(ligne(ligne))) {
                return matrice[ligne][0].type();
            }
        }
        return null;
    }

    private Type diagonalWinner() {
        if (match(premiereDiagonale()) || match(deuxiemeDiagonale())) {
            return matrice[1][1].type(); //on retourne le type de matrice[1][1] car c'est une case commune aux 2 diagonales
        }
        return null;
    }

    private boolean match(List<Jeton> jetons) {

        //si au moins un des elements est non defini alors on est sur que la triplette n'est pas gagnante
        if (jetons.stream().anyMatch(Objects::isNull)) {
            return false;
        }
        //si tous les elements sont identiques alors on matche
        //identique = distinct ne retourne qu'un seul résultat
        return jetons.stream()
                .map(Jeton::type)
                .distinct()
                .count() == 1;
    }

    public Type gagnant() {
        Type res = verticalWinner();
        if (res == null) {
            res = horizontalWinner();
        }
        if (res == null) {
            res = diagonalWinner();
        }
        return res;
    }

    public boolean plein() {
        return Arrays.stream(matrice).flatMap(Arrays::stream).noneMatch(Objects::isNull);
    }

    public List<Coup> coupsPossibles(Type type) {
        List<Coup> result = new ArrayList<>();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                Jeton current = matrice[x][y];
                if (current == null) {
                    result.add(new Coup(x, y, new Jeton(type)));
                }
            }
        }
        return result;
    }

    public boolean finie() {
        return gagnant() != null || plein();
    }

    public List<Jeton> ligne(int index) {
        return Arrays.asList(matrice[index][0], matrice[index][1], matrice[index][2]);
    }

    public List<Jeton> colonne(int index) {
        return Arrays.asList(matrice[0][index], matrice[1][index], matrice[2][index]);
    }

    public List<Jeton> premiereDiagonale() {
        return Arrays.asList(matrice[0][0], matrice[1][1], matrice[2][2]);
    }

    public List<Jeton> deuxiemeDiagonale() {
        return Arrays.asList(matrice[0][2], matrice[1][1], matrice[2][0]);
    }
}

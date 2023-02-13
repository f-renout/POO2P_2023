package td2.morpion;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class IAevoluee extends IA {
    public IAevoluee(Type x, Grille grille, int niveau) {
        super(x, grille, niveau);
    }

    @Override
    protected int evaluer() {
        int evaluer = super.evaluer();
        if (evaluer != 0) {
            return evaluer;
        }
        int somme = 0;
        for (int i = 0; i < 3; i++) {
            //les lignes et les colonnes
            somme += ajusteSomme(grille.ligne(i));
            somme += ajusteSomme(grille.colonne(i));
        }
        //les diagonales
        somme += ajusteSomme(grille.premiereDiagonale());
        somme += ajusteSomme(grille.deuxiemeDiagonale());
        return somme;
    }

    int ajusteSomme(List<Jeton> list) {
        final var flux = list.stream();
        final var collect = flux.filter(Objects::nonNull).collect(groupingBy(Jeton::type, counting()));
        final var nbJetonsCurrentPlayer = collect.getOrDefault(type, 0L);
        final var nbJetonsAdversaire = collect.getOrDefault(type.adversaire(), 0L);
        if (nbJetonsAdversaire == 2) {
            return -30;
        } else if (nbJetonsCurrentPlayer == 2) {
            return 30;
        } else if (nbJetonsAdversaire == 1) {
            return -10;
        } else if (nbJetonsCurrentPlayer == 1) {
            return 10;
        }
        return 0;
    }

}

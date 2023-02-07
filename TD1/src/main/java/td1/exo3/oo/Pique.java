package td1.exo3.oo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Pique {
    private final Position position;
    private final Stack<Piece> pieces;

    public Pique(Position position) {
        this.position = position;
        pieces = new Stack<>();
    }

    public void ajoutePiece(Piece piece) {
        if (pieces.isEmpty()) {
            pieces.push(piece);
        } else {
            Piece peek = pieces.peek();
            if (peek.taille() > piece.taille()) {
                pieces.push(piece);
            } else {
                throw new InvalidMoveException(piece, peek, position);
            }
        }
    }

    public Piece prendPiece() {
        return pieces.pop();
    }

    public List<String> print(int nbPiece) {
        String empty = "|";
        String base = "=".repeat(2 * nbPiece + 1); //on crée une base qui va etre plus grande que la plus grande des pieces
        List<String> display = new ArrayList<>();

        final var demiLargeurDessin = nbPiece + 2;

        display.add(createLine(base, nbPiece+1, demiLargeurDessin));


        //on commence par dessiner les pieces qui sont sur la pique
        for (int i = 0; i < pieces.size(); i++) {
            Piece piece = pieces.elementAt(i);
            display.add(createLine(piece.print(), piece.taille(), demiLargeurDessin));
        }

        //puis on complete en rajoutant le bout de pique qui depasse
        for (int i = pieces.size(); i < nbPiece; i++) {
            display.add(createLine(empty, 1, demiLargeurDessin));
        }

        //comme on a tout dessiné à l'envers, on renverse le resultat
        Collections.reverse(display);
        return display;
    }

    private String createLine(String dessinPiece, int demiLargeur, int demiLargeurMax) {
        //ici on dessine les bords blancs autour d'une piece de largeur donnée
        int additionnal = demiLargeurMax - demiLargeur;
        String external = " ".repeat(additionnal);
        return external.concat(dessinPiece).concat(external);
    }

}

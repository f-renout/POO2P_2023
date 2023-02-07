package td1.exo3.oo;

import java.util.List;
import java.util.Map;

import static td1.exo3.oo.Position.*;

public class Hanoi {

    private final int nbPiece;

    private final Map<Position, Pique> map; //sert à recuperer facilement une pique à partir de sa position

    public Hanoi(int nbPiece) {
        this.nbPiece = nbPiece;

        var gauche = new Pique(GAUCHE);
        for (int i = nbPiece; i > 0; i--) {
            gauche.ajoutePiece(new Piece(i));
        }

        map = Map.of(GAUCHE, gauche, Position.CENTRE, new Pique(Position.CENTRE), Position.DROITE, new Pique(Position.DROITE));

    }

    public static void main(String[] args) {
        Hanoi h = new Hanoi(3);
        h.print();
        h.deplace(h.nbPiece, GAUCHE, Position.DROITE);
    }

    public void deplace(int nombre, Position plotDepart, Position plotArrive) {
        if (nombre > nbPiece) {
            throw new IllegalArgumentException("le nombre maximum de piece deplacable est " + nbPiece);
        }
        if (nombre == 1) {
            System.out.printf("on déplace 1 rondelle de %s à %s%n", plotDepart, plotArrive);
            Piece piece = map.get(plotDepart).prendPiece();
            map.get(plotArrive).ajoutePiece(piece);
            print();
        }
        if (nombre > 1) {
            Position intermetiaire = Position.intermediaire(plotDepart, plotArrive);
            //on bouge les n-1 sur le plot intermediaire
            deplace(nombre - 1, plotDepart, intermetiaire);
            //on deplace la base vers la destination
            deplace(1, plotDepart, plotArrive);
            //on deplace les n-1 de l'intermediaire à la destination
            deplace(nombre - 1, intermetiaire, plotArrive);
        }
    }

    private void print() {
        List<String> printGauche = map.get(GAUCHE).print(nbPiece);
        List<String> printCentre = map.get(CENTRE).print(nbPiece);
        List<String> printDroite = map.get(DROITE).print(nbPiece);
        for (int i = 0; i < printGauche.size(); i++) {
            System.out.println(printGauche.get(i) + printCentre.get(i) + printDroite.get(i));
        }
    }
}

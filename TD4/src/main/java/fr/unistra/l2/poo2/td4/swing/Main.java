package fr.unistra.l2.poo2.td4.swing;

public class Main {
    public static void main(String[] args)
    {
        PointModele modele = new PointModele();
        PointControleur controleur = new PointControleur(modele);
        controleur.afficherVues();
    }
}

package fr.unistra.l2.poo2.td4.javafx;

import javafx.scene.Group;

public abstract class PointVue extends Group {

    protected PointModele modele;

    public PointVue(PointModele modele) {
        super();
        this.modele = modele;
    }
}

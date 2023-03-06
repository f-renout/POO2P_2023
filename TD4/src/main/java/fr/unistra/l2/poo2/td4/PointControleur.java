package fr.unistra.l2.poo2.td4;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PointControleur extends WindowAdapter {
    private final VueGraphe vueGraphe;
    private final VueSlider vueSlider;

    public PointControleur(PointModele pointMod) {
        vueGraphe = new VueGraphe(pointMod.getPoint());
        vueSlider = new VueSlider(pointMod.getPoint());

        pointMod.addChangementPositionListener(vueGraphe);
        pointMod.addChangementPositionListener(vueSlider);

        vueSlider.addWindowListener(this);
        vueSlider.addChangementPositionListener(pointMod);

        vueGraphe.addWindowListener(this);
        vueGraphe.addChangementPositionListener(pointMod);
    }

    public void afficherVues() {
        vueGraphe.setVisible(true);
        vueSlider.setVisible(true);
    }
    @Override
    public void windowClosing(WindowEvent e) {
        vueSlider.setVisible(false);
        vueGraphe.setVisible(false);
        vueSlider.dispose();
        vueGraphe.dispose();
        System.exit(0);
    }
}

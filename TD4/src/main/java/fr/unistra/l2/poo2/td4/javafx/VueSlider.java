package fr.unistra.l2.poo2.td4.javafx;

import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;

public class VueSlider extends PointVue {
    public Slider scrollX;
    public Slider scrollY;

    public VueSlider(PointModele modele) {
        super(modele);

        this.getChildren().removeAll();
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(10, 10, 10, 10));
        gp.setVgap(10);
        this.getChildren().add(gp);

        scrollX = new Slider(0, 10, modele.getX());
        scrollX.setBlockIncrement(1);
        scrollY = new Slider(0, 10, modele.getY());
        scrollY.setBlockIncrement(1);

        gp.add(new Label("Point X : "), 0, 0);
        gp.add(scrollX, 1, 0);
        gp.add(new Label("Point Y : "), 0, 1);
        gp.add(scrollY, 1, 1);

        redraw();
    }

    public void redraw() {
        scrollX.setValue(modele.getX());
        scrollY.setValue(modele.getY());
    }

    public DoubleProperty getXProperty() {
        return scrollX.valueProperty();
    }

    public DoubleProperty getYProperty() {
        return scrollY.valueProperty();
    }
}

package fr.unistra.l2.poo2.td4.javafx;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class VueGraphe extends PointVue {
    private final int maxX;
    private final int maxY;

    public VueGraphe(PointModele modele, int maxX, int maxY) {
        super(modele);
        this.maxX = maxX;
        this.maxY = maxY;

        redraw();
    }

    public void redraw() {
        this.getChildren().removeAll();

        Rectangle background = new Rectangle(0, 0, maxX, maxY);
        background.setFill(Color.WHITE);
        this.getChildren().add(background);

        for (int x = -5; x <= 5; x++) {
                var lx = new Line(x * 100 + 500, 0, x * 100 + 500, 1000);
                lx.setStroke(getColor(x));
                this.getChildren().add(lx);
                var ly = new Line(0, x * 100 + 500, 1000, x * 100 + 500);
                ly.setStroke(getColor(x));
                this.getChildren().add(ly);
        }

        Circle circle = new Circle();
        circle.setCenterX(modele.getX() * 100);
        circle.setCenterY(modele.getY() * 100);
        circle.setRadius(10);
        circle.setFill(Color.RED);
        this.getChildren().add(circle);
    }

    private static Color getColor(int x) {
        return x==0?Color.BLUE:Color.BLACK;
    }
}

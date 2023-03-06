package fr.unistra.l2.poo2.td4.swing;

import java.util.EventObject;

public class ChangementPositionEvent extends EventObject {
    private final Point point;

    public ChangementPositionEvent(Object source, Point nouveauPoint) {
        super(source);
        point = nouveauPoint;
    }

    public Point getPoint() {
        return point;
    }
}
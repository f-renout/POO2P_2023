package fr.unistra.l2.poo2.td4.swing;

import javax.swing.event.EventListenerList;

public class PointModele implements ChangementPositionListener{
    private Point point;
    private final EventListenerList listeners;

    public PointModele() {
        this.point = new Point(0, 0);
        listeners = new EventListenerList();
    }

    public Point getPoint() {
        return point;
    }
    public void setPosition(Point nouveauPoint) {
        this.point=nouveauPoint;
        positionChangee();
    }

    public void addChangementPositionListener(ChangementPositionListener listener) {
        listeners.add(ChangementPositionListener.class, listener);
    }

    private void positionChangee() {
        System.out.println("modele notifie "+point);
        ChangementPositionListener[] listeners = this.listeners.getListeners(ChangementPositionListener.class);
        for (ChangementPositionListener listener : listeners) {
            listener.positionChanged(new ChangementPositionEvent(this, point));
        }
    }

    @Override
    public void positionChanged(ChangementPositionEvent event) {
        System.out.println("modele recoit "+event.getPoint());
        setPosition(event.getPoint());
    }
}

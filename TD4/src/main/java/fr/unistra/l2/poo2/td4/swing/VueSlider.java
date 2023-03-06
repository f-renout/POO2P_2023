package fr.unistra.l2.poo2.td4.swing;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;
import java.awt.*;

public class VueSlider extends JFrame implements ChangeListener, ChangementPositionListener {
    private final JScrollBar scrollX;
    private final JScrollBar scrollY;
    private Point point;

    private final EventListenerList listeners = new EventListenerList();

    public VueSlider(Point point) {
        this.point = point;
        scrollX = new JScrollBar(JScrollBar.HORIZONTAL, this.point.x(), 0, -5, 5);
        scrollY = new JScrollBar(JScrollBar.HORIZONTAL, this.point.y(), 0, -5, 5);
        scrollX.getModel().addChangeListener(this);
        scrollY.getModel().addChangeListener(this);
        JPanel content = new JPanel();
        GridLayout grid = new GridLayout(2, 1);
        content.setLayout(grid);
        content.add(scrollX);
        content.add(scrollY);
        setContentPane(content);
        pack();
        setSize(200, 200);
    }

    public void addChangementPositionListener(ChangementPositionListener listener) {
        listeners.add(ChangementPositionListener.class, listener);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        if(interalEvent) {
            System.out.printf("slider notifie "+point);
            this.point = new Point(scrollX.getValue(), scrollY.getValue());
            ChangementPositionListener[] listeners = this.listeners.getListeners(ChangementPositionListener.class);
            for (ChangementPositionListener listener : listeners) {
                listener.positionChanged(new ChangementPositionEvent(this, point));
            }
        }
    }

    private boolean interalEvent = true;

    @Override
    public void positionChanged(ChangementPositionEvent event) {
        System.out.println("slider recoit "+event.getPoint());
        interalEvent = false;
        this.point = event.getPoint();
        scrollX.setValue(point.x());
        scrollY.setValue(point.y());
        repaint();
        interalEvent = true;
    }
}

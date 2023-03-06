package fr.unistra.l2.poo2.td4.swing;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VueGraphe extends JFrame implements ChangementPositionListener {
    private final EventListenerList listeners = new EventListenerList();
    JPanel panneau;
    private Point point;

    public VueGraphe(Point pointASetter) {
        this.point = pointASetter;
        panneau = new JPanel() {
            @Override
            public void paint(Graphics g) {
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, 1000, 1000);
                g.setColor(Color.BLUE);
                g.drawLine(0, 500, 1000, 500);
                g.drawLine(500, 0, 500, 1000);
                g.setColor(Color.BLACK);
                for (int x = -5; x <= 5; x++) {
                    if (x != 0) {
                        g.drawLine(x * 100 + 500, 0, x * 100 + 500, 1000);
                        g.drawLine(0, x * 100 + 500, 1000, x * 100 + 500);
                    }
                }
                g.setColor(Color.RED);
                g.fillOval(point.x() * 100 + 475, point.y() * 100 + 475, 50, 50);
            }
        };
        MouseAdapter notifier = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = Math.round(((float) e.getX()) / 100) - 5;
                int y = Math.round(((float) e.getY()) / 100) - 5;
                point = new Point(x, y);
                System.out.println("on a clique sur "+e.getX()+","+e.getY());
                System.out.println("converti en "+point);
                positionChangee();
            }
        };
        panneau.addMouseListener(notifier);
        setContentPane(panneau);
        pack();
        setSize(1000, 1075);
    }

    public void addChangementPositionListener(ChangementPositionListener listener) {
        listeners.add(ChangementPositionListener.class, listener);
    }

    private void positionChangee() {
        System.out.println("graphe notifie avec "+point);
        ChangementPositionListener[] listeners = this.listeners.getListeners(ChangementPositionListener.class);
        for (ChangementPositionListener listener : listeners) {
            listener.positionChanged(new ChangementPositionEvent(this, point));
        }
    }

    @Override
    public void positionChanged(ChangementPositionEvent event) {
        this.point = event.getPoint();
        System.out.println("graphe recoit "+event.getPoint());
        panneau.repaint();
    }
}

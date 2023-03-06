package fr.unistra.l2.poo2.td4.javafx;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PointApplication extends Application {

    VueGraphe vueGraphe;
    VueSlider vueSlider;
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {

        PointModele pm = new PointModele();
        pm.setX(5);
        pm.setY(5);

        initGrilleStage(pm, stage);
        initSliderStage(pm);

        EventHandler<MouseEvent> eventHandler = e -> {
            int x = (int) Math.round(e.getX() / 100);
            int y = (int) Math.round(e.getY() / 100);
            pm.setX(x);
            pm.setY(y);
            redraw();
        };
        vueGraphe.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);


        vueSlider.getXProperty().addListener((ov, old_val, new_val) -> {
            int x = new_val.intValue();
            pm.setX(x);
            vueGraphe.redraw();
        });

        vueSlider.getYProperty().addListener((ov, old_val, new_val) -> {
            int y = new_val.intValue();
            pm.setY(y);
            vueGraphe.redraw();
        });

    }

    private void initGrilleStage(PointModele pm, Stage grilleStage) {
        int sizeGrilleX = 1000;
        int sizeGrilleY = 1075;
        vueGraphe = new VueGraphe(pm, sizeGrilleX, sizeGrilleY);
        Scene scene = new Scene(vueGraphe, sizeGrilleX, sizeGrilleY);
        grilleStage.setTitle("Grille point");
        grilleStage.setScene(scene);
        grilleStage.show();
    }

    private void initSliderStage(PointModele pm) {
        vueSlider = new VueSlider(pm);
        var windowSliderStage = new Stage();
        Scene scene2 = new Scene(vueSlider, 200, 100);
        windowSliderStage.setTitle("Sliders");
        windowSliderStage.setScene(scene2);
        windowSliderStage.show();
    }

    public void redraw() {
        vueGraphe.redraw();
        vueSlider.redraw();
    }

}

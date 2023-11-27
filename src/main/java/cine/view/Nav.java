package cine.view;

import javafx.scene.control.Button;

public class Nav {
    private final String elegido = "-fx-background-color: black; -fx-text-fill: white; -fx-border-color: white; -fx-cursor: DEFAULT;";
    private final String noElegido = "-fx-background-color: white; -fx-text-fill: black; -fx-border-color: black; -fx-cursor: HAND;";

    public void setStyle(Button opcion, boolean elegir) {
        if (elegir) {
            opcion.setStyle(elegido);
            opcion.setDisable(true);
            opcion.setOpacity(1);
            return;
        }
        opcion.setStyle(noElegido);
        opcion.setDisable(false);
    }
}

package cine.view;

import javafx.scene.control.Button;
import javafx.scene.layout.Region;

public class Nav {
    private final String elegido = "-fx-background-color: black; -fx-text-fill: white; -fx-border-color: white; -fx-cursor: DEFAULT;";
    private final String noElegido = "-fx-background-color: white; -fx-text-fill: black; -fx-border-color: black; -fx-cursor: HAND;";
    private final int OpcionHeight = 100;
    private final int OpcionWidth = 300;
    private final int EspacioHeight = 50;

    public Button crearOpcion(String nombre) {
        Button opcion = new Button();
        opcion.setStyle(noElegido);
        opcion.setPrefHeight(OpcionHeight);
        opcion.setPrefWidth(OpcionWidth);
        opcion.setText(nombre);
        return opcion;
    }
    
    public Region crearEspacio(){
        Region espacio = new Region();
        espacio.setPrefHeight(EspacioHeight);
        return espacio;
    }

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

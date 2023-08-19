package cine.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Carga {

    
    public Label armar() {
        Label carga = new Label("Cargando...");
        carga.setPrefHeight(75);
        carga.setPrefWidth(250);
        String style = "-fx-background-color: white;-fx-background-insets: 0;-fx-border-color: black;-fx-border-width: 5;";
        carga.setStyle(style);
        carga.setFont(Font.font(29));
        carga.setAlignment(Pos.CENTER);
        return carga;
    }
}

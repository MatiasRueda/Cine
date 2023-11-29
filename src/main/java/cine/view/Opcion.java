package cine.view;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class Opcion {
    private int BOTON_HEIGHT = 80;
    private int BOTON_WIDTH = 160;
    private final double font = 20;
    private String DEFAULT_STYLE = "-fx-border-color: black; -fx-text-fill: black; -fx-border-width: 2;";
    private String SELECT_STYLE = "-fx-border-color: black; -fx-text-fill: white; -fx-border-width: 2;";

    public Opcion(String defaultColor, String selectColor) {
        this.DEFAULT_STYLE += "-fx-background-color: "+ defaultColor + ";";
        this.SELECT_STYLE += "-fx-background-color: "+ selectColor + ";";
    }

    public void colorearElegido(Button opcion) {
        opcion.setStyle(SELECT_STYLE);
    }

    public void colorearDefault(Button opcion) {
        opcion.setStyle(DEFAULT_STYLE);
    }

    public Button armar(String elemento) {
        Button boton = new Button(elemento);
        boton.setCursor(Cursor.HAND);
        boton.setFont(Font.font(this.font));
        boton.setPrefHeight(this.BOTON_HEIGHT);
        boton.setPrefWidth(this.BOTON_WIDTH);
        boton.setStyle(DEFAULT_STYLE);
        return boton;
    }
}

package cine.view;

import javafx.scene.Cursor;
import javafx.scene.control.Button;

public class Opciones {
    private String eleccion;
    private Button anteriorBtn;
    private int BOTON_HEIGHT = 45;
    private int BOTON_WIDTH = 100;
    private String DEFAULT_STYLE = "-fx-border-color: black; -fx-text-fill: black; -fx-border-width: 2;";
    private String SELECT_STYLE = "-fx-border-color: black; -fx-text-fill: white; -fx-border-width: 2;";

    public Opciones(String defaultColor, String selectColor) {
        this.DEFAULT_STYLE += "-fx-background-color: "+ defaultColor + ";";
        this.SELECT_STYLE += "-fx-background-color: "+ selectColor + ";";
    }

    public Opciones(String defaultColor, String selectColor, int height, int width) {
        this.DEFAULT_STYLE += "-fx-background-color: "+ defaultColor + ";";
        this.SELECT_STYLE += "-fx-background-color: "+ selectColor + ";";
        this.BOTON_HEIGHT = height;
        this.BOTON_WIDTH = width;
    }

    private void setAction(Button boton) {
        if (anteriorBtn != null) anteriorBtn.setStyle(DEFAULT_STYLE);
        boton.setStyle(SELECT_STYLE);
        eleccion = boton.getText();
        anteriorBtn = boton;
    }

    public Button armar(String elemento) {
        Button boton = new Button(elemento);
        boton.setCursor(Cursor.HAND);
        boton.setPrefHeight(this.BOTON_HEIGHT);
        boton.setPrefWidth(this.BOTON_WIDTH);
        boton.setStyle(DEFAULT_STYLE);
        boton.setOnAction(e -> setAction(boton));
        return boton;
    }

    public String getEleccion(){
        return this.eleccion;
    }

}

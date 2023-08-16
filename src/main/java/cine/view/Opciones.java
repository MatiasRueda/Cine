package cine.view;

import javafx.scene.control.Button;



public class Opciones {
    private String eleccion;
    private Button anteriorBtn;
    private int BOTON_HEIGHT = 40;
    private int BOTON_WIDTH = 80;
    private String DEFAULT_STYLE = "-fx-border-color: black; -fx-text-fill: black;";
    private String SELECT_STYLE = "-fx-border-color: black; -fx-text-fill: white;";

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

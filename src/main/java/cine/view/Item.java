package cine.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Item {

    private final int CONTENEDOR_HEIGHT = 20;
    private final int CONTENEDOR_WIDTH = 235;
    private final int ETIQUETA_HEIGHT = 20;
    private final int ETIQUETA_WIDTH = 215;
    private final int BOTON_HEIGHT = 20;
    private final int BOTON_WIDTH = 20;


    private Label etiqueta(String texto) {
        Label etiqueta = new Label(texto);
        etiqueta.setPrefHeight(this.ETIQUETA_HEIGHT); 
        etiqueta.setPrefWidth(this.ETIQUETA_WIDTH);  
        etiqueta.setPadding(new Insets(0, 0 ,0 , 10));
        return etiqueta;
    }

    private Button boton(VBox lista, HBox contenedor) {
        Button boton = new Button("X");
        boton.setPrefHeight(this.BOTON_HEIGHT);
        boton.setPrefWidth(this.BOTON_WIDTH);
        return boton;
    }

    public HBox armar(VBox lista, String texto, boolean agregarBoton) {
        final HBox contenedor = new HBox();
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setPrefHeight(this.CONTENEDOR_HEIGHT);
        contenedor.setPrefWidth(this.CONTENEDOR_WIDTH);
        Label nombre = etiqueta(texto);
        contenedor.getChildren().add(nombre);
        if (!agregarBoton) return contenedor;
        Button boton = boton(lista, contenedor);
        contenedor.getChildren().add(boton);
        return contenedor;
    }
    
}

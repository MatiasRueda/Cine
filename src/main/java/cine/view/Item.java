package cine.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Item {

    private Label etiqueta(String texto) {
        Label etiqueta = new Label(texto);
        etiqueta.setPrefHeight(20); 
        etiqueta.setPrefWidth(215);  
        etiqueta.setPadding(new Insets(0, 0 ,0 , 10));
        return etiqueta;
    }

    private Button boton(VBox lista, HBox contenedor) {
        Button boton = new Button("X");
        boton.setPrefHeight(20);
        boton.setPrefWidth(20);
        return boton;
    }

    public HBox armar(VBox lista, String texto, boolean agregarBoton) {
        final HBox contenedor = new HBox();
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setPrefHeight(20);
        contenedor.setPrefWidth(235);
        Label nombre = etiqueta(texto);
        contenedor.getChildren().add(nombre);
        if (!agregarBoton) return contenedor;
        Button boton = boton(lista, contenedor);
        contenedor.getChildren().add(boton);
        return contenedor;
    }
    
}

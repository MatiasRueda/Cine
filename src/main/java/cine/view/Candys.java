package cine.view;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.VBox;

public class Candys {

    private final int CONTENEDOR_HEIGHT = 120;
    private final int CONTENEDOR_WIDTH = 100;

    private final int IMAGE_HEIGHT = 90;
    private final int IMAGE_WIDTH = 90;

    private final int ETIQUETA_HEIGHT = 10;
    private final int ETIQUETA_WIDTH = 100;

    private final int BOTON_HEIGHT = 20;
    private final int BOTON_WIDTH = 100;


    private ImageView imagen(String imagen) {
        ImageView imagenView = new ImageView();
        imagenView.prefHeight(this.IMAGE_HEIGHT);
        imagenView.prefWidth(this.IMAGE_WIDTH);
        Image productoImagen = new Image(imagen, this.IMAGE_WIDTH, this.IMAGE_HEIGHT, false, false);
        imagenView.setImage(productoImagen);
        return imagenView;
    }

    private Label etiqueta(String nombre) {
        Label etiqueta = new Label(nombre);
        etiqueta.setAlignment(Pos.CENTER);
        etiqueta.setPrefHeight(this.ETIQUETA_HEIGHT);
        etiqueta.setPrefWidth(this.ETIQUETA_WIDTH);
        return etiqueta;
    }
    
    private Button boton(String nombre, String precio) {
        Button boton = new Button("Comprar " + precio + " $");
        boton.setStyle("-fx-background-color: white; -fx-border-color: black");
        boton.setId(nombre);
        boton.setCursor(Cursor.HAND);
        boton.prefHeight(this.BOTON_HEIGHT);
        boton.prefWidth(this.BOTON_WIDTH);
        return boton;
    }


    public VBox armar(String nombre, String precio, String imagen) {
        VBox contenedor = new VBox();
        contenedor.setPrefHeight(this.CONTENEDOR_HEIGHT);
        contenedor.setPrefWidth(this.CONTENEDOR_WIDTH);
        ImageView productoImagen = imagen(imagen);
        Label productoNombre = etiqueta(nombre);
        Button boton = boton(nombre, precio);
        contenedor.getChildren().addAll(productoImagen, productoNombre, boton);
        return contenedor;
    }
}

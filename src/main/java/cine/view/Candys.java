package cine.view;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Candys {

    private final int CONTENEDOR_HEIGHT = 200;
    private final int CONTENEDOR_WIDTH = 150;

    private final int IMAGE_HEIGHT = 150;
    private final int IMAGE_WIDTH = 150;

    private final int ETIQUETA_HEIGHT = 20;
    private final int ETIQUETA_WIDTH = 150;

    private final int BOTON_HEIGHT = 30;
    private final int BOTON_WIDTH = 150;


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
        etiqueta.setFont(Font.font(20));
        return etiqueta;
    }
    
    private Button boton(String nombre, String precio) {
        Button boton = new Button("Comprar " + precio + " $");
        boton.setId(nombre);
        boton.setCursor(Cursor.HAND);
        boton.prefHeight(this.BOTON_HEIGHT);
        boton.prefWidth(this.BOTON_WIDTH);
        boton.setFont(Font.font(15));
        return boton;
    }


    public VBox armar(String nombre, String precio, String imagen) {
        VBox contenedor = new VBox();
        contenedor.setPrefHeight(this.CONTENEDOR_HEIGHT);
        contenedor.setPrefWidth(this.CONTENEDOR_WIDTH);
        contenedor.setAlignment(Pos.CENTER);
        ImageView productoImagen = imagen(imagen);
        Label productoNombre = etiqueta(nombre);
        Button boton = boton(nombre, precio);
        contenedor.getChildren().addAll(productoImagen, productoNombre, boton);
        return contenedor;
    }
}

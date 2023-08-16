package cine.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.VBox;

public class Candys {

    final private int IMAGE_HEIGHT = 90;
    final private int IMAGE_WIDTH = 90;
    final private int HEIGHT = 120;
    final private int WIDTH = 100;


    public VBox armarCandys(String nombre, String precio, String imagen) {
        VBox candy = new VBox();
        candy.setPrefHeight(this.HEIGHT);
        candy.setPrefWidth(this.WIDTH);
        ImageView imagenvView = new ImageView();
        imagenvView.prefHeight(this.IMAGE_HEIGHT);
        imagenvView.prefWidth(this.IMAGE_WIDTH);
        Image productoImagen = new Image(imagen, this.IMAGE_WIDTH, this.IMAGE_HEIGHT, false, false);
        imagenvView.setImage(productoImagen);
        Label productoNombre = new Label();
        productoNombre.setPrefHeight(10);
        productoNombre.setPrefWidth(this.WIDTH);
        productoNombre.setText(nombre);
        Button button = new Button();
        button.setId(nombre);
        button.prefHeight(20);
        button.prefWidth(this.WIDTH);
        button.setText("Comprar " + precio + " $");
        candy.getChildren().addAll(imagenvView, productoNombre, button);
        return candy;
    }
}

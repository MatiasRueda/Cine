package cine.view;

import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Pelicula {

    private final int CONTENEDOR_HEIGHT = 300;
    private final int CONTENEDOR_WIDTH = 175;
    private final int IMAGEN_HEIGHT = 260;

    public VBox crear(String titulo, String url) {
        VBox pane = new VBox();
        pane.setPrefHeight(CONTENEDOR_HEIGHT);
        pane.setPrefWidth(CONTENEDOR_WIDTH);
        pane.setCursor(Cursor.HAND);
        pane.setSpacing(10);
        ImageView imagenView = new ImageView();
        imagenView.prefHeight(IMAGEN_HEIGHT);
        imagenView.prefWidth(CONTENEDOR_WIDTH);
        Image imagen = new Image(url, CONTENEDOR_WIDTH, IMAGEN_HEIGHT, false, false);
        imagenView.setImage(imagen);
        Text texto = new Text(titulo);
        texto.setWrappingWidth(CONTENEDOR_WIDTH);
        texto.setFont(Font.font(15));
        texto.setTextAlignment(TextAlignment.CENTER);
        pane.getChildren().addAll(imagenView, texto);
        return pane;
    }
}

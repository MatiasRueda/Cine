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

    private VBox contenedor() {
        VBox contenedor = new VBox();
        contenedor.setPrefHeight(CONTENEDOR_HEIGHT);
        contenedor.setPrefWidth(CONTENEDOR_WIDTH);
        contenedor.setSpacing(10);
        return contenedor;
    }

    private ImageView imagen(String url) {
        ImageView imagenView = new ImageView();
        imagenView.prefHeight(IMAGEN_HEIGHT);
        imagenView.prefWidth(CONTENEDOR_WIDTH);
        Image imagen = new Image(url, CONTENEDOR_WIDTH, IMAGEN_HEIGHT, false, false);
        imagenView.setImage(imagen);
        return imagenView;
    }

    private Text texto(String titulo) {
        Text texto = new Text(titulo);
        texto.setWrappingWidth(CONTENEDOR_WIDTH);
        texto.setFont(Font.font(15));
        texto.setTextAlignment(TextAlignment.CENTER);
        return texto;
    }

    public VBox crearEstreno(String titulo, String url) {
        VBox contenedor = this.contenedor();
        contenedor.setCursor(Cursor.HAND);
        ImageView imagen = this.imagen(url);
        Text texto = this.texto(titulo);
        contenedor.getChildren().addAll(imagen, texto);
        return contenedor;
    }

    public VBox crearProximamente(String titulo, String url) {
        VBox contenedor = this.contenedor();
        ImageView imagen = this.imagen(url);
        Text texto = this.texto(titulo);
        contenedor.getChildren().addAll(imagen, texto);
        return contenedor;
    }
}

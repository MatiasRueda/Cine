package cine.visual;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class Pelicula {
    private String titulo;
    private String imagen;
    private String fecha;
    private String audio;
    private String subtitulo;
    private String duracion;
    private Tab pelicula;
    private final int HEIGHT = 300;
    private final int WIDTH = 600;
    private final int HEIGHT_REGION = 10;
    
    public Pelicula(String titulo, String imagen,String fecha, String audio, String subtitulo, String duracion) {
        this.titulo = titulo;
        this.imagen = imagen;
        this.fecha = fecha;
        this.audio = audio;
        this.subtitulo = subtitulo;
        this.duracion = duracion;
        this.pelicula = armarPelicula();
    }

    public Pelicula(List<String> informacion) {
        this.titulo = informacion.get(0);
        this.fecha = informacion.get(1);
        this.audio = informacion.get(2);
        this.subtitulo = informacion.get(3);
        this.duracion = informacion.get(4);
        this.imagen = informacion.get(5); 
        this.pelicula = armarPelicula();
    }

    private VBox contVBox(int width) {
        VBox infoPeli = new VBox();
        infoPeli.setPrefHeight(this.HEIGHT);
        infoPeli.setPrefWidth(width);
        infoPeli.setAlignment(Pos.CENTER);
        return infoPeli;
    }

    private HBox contenedorPeli() {
        HBox contPeli = new HBox();
        contPeli.setAlignment(Pos.CENTER);
        contPeli.setPrefHeight(this.HEIGHT);
        contPeli.setPrefWidth(this.WIDTH);
        VBox contImagen = contVBox(200);
        ImageView imagenPeli = new ImageView();
        imagenPeli.prefHeight(175);
        imagenPeli.prefWidth(125);
        Image imagen = new Image(this.imagen, 125, 175, false, false);
        imagenPeli.setImage(imagen);
        contImagen.getChildren().add(imagenPeli);
        VBox contDescripcion = contVBox(400);
        Label titulo = new Label("Titulo: " + this.titulo);
        Label fecha = new Label("Fecha de estreno: "+ this.fecha);   
        Label audio = new Label("Audio: " + this.audio);
        Label subtitulo = new Label("Subtitulo: " + this.subtitulo);
        Label duracion = new Label("Duracion: " + this.duracion + " min");
        Region region = new Region();
        region.setPrefHeight(HEIGHT_REGION); 
        Region region2 = new Region();
        region2.setPrefHeight(HEIGHT_REGION);
        Region region3 = new Region();
        region3.setPrefHeight(HEIGHT_REGION);
        Region region4 = new Region();
        region4.setPrefHeight(HEIGHT_REGION);
        contDescripcion.getChildren().addAll(titulo, region, fecha, region2, audio, region3, subtitulo , region4, duracion);
        contPeli.getChildren().addAll(contImagen, contDescripcion);       
        return contPeli;
    }

    private Tab armarPelicula() {
        Tab pelicula = new Tab(this.titulo);
        pelicula.setContent(contenedorPeli());
        return pelicula;
    }

    public Tab getPelicula() {
        return this.pelicula;
    }
}

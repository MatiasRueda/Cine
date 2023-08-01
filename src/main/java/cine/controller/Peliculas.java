package cine.controller;

import java.util.List;

import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Peliculas {
    private Label titulo;
    private Label fecha;
    private Label audio;
    private Label subtitulo;
    private Label duracion;
    private ImageView imagen;
    private int offset = 0;
    
    public Peliculas(Label titulo, Label fecha, Label audio, Label subtitulo, Label duracion, ImageView imagen) {
        this.titulo = titulo;
        this.fecha = fecha;
        this.audio = audio;
        this.subtitulo = subtitulo;
        this.duracion = duracion;
        this.imagen = imagen;
    }

    public void setInformacion(List<String> informacion) {
        this.titulo.setText("Titulo: " + informacion.get(0));
        this.fecha.setText("Fecha: " + informacion.get(1));
        this.audio.setText("Audio: " + informacion.get(2));
        this.subtitulo.setText("Subtitulos: " + informacion.get(3));
        this.duracion.setText("Duracion: " + informacion.get(4) +  " minutos");
        Image imagenPeli = new Image(informacion.get(5), 125, 175, false, false);
        this.imagen.setImage(imagenPeli);
    }

    public void subirOffset() {
        this.offset += 1;
    }

    public void bajarOffset() {
        this.offset -= 1;
    }

    public int getOffset() {
        return this.offset;
    }
}

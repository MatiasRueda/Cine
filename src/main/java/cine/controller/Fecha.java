package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import cine.model.Usuario;
import cine.view.Opciones;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class Fecha {

    private Cine cine = Controlador.cine;
    private Escenas escenas = Controlador.escenas;
    private Usuario usuario = Controlador.usuario;
    private Opciones opciones = new Opciones("white", "red");

    @FXML
    private StackPane stackFecha;

    @FXML
    private VBox fecha;

    @FXML
    private FlowPane fechas;

    @FXML
    private Label labelFecha;

    @FXML
    void initialize() {
        this.labelFecha.setText("Fechas disponibles para: " + this.usuario.getTituloPelicula());
        for (String fecha: this.cine.getFechas()) {
            Button boton = this.opciones.armar(fecha);
            this.fechas.getChildren().add(boton);
        }
    }

    @FXML
    void cancelar(ActionEvent event) throws IOException {
        this.usuario.reiniciarValores();
        this.cine.reiniciarValores();
        this.escenas.mostrarEscenaSig(ESCENA.INICIO);
    }

    @FXML
    void continuar(ActionEvent event) throws IOException {
        String opcion = this.opciones.getEleccion();
        if (opcion == null) {
            this.escenas.mensajeError("Elija alguna opcion");
            return;
        }
        this.usuario.setFechaPelicula(opcion);
        this.escenas.mostrarEscenaSig(ESCENA.HORARIO);
    }

}
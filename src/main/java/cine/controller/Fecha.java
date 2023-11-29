package cine.controller;

import java.io.IOException;
import java.sql.SQLException;

import cine.model.Cine;
import cine.model.Usuario;
import cine.view.Nav;
import cine.view.Opciones;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Fecha {

    private Cine cine = Controlador.cine;
    private Escenas escenas = Controlador.escenas;
    private Usuario usuario = Controlador.usuario;
    private Opciones opciones = new Opciones("white", "red");
    private Nav nav = NavUsuario.nav;

    @FXML
    private VBox fecha;

    @FXML
    private FlowPane fechas;

    @FXML
    private Text textFecha;

    @FXML
    void initialize() throws SQLException {
        nav.indicarComprar();
        this.textFecha.setText("Fechas disponibles para: " + this.usuario.getTituloPelicula());
        for (String fecha: this.cine.getFechas()) {
            Button boton = this.opciones.armar(fecha);
            this.fechas.getChildren().add(boton);
        }
    }

    @FXML
    void continuar(ActionEvent event) throws IOException, InterruptedException {
        String opcion = this.opciones.getEleccion();
        if (opcion == null) {
            this.escenas.mensajeError("Elija alguna opcion");
            return;
        }
        this.usuario.setFechaPelicula(opcion);
        this.escenas.cargarSiguienteEscena(ESCENA.HORARIO);
    }

}
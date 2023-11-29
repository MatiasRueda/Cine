package cine.controller;

import java.io.IOException;
import java.sql.SQLException;

import cine.model.Cine;
import cine.model.Usuario;
import cine.view.Nav;
import cine.view.Opcion;
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
    private Opcion opcion = new Opcion("white", "red");
    private Nav nav = NavUsuario.nav;
    private String eleccion;
    private Button anterior;

    @FXML
    private VBox fecha;

    @FXML
    private FlowPane fechas;

    @FXML
    private Text textFecha;

    private void elegir(Button boton) {
        if (anterior != null) 
            this.opcion.colorearDefault(anterior);
        this.opcion.colorearElegido(boton);
        this.eleccion = boton.getText();
        this.anterior = boton;
    }

    @FXML
    void initialize() throws SQLException {
        nav.indicarComprar();
        this.textFecha.setText("Fechas disponibles para: " + this.usuario.getTituloPelicula());
        for (String fecha: this.cine.getFechas()) {
            Button boton = this.opcion.armar(fecha);
            boton.setOnAction(e -> elegir(boton));
            this.fechas.getChildren().add(boton);
        }
    }

    @FXML
    void continuar(ActionEvent event) throws IOException, InterruptedException {
        if (this.eleccion == null) {
            this.escenas.mensajeError("Elija alguna opcion");
            return;
        }
        this.usuario.setFechaPelicula(this.eleccion);
        this.escenas.cargarSiguienteEscena(ESCENA.HORARIO);
    }

}
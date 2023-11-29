package cine.controller;

import java.io.IOException;
import java.sql.SQLException;

import cine.model.Cine;
import cine.model.Usuario;
import cine.view.Opcion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Horario {
    private Cine cine = Controlador.cine;
    private Escenas escenas = Controlador.escenas;
    private Usuario usuario = Controlador.usuario;
    private Opcion opcion = new Opcion("white", "green");
    private String eleccion;
    private Button anterior;

    @FXML
    private VBox horario;

    @FXML
    private FlowPane horarios;

    @FXML
    private Text textHorarios;

    @FXML
    private Label labelFecha;

    private void elegir(Button boton) {
        if (anterior != null) 
            opcion.colorearDefault(anterior);
        opcion.colorearElegido(boton);
        this.eleccion = boton.getText();
        this.anterior = boton;
    }

    @FXML
    void initialize() throws SQLException {
        this.textHorarios.setText("Horarios para: " + this.usuario.getTituloPelicula());
        this.labelFecha.setText("Fecha elegida: " + this.usuario.getFechaPelicula());
        for (String horario: this.cine.getHorarios()) {
            Button boton = this.opcion.armar(horario);
            boton.setOnAction(e -> elegir(boton));
            this.horarios.getChildren().add(boton);        
        }
    }

    @FXML
    void continuar(ActionEvent event) throws IOException, InterruptedException {
        if (this.eleccion == null) {
            this.escenas.mensajeError("Elija alguna opcion");
            return;
        }
        this.usuario.setHorario(this.eleccion);
        this.usuario.setSala(this.eleccion, this.cine.getSalas());
        this.escenas.cargarSiguienteEscena(ESCENA.SALA);
    }
}
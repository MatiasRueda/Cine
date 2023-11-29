package cine.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import cine.model.Cine;
import cine.model.Usuario;
import cine.view.Butacas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Sala {
    private Cine cine = Controlador.cine;
    private Escenas escenas = Controlador.escenas;
    private Usuario usuario = Controlador.usuario;
    private Butacas butacas = new Butacas();
    private ArrayList<ArrayList<Integer>> reservas;
    private Button anterior;
    private Integer fila = -1;
    private Integer columna = -1;

    @FXML
    private VBox sala;

    @FXML
    private GridPane butacasDos;

    @FXML
    private GridPane butacasUno;

    @FXML
    void initialize() throws SQLException {
        this.reservas = this.cine.getReservas();
        this.setActionGridPane(this.butacasUno);
        this.setActionGridPane(this.butacasDos);
    }
    
    private void action(Node node, Integer fila, Integer columna) {
        if (this.anterior != null) 
            butacas.colorDefault(anterior);
        butacas.colorearElegido((Button) node);
        this.fila = fila;
        this.columna = columna;
        this.anterior = (Button) node;
        this.usuario.setFila(fila);
        this.usuario.setColumna(columna);
    }

    public void setActionGridPane(GridPane gridpane) throws SQLException {
        for (Node node:  gridpane.getChildren()) {
            if (!(node instanceof Button)) 
                continue;
            Button boton = (Button) node;
            Integer fila = GridPane.getRowIndex(node);
            Integer columna = Integer.valueOf(boton.getText());
            ArrayList<Integer> ubicacion = new ArrayList<>();
            ubicacion.add(fila);
            ubicacion.add(columna);
            boton.setOnAction(e -> action(node, fila, columna));
            if (!this.reservas.contains(ubicacion)) 
                continue;
            butacas.colorOcupada(boton);
        }
    }

    @FXML
    void continuar(ActionEvent event) throws IOException, InterruptedException {
        if (this.fila == -1 && this.columna == -1) {
            this.escenas.mensajeError("Elija alguna butaca");
            return;
        }
        this.escenas.cargarSiguienteEscena(ESCENA.OPCION);
    }

}
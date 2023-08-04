package cine.controller;

import java.io.IOException;
import java.util.ArrayList;

import cine.model.Cine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SalaController {
   private Cine cine = MenuController.cine;
   private ArrayList<ArrayList<Integer>> reservas = this.cine.getFilaColumnaReservadas();

   @FXML
   private VBox sala;

   @FXML
    private GridPane butacasDos;

   @FXML
   private GridPane butacasUno;

   @FXML
   void initialize() {
      setActionGridPane(this.butacasUno);
      setActionGridPane(this.butacasDos);
   }

   private void setFilaColumna(Node node, Integer fila, Integer columna) {
      this.cine.setFila(fila);
      this.cine.setColumna(columna);
   }

   private void setActionGridPane(GridPane gridpane) {
      for (Node node:  gridpane.getChildren()) {
         if (!(node instanceof Button)) continue;
         Button boton = (Button) node;
         Integer fila = GridPane.getRowIndex(node);
         Integer columna = Integer.valueOf(boton.getText());
         ArrayList<Integer> ubicacion = new ArrayList<>();
         ubicacion.add(fila);
         ubicacion.add(columna);
         boton.setOnAction(e -> setFilaColumna(node, fila, columna));
         if (!this.reservas.contains(ubicacion)) continue;
         boton.setStyle("-fx-background-color: red");
         boton.setDisable(true);
      }
   }


   @FXML
   void cancelar(ActionEvent event) {
   }

   @FXML
   void comprar(ActionEvent event) throws IOException {
      this.cine.guardarEleccion();
      this.cine.reiniciarValores();
      Escenas.mostrarEscenaSig(this.sala, "usuarioMenu");
   }

}
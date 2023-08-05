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
   private Escenas escenas = MenuController.escenas;
   private ArrayList<ArrayList<Integer>> reservas = this.cine.getFilaColumnaReservadas();
   private Button anteriorBtn;
   private String DEFAULT_STYLE = "-fx-border-color: black; -fx-text-fill: black; -fx-background-color: white;";
   private String SELECT_STYLE = "-fx-border-color: black; -fx-text-fill: white; -fx-background-color: green;";

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

   private void action(Node node, Integer fila, Integer columna) {
      if (this.anteriorBtn != null) anteriorBtn.setStyle(this.DEFAULT_STYLE);
      node.setStyle(this.SELECT_STYLE);
      this.anteriorBtn = (Button) node;
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
         boton.setOnAction(e -> action(node, fila, columna));
         if (!this.reservas.contains(ubicacion)) continue;
         boton.setStyle("-fx-background-color: red");
         boton.setDisable(true);
      }
   }


   @FXML
   void cancelar(ActionEvent event) throws IOException{
      this.cine.reiniciarValores();
      this.escenas.mostrarEscenaSig("usuarioMenu");
   }

   @FXML
   void comprar(ActionEvent event) throws IOException {
      this.cine.guardarEleccion();
      this.cine.reiniciarValores();
      this.escenas.mostrarEscenaSig("usuarioMenu");
   }

}
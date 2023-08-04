package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class SalaController {
   private Cine cine = MenuController.cine;

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

   private void setColumnaFila(Node node) {
      Integer columna = GridPane.getColumnIndex(node);
      Integer fila = GridPane.getRowIndex(node);
      this.cine.setColumna(columna);
      this.cine.setFila(fila);
   }

   private void setActionGridPane(GridPane gridpane) {
      for (Node node:  gridpane.getChildren()) {
         if (!(node instanceof Button)) continue;
         Button boton = (Button) node;
         boton.setOnAction(e -> setColumnaFila(node));
      }
   }


   @FXML
   void cancelar(ActionEvent event) {
   }

   @FXML
   void comprar(ActionEvent event) throws IOException {
      this.cine.guardarEleccion();
      Escenas.mostrarEscenaSig(this.sala, "menu");
   }

}
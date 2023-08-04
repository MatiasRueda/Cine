package cine.controller;

import cine.model.Cine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class SalaController {
   Cine cine = MenuController.cine;

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
      cine.setColumna(columna);
      cine.setFila(fila);
      System.out.println(GridPane.getColumnIndex(node));
      System.out.println(GridPane.getRowIndex(node));
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
   void comprar(ActionEvent event) {

   }

}
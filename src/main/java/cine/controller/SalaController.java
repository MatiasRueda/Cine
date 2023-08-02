package cine.controller;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class SalaController {

    @FXML
    private GridPane butacasDos;

    @FXML
    private GridPane butacasUno;

    @FXML
    void initialize() {
        setBotones();
    }


    private void setBotones() {
       for (Node node:  butacasUno.getChildren()) {
          if (!(node instanceof Button)) continue;
          Button boton = (Button) node;
          boton.setOnAction(e -> { System.out.println(GridPane.getRowIndex(node));});
       }
       for (Node node:  butacasDos.getChildren()) {
          if (!(node instanceof Button)) continue;
          Button boton = (Button) node;
          boton.setOnAction(e -> { System.out.println(GridPane.getColumnIndex(node));});
       }
    }


    @FXML
    void cancelar(ActionEvent event) {

    }

    @FXML
    void comprar(ActionEvent event) {

    }

}
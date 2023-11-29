package cine.controller;

import java.io.IOException;

import cine.model.Cine;
import cine.model.Usuario;
import cine.view.Butacas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Sala {
   private Cine cine = Controlador.cine;
   private Escenas escenas = Controlador.escenas;
   private Usuario usuario = Controlador.usuario;
   private Butacas butacas = new Butacas(this.usuario, this.cine.getReservas());

   @FXML
   private VBox sala;

   @FXML
    private GridPane butacasDos;

   @FXML
   private GridPane butacasUno;

   @FXML
   void initialize() {
      this.butacas.setActionGridPane(this.butacasUno);
      this.butacas.setActionGridPane(this.butacasDos);
   }

   @FXML
   void comprar(ActionEvent event) throws IOException, InterruptedException {
      this.escenas.cargarSiguienteEscena(ESCENA.OPCION);
   }

}
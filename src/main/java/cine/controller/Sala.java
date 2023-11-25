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
   private Cine cine = Menu.cine;
   private Escenas escenas = Menu.escenas;
   private Usuario usuario = Menu.usuario;
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
   void cancelar(ActionEvent event) throws IOException{
      this.usuario.reiniciarValores();
      this.cine.reiniciarValores();
      this.escenas.mostrarEscenaSig("usuarioMenu");
   }

   @FXML
   void comprar(ActionEvent event) throws IOException {
      this.cine.guardarEleccion();
      this.usuario.reiniciarValores();
      this.cine.reiniciarValores();
      this.escenas.mostrarEscenaSig("compraExito");
   }

}
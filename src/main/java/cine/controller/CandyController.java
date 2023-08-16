package cine.controller;

import java.util.ArrayList;

import cine.model.Cine;
import cine.view.Candys;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class CandyController {
    private Cine cine = MenuController.cine;
    private Candys candys = new Candys();

    @FXML
    private VBox candy;

    @FXML
    private FlowPane elementos;

    @FXML
    private ListView<String> lista;

    @FXML
    private Label precio;

    

    private void setBoton(VBox vbox) {
        for (Node elemento : vbox.getChildren()) {
            if (!(elemento instanceof Button)) continue;
            Button button = (Button) elemento;
            button.setOnAction(e -> {
                String nombreProducto = button.getId();
                this.lista.getItems().add(nombreProducto);
                this.cine.agregarProducto(nombreProducto);
                String precio = String.valueOf(this.cine.getPrecioTotal());
                this.precio.setText(precio);
            });
        }  
    }

    private void recorrerVbox() {
        for (Node elemento : this.elementos.getChildren()) {
            if (!(elemento instanceof VBox)) continue;
            this.setBoton((VBox) elemento);
        }   
    }

    @FXML
    void initialize() {
        ArrayList<ArrayList<String>> filas = this.cine.getCandy();
        for (ArrayList<String> fila : filas) {
            VBox producto = this.candys.armarCandys(fila.get(0), fila.get(1), fila.get(2));
            this.elementos.getChildren().add(producto);
        }
        this.recorrerVbox();
    }



    @FXML
    void cancelar(ActionEvent event) {

    }

    @FXML
    void continuar(ActionEvent event) {

    }

    
    @FXML
    void eliminar(ActionEvent event) {
        if (this.lista.getItems().size() == 0) return;
        int indice = this.lista.getSelectionModel().getSelectedIndex();
        String nombreProducto = this.lista.getSelectionModel().getSelectedItem();
        this.lista.getItems().remove(indice);
        this.cine.sacarProducto(nombreProducto);
        String precio = String.valueOf(this.cine.getPrecioTotal());
        this.precio.setText(precio);
    }

}
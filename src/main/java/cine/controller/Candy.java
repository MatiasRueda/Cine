package cine.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import cine.model.Cine;
import cine.model.Usuario;
import cine.view.Candys;
import cine.view.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Candy {
    private Cine cine = Controlador.cine;
    private Escenas escenas = Controlador.escenas;
    private Usuario usuario = Controlador.usuario;
    private Item fabrica = new Item();
    private Candys candys = new Candys();

    @FXML
    private VBox candy;

    @FXML
    private FlowPane elementos;

    @FXML
    private VBox contenedor;

    @FXML
    private Label precio;

    private void sacarDeLaLista(HBox item, String productoNombre) {
        this.contenedor.getChildren().remove(item);
        this.cine.sacarProducto(productoNombre);
        this.precio.setText(String.valueOf(this.usuario.getPrecioCandy()));
    }

    private void setActionLista(HBox item, String productoNombre) {
        for(Node node : item.getChildren()) {
            if (!(node instanceof Button))
                continue;
            Button boton = (Button) node;
            boton.setOnAction(e -> sacarDeLaLista(item, productoNombre));
        }
    }

    private void agregarItemLista(String productoNombre) {
        HBox item = this.fabrica.armar(productoNombre, true);
        this.setActionLista(item, productoNombre);
        this.contenedor.getChildren().add(item);
        this.cine.agregarProducto(productoNombre);
        this.precio.setText(String.valueOf(this.usuario.getPrecioCandy()));
    }

    private void setActionElementos(VBox vbox) {
        for (Node elemento : vbox.getChildren()) {
            if (!(elemento instanceof Button))
                continue;
            Button button = (Button) elemento;
            button.setOnAction(e -> agregarItemLista(button.getId()));
        }  
    }

    private void recorrerVbox() {
        for (Node elemento : this.elementos.getChildren()) {
            if (!(elemento instanceof VBox))
                continue;
            this.setActionElementos((VBox) elemento);
        }   
    }

    @FXML
    void initialize() throws SQLException {
        ArrayList<ArrayList<String>> filas = this.cine.getCandy();
        for (ArrayList<String> fila : filas) {
            VBox producto = this.candys.armar(fila.get(0), fila.get(1), fila.get(2));
            this.elementos.getChildren().add(producto);
        }
        this.recorrerVbox();
    }

    @FXML
    void continuar(ActionEvent event) throws IOException {
        if (this.contenedor.getChildren().size() == 0) {
            this.escenas.mensajeError("Elija algun producto");
            return;
        }
        this.escenas.cargarEscena(ESCENA.CONFIRMAR);
    }

}
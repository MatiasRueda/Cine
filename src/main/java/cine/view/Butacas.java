package cine.view;

import java.util.ArrayList;

import cine.model.Usuario;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Butacas {

    private Usuario usuario;
    private ArrayList<ArrayList<Integer>> reservas;
    private Button anteriorBtn;
    private String DEFAULT_STYLE = "-fx-border-color: black; -fx-text-fill: black; -fx-background-color: white;";
    private String SELECT_STYLE = "-fx-border-color: black; -fx-text-fill: white; -fx-background-color: green;";

    public Butacas(Usuario usuario, ArrayList<ArrayList<Integer>> reservas) {
        this.usuario = usuario;
        this.reservas = reservas;
    }

    private void action(Node node, Integer fila, Integer columna) {
        if (this.anteriorBtn != null) anteriorBtn.setStyle(this.DEFAULT_STYLE);
        node.setStyle(this.SELECT_STYLE);
        this.anteriorBtn = (Button) node;
        this.usuario.setFila(fila);
        this.usuario.setColumna(columna);
    }

    public void setActionGridPane(GridPane gridpane) {
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
}

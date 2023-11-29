package cine.view;

import javafx.scene.control.Button;

public class Butacas {

    private String DEFAULT_STYLE = "-fx-border-color: black; -fx-text-fill: black; -fx-background-color: white;";
    private String SELECT_STYLE = "-fx-border-color: black; -fx-text-fill: white; -fx-background-color: green;";

    public void colorearElegido(Button butaca) {
        butaca.setStyle(SELECT_STYLE);
    }

    public void colorDefault(Button butaca) {
        butaca.setStyle(DEFAULT_STYLE);
    }

    public void colorOcupada(Button butaca) {
        butaca.setStyle("-fx-background-color: red");
        butaca.setDisable(true);
    }

}

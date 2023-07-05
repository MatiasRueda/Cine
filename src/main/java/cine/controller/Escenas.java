package cine.controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import cine.App;

public class Escenas {

    public static FXMLLoader getFXML(String fxml) {
        return new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = getFXML(fxml);
        return fxmlLoader.load();
    }

    static public void mostrarEscenaSig(Pane escenaActual, String siguienteEscena) throws IOException{
        Parent root = loadFXML(siguienteEscena);
        Scene scena = new Scene(root);
        Stage stage = (Stage)escenaActual.getScene().getWindow();
        stage.setScene(scena);
    }

    static public void mostrarEscenaSig(Pane escenaActual, Parent siguienteEscena) throws IOException{
        Scene scena = new Scene(siguienteEscena);
        Stage stage = (Stage)escenaActual.getScene().getWindow();
        stage.setScene(scena);
    }

    public static Stage getStage(Parent root, Modality modal, StageStyle style) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(modal);
        stage.initStyle(style);
        stage.setScene(scene);
        return stage;
    }

    public static void mostrarMsjError(String mensaje) throws IOException{
        FXMLLoader fxmlLoader = Escenas.getFXML("mensaje");
        Parent root = fxmlLoader.load();
        MensajeController mensajeCtrller = fxmlLoader.getController();
        mensajeCtrller.setMensaje(mensaje);
        Stage stage = Escenas.getStage(root, Modality.APPLICATION_MODAL, StageStyle.UNDECORATED);
        stage.showAndWait();
    }

}

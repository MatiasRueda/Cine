package cine.controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import cine.App;
import cine.view.Carga;

public class Escenas {
    private Stage primaryStage;
    private Carga carga = new Carga();

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    public FXMLLoader getFXML(ESCENA escena) {
        return new FXMLLoader(App.class.getResource(escena.toString().toLowerCase() + ".fxml"));
    }

    public Parent loadFXML(ESCENA fxml) throws IOException {
        FXMLLoader fxmlLoader = this.getFXML(fxml);
        return fxmlLoader.load();
    }

    public void mostrarEscenaSig(ESCENA escena) throws IOException{
        Parent root = loadFXML(escena);
        Scene scena = new Scene(root);
        this.primaryStage.setScene(scena);
    }

    public static Stage getStage(Parent root, Modality modal, StageStyle style) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(modal);
        stage.initStyle(style);
        stage.setScene(scene);
        return stage;
    }
    
    public void mensajeError(String mensaje, StackPane stackPane, Pane contenedorActual) throws IOException {
        contenedorActual.setDisable(true);
        contenedorActual.setEffect(new GaussianBlur());
        FXMLLoader fxmlLoader = this.getFXML(ESCENA.MENSAJE);
        Pane contenido = fxmlLoader.load();
        Mensaje mensajeController = fxmlLoader.getController();
        mensajeController.setMensaje(mensaje);
        mensajeController.setStackPane(stackPane);
        mensajeController.setContenedorActual(contenedorActual);
        stackPane.getChildren().add(contenido);
    }

    public Label cartelCarga() {
        return this.carga.armar();
    }

    public void cargarSiguienteEscena(ESCENA escena , StackPane stackPane, Pane contenedorActual) throws IOException, InterruptedException {
        contenedorActual.setDisable(true);
        contenedorActual.setEffect(new GaussianBlur());
        Label cartel = this.carga.armar();
        stackPane.getChildren().add(cartel);
        Thread.sleep(30);
        Platform.runLater(() -> {
            try {
                mostrarEscenaSig(escena);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }
}

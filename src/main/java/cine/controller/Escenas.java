package cine.controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import cine.App;

public class Escenas {
    private Stage primaryStage;
    private StackPane stackPane;
    private Pane escenaPrincipal;

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    public void setEscenaPrincipal(Pane escena) {
        this.escenaPrincipal = escena;
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
    
    public void mensajeError(String mensaje) throws IOException {
        this.escenaPrincipal.setDisable(true);
        this.escenaPrincipal.setEffect(new GaussianBlur());
        FXMLLoader fxmlLoader = this.getFXML(ESCENA.MENSAJE);
        Pane contenido = fxmlLoader.load();
        Mensaje mensajeController = fxmlLoader.getController();
        mensajeController.setMensaje(mensaje);
        mensajeController.setStackPane(stackPane);
        mensajeController.setContenedorActual(this.escenaPrincipal);
        stackPane.getChildren().add(contenido);
    }

    public void cargarEscena(ESCENA escena) throws IOException {
        this.escenaPrincipal.getChildren().remove(1);
        Pane escenaCargada = (Pane) this.loadFXML(escena);
        this.escenaPrincipal.getChildren().add(escenaCargada);
    }

    public void cargarSiguienteEscena(ESCENA escena , StackPane stackPane, Pane contenedorActual) throws IOException, InterruptedException {
        contenedorActual.setDisable(true);
        contenedorActual.setEffect(new GaussianBlur());
        Pane cargando = (Pane) this.loadFXML(ESCENA.CARGANDO);
        stackPane.getChildren().add(cargando);
        Thread.sleep(30);
        Platform.runLater(() -> {
            try {
                mostrarEscenaSig(escena);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}

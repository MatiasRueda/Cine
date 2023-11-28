package cine.controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import cine.App;

public class Escenas {
    private StackPane stackPane;
    private Pane escenaPrincipal;

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
    
    public void cargando() throws IOException {
        this.escenaPrincipal.setDisable(true);
        this.escenaPrincipal.setEffect(new GaussianBlur());
        Pane cargando = (Pane) this.loadFXML(ESCENA.CARGANDO);
        this.stackPane.getChildren().add(cargando);
    }

    public void sacarCargando() {
        this.escenaPrincipal.setDisable(false);
        this.escenaPrincipal.setEffect(null);
        this.stackPane.getChildren().remove(1);
    }

    public Pane getEscena(ESCENA escena) throws IOException {
        Pane escenaObtenida = (Pane) this.loadFXML(escena);
        return escenaObtenida;
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
        Pane escenaCargada = (Pane) this.loadFXML(escena);
        this.escenaPrincipal.getChildren().remove(1);
        this.escenaPrincipal.getChildren().add(escenaCargada);
    }

    public void usuario() throws IOException {
        Pane navUsuario = (Pane) this.loadFXML(ESCENA.NAVUSUARIO);
        Pane inicio = (Pane) this.loadFXML(ESCENA.INICIO);
        this.escenaPrincipal.getChildren().remove(1);
        this.escenaPrincipal.getChildren().remove(0);
        this.escenaPrincipal.getChildren().addAll(navUsuario, inicio);
    }

    public void cargarSiguienteEscena(ESCENA escena) throws IOException, InterruptedException {
        this.escenaPrincipal.setDisable(true);
        this.escenaPrincipal.setEffect(new GaussianBlur());
        Pane cargando = (Pane) this.loadFXML(ESCENA.CARGANDO);
        this.stackPane.getChildren().add(cargando);
        Thread.sleep(30);
        Platform.runLater(() -> {
            try {
                this.cargarEscena(escena);
                this.escenaPrincipal.setDisable(false);
                this.escenaPrincipal.setEffect(null);
                this.stackPane.getChildren().remove(cargando);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}

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
    private StackPane stackEscena;
    private Pane escenaPrincipal;

    public void setStackPane(StackPane stackPane) {
        this.stackPane = stackPane;
    }

    public void setStackEscena(StackPane stackEscena) {
        this.stackEscena = stackEscena;
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
        if (this.stackEscena.getChildren().size() != 1) 
            this.stackEscena.getChildren().remove(1);
        if (escena == ESCENA.INICIO)
            return;
        Pane escenaCargada = (Pane) this.loadFXML(escena);
        this.stackEscena.getChildren().add(escenaCargada);
    }

    public void ingresarUsuario() throws IOException {
        Pane navUsuario = (Pane) this.loadFXML(ESCENA.NAVUSUARIO);
        this.stackEscena.getChildren().remove(1);
        this.escenaPrincipal.getChildren().remove(0);
        this.escenaPrincipal.getChildren().add(0, navUsuario);
    }

    public void cerrarSesion() throws IOException {
        Pane navSinUsuario = (Pane) this.loadFXML(ESCENA.NAVSINUSUARIO);
        if (this.stackEscena.getChildren().size() != 1) 
            this.stackEscena.getChildren().remove(1);
        this.escenaPrincipal.getChildren().remove(0);
        this.escenaPrincipal.getChildren().add(0, navSinUsuario);
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

package cine.controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import cine.App;

public class Escenas {
    private Stage primaryStage;

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    public FXMLLoader getFXML(String fxml) {
        return new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    }

    public Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = this.getFXML(fxml);
        return fxmlLoader.load();
    }

    public void mostrarEscenaSig(String siguienteEscena) throws IOException{
        Parent root = loadFXML(siguienteEscena);
        Scene scena = new Scene(root);
        this.primaryStage.setScene(scena);
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

    public void mostrarMsjError(String mensaje) throws IOException{
        FXMLLoader fxmlLoader = this.getFXML("mensaje");
        Parent root = fxmlLoader.load();
        MensajeController mensajeCtrller = fxmlLoader.getController();
        mensajeCtrller.setMensaje(mensaje);
        Stage secundaryStage = Escenas.getStage(root, Modality.APPLICATION_MODAL, StageStyle.UNDECORATED);
        secundaryStage.setWidth(250);
        secundaryStage.setHeight(150);
        secundaryStage.setX(this.primaryStage.getX() + this.primaryStage.getWidth() / 2 - secundaryStage.getWidth() / 2);
        secundaryStage.setY(this.primaryStage.getY() + this.primaryStage.getHeight() / 2 - secundaryStage.getHeight() / 2);
        secundaryStage.showAndWait();
    }

    public void mensajeError(String mensaje, Pane contenedorActual) throws IOException {
        FXMLLoader fxmlLoader = this.getFXML("mensaje");
        Pane contenido = fxmlLoader.load();
        MensajeController mensajeController = fxmlLoader.getController();
        mensajeController.setMensaje(mensaje);
        mensajeController.setContenedorActual(contenedorActual);
        //Scene scena = new Scene(root);
        //this.primaryStage.setScene(scena);
        contenedorActual.getChildren().add(contenido);
    }

    public Stage armarPantallaCarga() throws IOException {
        Parent pantallaCarga = this.loadFXML("carga");
        Scene sceneCarga = new Scene(pantallaCarga);
        Stage secundaryStage = new Stage();
        secundaryStage.setWidth(250);
        secundaryStage.setHeight(75);
        secundaryStage.setX(this.primaryStage.getX() + this.primaryStage.getWidth() / 2 - secundaryStage.getWidth() / 2);
        secundaryStage.setY(this.primaryStage.getY() + this.primaryStage.getHeight() / 2 - secundaryStage.getHeight() / 2);
        secundaryStage.setResizable(false);
        secundaryStage.initModality(Modality.APPLICATION_MODAL);
        secundaryStage.initStyle(StageStyle.UNDECORATED);
        secundaryStage.setScene(sceneCarga);
        secundaryStage.show();
        return secundaryStage;
    }

    public void cargarSiguienteEscena(String siguienteEscena) throws IOException {
        Stage secundaryStage = this.armarPantallaCarga();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Parent cartelera;
                try {
                    cartelera = loadFXML(siguienteEscena);
                    Scene sceneCartelera = new Scene(cartelera);
                    primaryStage.setScene(sceneCartelera);
                    primaryStage.show();
                    secundaryStage.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
        });
    }

}

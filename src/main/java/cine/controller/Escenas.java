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

    public static Stage armarPantallaCarga(Stage primaryStage) throws IOException {
        Parent pantallaCarga = Escenas.loadFXML("carga");
        Scene sceneCarga = new Scene(pantallaCarga);
        Stage secundaryStage = new Stage();
        secundaryStage.setWidth(250);
        secundaryStage.setHeight(75);
        secundaryStage.setX(primaryStage.getX() + primaryStage.getWidth() / 2 - secundaryStage.getWidth() / 2);
        secundaryStage.setY(primaryStage.getY() + primaryStage.getHeight() / 2 - secundaryStage.getHeight() / 2);
        secundaryStage.setResizable(false);
        secundaryStage.initModality(Modality.APPLICATION_MODAL);
        secundaryStage.initStyle(StageStyle.UNDECORATED);
        secundaryStage.setScene(sceneCarga);
        secundaryStage.show();
        return secundaryStage;
    }

    public static void cargarSiguienteEscena(Pane escenaActual, String siguienteEscena) throws IOException {
        Stage primaryStage = (Stage)escenaActual.getScene().getWindow();
        Stage secundaryStage = armarPantallaCarga(primaryStage);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Parent cartelera;
                try {
                    cartelera = Escenas.loadFXML(siguienteEscena);
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

    public static void cargarUsuarioMenu(Pane escenaActual, String nombre) throws IOException {
        FXMLLoader fxmlLoader = Escenas.getFXML("usuarioMenu");
        Parent root = fxmlLoader.load();
        UsuarioMenuController usuarioMenuController = fxmlLoader.getController();
        usuarioMenuController.setUsuario(nombre);
        Escenas.mostrarEscenaSig(escenaActual, root);
    }

}

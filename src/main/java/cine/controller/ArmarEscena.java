package cine.controller;

import cine.view.Carga;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class ArmarEscena implements Runnable {

    private Carga carga = new Carga();
    private StackPane stackPane;

    public ArmarEscena(StackPane stackPane) {
       this.stackPane =  stackPane;
    }
     
    @Override
    public void run() {
        try{
            synchronized(stackPane) {
                Label cartel = this.carga.armar();
                stackPane.getChildren().add(cartel);
                if (stackPane.getChildren().size() == 1) stackPane.getChildren().add(cartel);
                System.out.println("El total de hijos es: " + stackPane.getChildren().size() + " en ArmarEscena");
                if (stackPane.getChildren().size() == 2) {
                    System.out.println("La cantidad de hijos es 2");
                    stackPane.notify();
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}

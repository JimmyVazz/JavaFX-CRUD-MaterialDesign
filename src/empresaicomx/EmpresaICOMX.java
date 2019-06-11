/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresaicomx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *Clase principal de la agenda
 * Donde se inicializa la ventana del login
 * @author jimmy
 */
public class EmpresaICOMX extends Application {
    
    /***
     * Inicializamos el login para que pase a la escena principal
     * @param stage variable donde se guarda el estado del scene
     * @throws Exception 
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLogin.fxml"));
       
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**Clase main
     * Lanzamos o cargamos el login
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        launch(args);
       
    }
    
}

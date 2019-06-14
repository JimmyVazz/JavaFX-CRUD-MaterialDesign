/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresaicomx;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jimmy
 */
public class FXMLReporteController implements Initializable {

    @FXML
    private JFXButton generarClientes;
    @FXML
    private JFXButton generarProductos;
    @FXML
    private JFXButton generarVentas;
    @FXML
    private JFXButton generarPedidos;
    @FXML
    private JFXButton generarReportes;
    @FXML
    private JFXButton regresar;
       
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /***
     * Navegacion entre pantallas
     * metodo para el boton abrirClientes
     * @param actionEvent
     * @throws IOException 
     */
    public void generarClientes(javafx.event.ActionEvent actionEvent) throws IOException{
        reportes.Reporte reporte = new reportes.Reporte("cliente");
        reporte.generarReporte();
    } 
    /***
     * Navegacion entre pantallas
     * metodo para el boton abrirProductos
     * @param actionEvent
     * @throws IOException 
     */
    public void generarProductos(javafx.event.ActionEvent actionEvent) throws IOException{
        reportes.Reporte reporte = new reportes.Reporte("producto");
        //Abrimos el reporte en pdf
        reporte.generarReporte();   
    } 
   
    /***
     * Regresar al home
     * actionEvent
     * @throws IOException 
     */
     public void regresar(javafx.event.ActionEvent actionEvent) throws IOException{
            regresar.getScene().getWindow().hide();
            Stage dashboardStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLHome.fxml"));
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
    }
    
    
}

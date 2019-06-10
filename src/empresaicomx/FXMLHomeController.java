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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jimmy
 */
public class FXMLHomeController implements Initializable {

    @FXML
    private JFXButton abrirClientes;
    @FXML
    private JFXButton abrirProductos;
    @FXML
    private JFXButton abrirVentas;
    @FXML
    private JFXButton abrirPedidos;
    @FXML
    private JFXButton abrirReportes;
       
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void abrirClientes(javafx.event.ActionEvent actionEvent) throws IOException{
            abrirClientes.getScene().getWindow().hide();
            Stage dashboardStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLCliente.fxml"));
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
    } 
    
    public void abrirProductos(javafx.event.ActionEvent actionEvent) throws IOException{
            abrirProductos.getScene().getWindow().hide();
            Stage dashboardStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLProducto.fxml"));
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
    } 
    
    public void abrirVentas(javafx.event.ActionEvent actionEvent) throws IOException{
            abrirVentas.getScene().getWindow().hide();
            Stage dashboardStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLVenta.fxml"));
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
    } 
    
    public void abrirPedidos(javafx.event.ActionEvent actionEvent) throws IOException{
            abrirPedidos.getScene().getWindow().hide();
            Stage dashboardStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLPedido.fxml"));
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
    } 
    
    public void abrirReportes(javafx.event.ActionEvent actionEvent) throws IOException{
            abrirReportes.getScene().getWindow().hide();
            Stage dashboardStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLReporte.fxml"));
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
    } 
    
}

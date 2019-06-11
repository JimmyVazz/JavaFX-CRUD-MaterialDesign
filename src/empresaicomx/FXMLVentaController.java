/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresaicomx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class de venta
 *
 * @author jimmy
 */
public class FXMLVentaController implements Initializable {
    
    /***
     * Encapsulamos los atributos de la vista
     * Por medio de variables los manipulamos
     */
    @FXML
    private JFXButton regresar;
    /***
     * Sentencia SQL por medio de objetos para realizar el query
     */
    Statement st;
    
    @FXML
    private JFXDatePicker txtFechaVenta;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    private JFXTextField txtIdCliente;
    @FXML
    private JFXTextField txtIdProducto;
    @FXML
    JFXButton btnGuardar;
    @FXML
    JFXButton btnEditar;
    @FXML
    JFXButton btnEliminar;
    
   
     /**
     * Initializes the controller class.
     * Verificamos los datos que se ingresen al textfield
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validador(txtIdCliente,"^[0-9]*$",10);
        validador(txtCantidad,"^[0-9]*$",10);
        validador(txtIdProducto,"^[0-9]*$",10);
        
        txtIdCliente.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("^[0-9]*$")|| newValue.length()>10) {//^= empieza *=0 o más numeros  $=terine con un número
                txtIdProducto.setText(oldValue);
                }
            }
        });
            
    }
    /**
     * Este metodo sirve para validar el contenido de cualquier textfiled.
     * @param caja La caja de texto sobre la cual se hace la validacion
     * @param regex La expresion regular que debe de cumplir el texto
     * @param max la longitud maxima del texto
     */
    private void validador(TextField caja, String regex, int max) {
  caja.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
      if(!newValue.matches(regex)|| newValue.length()>max) {
          caja.setText(oldValue);
      }
      
      }
      
  });
    }  
     /***
     * Metodo onAction para regresar
     * Navegacion entre pantallas
     * Home-Ventas
     * @param actionEvent evento del objeto 
     * @throws IOException excepcion de error
     */
    
    public void regresar(javafx.event.ActionEvent actionEvent) throws IOException{
            regresar.getScene().getWindow().hide();
            Stage dashboardStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLHome.fxml"));
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
    } 
    
        /***
     * Metodo insertar en la BD
     * st es la variable para hacer el query
     * Try
     * Catch
     * Para manejar las excepciones
     * metodo getConnection para obtener el estado de la conexion
     * @param venta
     */
    public void insert(Venta venta){
        try {
            st = databaseControl.DatabaseHandler.getConnection().createStatement();
            st.executeUpdate("insert into ventas (fecha_venta, cantidad, id_cliente, id_producto) values ('"+venta.getfecha_venta()+"', '"+venta.getCantidad()+"', '"+venta.getId_cliente()+"', '"+venta.getId_producto()+"')");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
        public void update(Venta venta){
        try {
            st = databaseControl.DatabaseHandler.getConnection().createStatement();
            st.executeUpdate("update ventas set fecha_venta = '"+venta.getfecha_venta()+"', cantidad = '"+venta.getCantidad()+"', id_cliente = '"+venta.getId_cliente()+"', id_producto = '"+venta.getId_producto()+"' where folio_venta = '"+venta.getfolio_venta()+"'");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     /***
     * Metodo actualizar en la BD
     * st es la variable para hacer el query
     * Try
     * Catch
     * Para manejar las excepciones
     * metodo getConnection para obtener el estado de la conexion
     * @param venta
     */
     public void delete(int id){
            try {
            st = databaseControl.DatabaseHandler.getConnection().createStatement();
            st.executeUpdate("delete from ventas where folio_venta = "+id+"");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLVentaController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
         /***
         * Acciones de los botones:
         * @Guardar void que inserta los datos en la BD
         * @Editar void que actualiza la BD
         * @Eliminar void que elimina el registro de la BD 
         */
     public void guardarDatos(javafx.event.ActionEvent actionEvent) throws IOException {
         FXMLVentaController ventaAgregada = new FXMLVentaController();
         Venta venta = new Venta();
         venta.setfecha_venta(txtFechaVenta.getValue());
         int cantidad = Integer.valueOf(txtCantidad.getText());
         int idCliente = Integer.valueOf(txtIdCliente.getText());
         int idProdcuto = Integer.valueOf(txtIdProducto.getText());
         venta.setCantidad(cantidad);
         System.out.println(venta);
         ventaAgregada.insert(venta);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Éxito");
         alert.setContentText("Venta, agregado de manera correcta");
          alert.show();
         txtFechaVenta.setValue(null);
         txtIdCliente.setText("");
         txtCantidad.setText("");
         txtIdProducto.setText("");
    }
    
}

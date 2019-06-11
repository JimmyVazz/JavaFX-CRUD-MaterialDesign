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
 * FXML Controller class del pedido
 * EL pedido es de acuerdo a los atributos del BD
 *
 * @author jimmy
 */
public class FXMLPedidoController implements Initializable {

    /***
     * Ensapsulamos los atributos y enlazamos con la vista
     * De acuerdo a las variables los manejamos
     */
    @FXML
    private JFXButton regresar;
    @FXML
    private JFXTextField txtFolioVenta;
    @FXML
    private JFXDatePicker txtFechaSalida;
    @FXML
    private JFXDatePicker txtFechaLlegada;
    @FXML
    private JFXTextField txtIdCliente;
    @FXML
    private JFXTextField txtIdProducto;
    @FXML
    private JFXTextField txtEstado;
    @FXML
    JFXButton btnGuardar;
    @FXML
    JFXButton btnEditar;
    @FXML
    JFXButton btnEliminar; 
    
    
    /***
     * Sentencia SQL para con el objeto de este tipo, mandar el query a la base de datos
     */
     Statement st;
 
     
         /**
     * Initializes the controller class.
     * Checamos los datos que se van ingresando en los textfield
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validador(txtFolioVenta,"^[0-9]*$",10);
        validador(txtEstado,"[A-Za-z ]*$",30);
        validador(txtIdCliente,"^[0-9]*$",10);
        validador(txtIdProducto,"^[0-9]*$",10);
        txtFolioVenta.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("^[0-9]*$")|| newValue.length()>10) {//^= empieza *=0 o más numeros  $=terine con un número
                txtFolioVenta.setText(oldValue);
                }
            }
        });
      txtEstado.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
              if(!newValue.matches("^[A-Za-z ]*$")) {//http://puntocomnoesunlenguaje.blogspot.com/2013/07/ejemplos-expresiones-regulares-java-split.html
                  txtEstado.setText(oldValue);
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
     * Home-Pedido
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
     * @param pedido
     */
    public void insert(Pedido pedido){
        try {
            st = databaseControl.DatabaseHandler.getConnection().createStatement();
            st.executeUpdate("insert into pedidos (folio_venta, fecha_salida, fecha_llegada, id_cliente, id_producto, estado) values ('"+pedido.getFolio_venta()+"', '"+pedido.getFecha_salida()+"', '"+pedido.getFecha_llegada()+"', '"+pedido.getId_cliente()+"', '"+pedido.getId_producto()+"', '"+pedido.getEstado()+"')");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
      /***
     * Metodo actualizar en la BD
     * st es la variable para hacer el query
     * Try
     * Catch
     * Para manejar las excepciones
     * metodo getConnection para obtener el estado de la conexion
     * @param pedido
     */ 
        public void update(Pedido pedido){
        try {
            st = databaseControl.DatabaseHandler.getConnection().createStatement();
            st.executeUpdate("update pedidos set folio_venta = '"+pedido.getFolio_venta()+"', fecha_salida = '"+pedido.getFecha_salida()+"', fecha_llegada = '"+pedido.getFecha_llegada()+"', id_cliente = '"+pedido.getId_cliente()+"', id_producto = '"+pedido.getId_producto()+"', estado = '"+pedido.getEstado()+"' where folio_pedido = '"+pedido.getFolio_pedido()+"'");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     /***
     * Metodo eliminar en la BD
     * st es la variable para hacer el query
     * Try
     * Catch
     * Para manejar las excepciones
     * metodo getConnection para obtener el estado de la conexion
     * @param pedido
     */ 
     public void delete(int id){
            try {
            st = databaseControl.DatabaseHandler.getConnection().createStatement();
            st.executeUpdate("delete from pedidos where folio_pedido = "+id+"");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLPedidoController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
          /***
         * Acciones de los botones:
         * @Guardar void que inserta los datos en la BD
         * @Editar void que actualiza la BD
         * @Eliminar void que elimina el registro de la BD 
         */
     
     public void guardarDatos(javafx.event.ActionEvent actionEvent) throws IOException {
         FXMLPedidoController pedidoAgregado = new FXMLPedidoController();
         Pedido pedido = new Pedido();
         int folio = Integer.valueOf(txtFolioVenta.getText());
         int idCliente = Integer.valueOf(txtIdCliente.getText());
         int idProdcuto = Integer.valueOf(txtIdProducto.getText());
         pedido.setFolio_venta(folio);
         pedido.setFecha_salida(txtFechaSalida.getValue());
         pedido.setFecha_llegada(txtFechaLlegada.getValue());
         pedido.setId_cliente(idCliente);
         pedido.setId_producto(idProdcuto);
         pedido.setEstado(txtEstado.getText());
         System.out.println(pedido);
         pedidoAgregado.insert(pedido);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Éxito");
         alert.setContentText("Pedido, enviado de manera correcta");
          alert.show();
         txtFolioVenta.setText("");
         txtFechaLlegada.setValue(null);
         txtFechaSalida.setValue(null);
         txtIdCliente.setText("");
         txtIdProducto.setText("");
         txtEstado.setText("");
     }
    
}

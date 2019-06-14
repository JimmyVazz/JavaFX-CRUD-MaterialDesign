/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresaicomx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class del cliente
 * Con esta clase manejamos los eventos de la vusta FXML
 *
 * @author jimmy
 */
public class FXMLClienteController implements Initializable {
    
    /***
     * Ensapsulamos los atributos de las variables de la vista
     * Ya despues por medio de variables los enlazamos
     */
    @FXML
    private JFXButton regresar;
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtApepat;
    @FXML
    private JFXTextField txtApemat;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtDireccion;
    @FXML
    private JFXTextField txtTelefono;
    @FXML
    JFXButton btnGuardar;
    @FXML
    JFXButton btnEditar;
    @FXML
    JFXButton btnEliminar; 
    @FXML
    TableView<Cliente> tabla;
    @FXML
    private JFXTextField txtBuscar;
    @FXML
    private JFXButton btnBuscar;
    
    int tempoID;
    
    /***
     * Tabla personalizada para el cliente
     */
   TableColumn<Cliente, Integer> columnId = new TableColumn<>("ID");        
   TableColumn<Cliente, String> columnNombre = new TableColumn<>("Nombre");
   TableColumn<Cliente, String> columnApepat = new TableColumn<>("Apellido Paterno");
   TableColumn<Cliente, String> columnApemat = new TableColumn<>("Apellido Materno");
   TableColumn<Cliente, String> columnEmail = new TableColumn<>("Email");
   TableColumn<Cliente, String> columnTelefono = new TableColumn<>("Telefono");
   TableColumn<Cliente, String> columnDireccion = new TableColumn<>("Direccion");

   
   /*public void leerTabla (){
   

   columnId.setCellValueFactory(new PropertyValueFactory<>("ID"));
   columnNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
   columnApepat.setCellValueFactory(new PropertyValueFactory<>("Apellido Paterno"));
   columnApemat.setCellValueFactory(new PropertyValueFactory<>("Apellido Materno"));
   columnEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
   columnTelefono.setCellValueFactory(new PropertyValueFactory<>("Teléfono"));
   columnDireccion.setCellValueFactory(new PropertyValueFactory<>("Dirección"));
   
   tabla.getColumns().addAll(columnId, columnNombre, columnApepat, columnApemat, columnEmail, columnTelefono, columnDireccion);
    
    
   }*/
   
    /***
     * Variable de tipo Statemnet para hacer sentencias SQL
     */
    Statement st;
    
    /**
     * Initializes the controller class.
     * Prohibimos meter caracteres no permitidos
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validador(txtTelefono,"^[0-9]*$",10);
        validador(txtNombre,"[A-Za-z ]*$",30);
        validador(txtApepat,"[A-Za-z ]*$",30);
        validador(txtApemat,"[A-Za-z ]*$",30);
        txtTelefono.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("^[0-9]*$")|| newValue.length()>10) {//^= empieza *=0 o más numeros  $=terine con un número
                txtTelefono.setText(oldValue);
                }
            }
        });
      txtNombre.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
              if(!newValue.matches("^[A-Za-z ]*$")) {//http://puntocomnoesunlenguaje.blogspot.com/2013/07/ejemplos-expresiones-regulares-java-split.html
                  txtNombre.setText(oldValue);
              }
                
            }
          
      });  
    /***
     * Inicializamos las columnas de la tabla con las de la BD y leemos los datos
     */
   columnId.setCellValueFactory(new PropertyValueFactory<>("Id_cliente"));
   columnNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
   columnApepat.setCellValueFactory(new PropertyValueFactory<>("Apepat"));
   columnApemat.setCellValueFactory(new PropertyValueFactory<>("Apemat"));
   columnEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
   columnTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
   columnDireccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
   
   //Alineamos las collumnas
   columnId.prefWidthProperty().bind(tabla.widthProperty().divide(7));
   columnNombre.prefWidthProperty().bind(tabla.widthProperty().divide(7));
   columnApepat.prefWidthProperty().bind(tabla.widthProperty().divide(7));
   columnApemat.prefWidthProperty().bind(tabla.widthProperty().divide(7));
   columnEmail.prefWidthProperty().bind(tabla.widthProperty().divide(7));
   columnTelefono.prefWidthProperty().bind(tabla.widthProperty().divide(7));
   columnDireccion.prefWidthProperty().bind(tabla.widthProperty().divide(7));
   
   //Cargamos la lista de clientes
   FXMLClienteController clienteAgregado = new FXMLClienteController();
   tabla.getColumns().addAll(columnId, columnNombre, columnApepat, columnApemat, columnEmail, columnTelefono, columnDireccion);
    tabla.setItems(clienteAgregado.getAllClientes());
    
    //Hacemos la tabla editable
    tabla.setOnMouseClicked(new EventHandler<MouseEvent>(){
 
          @Override
          public void handle(MouseEvent arg0) {
             
             Cliente cliente = tabla.getSelectionModel().getSelectedItem();
                txtNombre.setText(cliente.getNombre());
                txtApepat.setText(cliente.getApepat());
                txtApemat.setText(cliente.getApemat());
                txtEmail.setText(cliente.getEmail());
                txtTelefono.setText(cliente.getTelefono());
                txtDireccion.setText(cliente.getDireccion());
                //Si fuera int le agreariamos cliente.getDireccion()+""
                tempoID =cliente.getId_cliente();
                
                
          }
 
      });
   
    
    
    
    }
    
    
    /**
     * Este metodo sirve parra validar el contenido de cualquier textfield.
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
     * Home-Clientes
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
     * @param cliente 
     */
    public void insert(Cliente cliente){
        
        try {
            st = databaseControl.DatabaseHandler.getConnection().createStatement();
            st.executeUpdate("insert into clientes (nombre, apepat, apemat, email, telefono, direccion) values ('"+cliente.getNombre()+"', '"+cliente.getApepat()+"', '"+cliente.getApemat()+"', '"+cliente.getEmail()+"', '"+cliente.getTelefono()+"', '"+cliente.getDireccion()+"')");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     /***
     * Metodo actualizar en la BD
     * st es la variable para hacer el query
     * Try
     * Catch
     * Para manejar las excepciones
     * metodo getConnection para obtener el estado de la conexion
     * @param cliente 
     */ 
        public void update(Cliente cliente){
        try {
            st = databaseControl.DatabaseHandler.getConnection().createStatement();
            st.executeUpdate("update clientes set nombre = '"+cliente.getNombre()+"', apepat = '"+cliente.getApepat()+"', apemat = '"+cliente.getApemat()+"', email = '"+cliente.getEmail()+"', telefono = '"+cliente.getTelefono()+"', direccion = '"+cliente.getDireccion()+"' where id_cliente = '"+cliente.getId_cliente()+"'");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     /***
     * Metodo eliminar en la BD
     * st es la variable para hacer el query
     * Try
     * Catch
     * Para manejar las excepciones
     * metodo getConnection para obtener el estado de la conexion
     * @param cliente 
     */   
     public void delete(int id){
         
            try {
            st = databaseControl.DatabaseHandler.getConnection().createStatement();
            st.executeUpdate("delete from clientes where id_cliente = "+id+"");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     /***
      * Lista observable para ingresar los clientes de la BD
      * @return 
      */
     public ObservableList<Cliente> getAllClientes(){
         ObservableList <Cliente> clientes = FXCollections.observableArrayList();
        try {
            st = databaseControl.DatabaseHandler.getConnection().createStatement();
            ResultSet resulSet = st.executeQuery("select * from clientes");
            resulSet.beforeFirst();
            
            while(resulSet.next() ){
               Cliente cliente = new Cliente();
               cliente.setId_cliente(resulSet.getInt(1));
               cliente.setNombre(resulSet.getString(2));
               cliente.setApepat(resulSet.getString(3));
               cliente.setApemat(resulSet.getString(4));
               cliente.setEmail(resulSet.getString(5));
               cliente.setTelefono(resulSet.getString(6));
               cliente.setDireccion(resulSet.getString(7));
               clientes.add(cliente);
                System.out.println(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
        
        
     }
     
      
     /***
      * Lista observable para buscar los clientes en la  BD
      * @return 
      */
     public ObservableList<Cliente> search(String name){
         ObservableList <Cliente> clientes = FXCollections.observableArrayList();
        try {
            st = databaseControl.DatabaseHandler.getConnection().createStatement();
            ResultSet resulSet = st.executeQuery("select * from clientes where nombre like '%"+name+"%'  ");
            resulSet.beforeFirst();
            
            while(resulSet.next() ){
               Cliente cliente = new Cliente();
               cliente.setId_cliente(resulSet.getInt(1));
               cliente.setNombre(resulSet.getString(2));
               cliente.setApepat(resulSet.getString(3));
               cliente.setApemat(resulSet.getString(4));
               cliente.setEmail(resulSet.getString(5));
               cliente.setTelefono(resulSet.getString(6));
               cliente.setDireccion(resulSet.getString(7));
               clientes.add(cliente);
                System.out.println(cliente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
        
        
     }
     
       /***
         * Acciones de los botones:
         * @Guardar void que inserta los datos en la BD
         * @Editar void que actualiza la BD
         * @Eliminar void que elimina el registro de la BD 
         */
     public void guardarDatos(javafx.event.ActionEvent actionEvent) throws IOException {
         FXMLClienteController clienteAgregado = new FXMLClienteController();
         Cliente cliente = new Cliente();
         cliente.setNombre(txtNombre.getText());
         cliente.setApepat(txtApepat.getText());
         cliente.setApemat(txtApemat.getText());
         cliente.setEmail(txtEmail.getText());
         cliente.setTelefono(txtTelefono.getText());
         cliente.setDireccion(txtDireccion.getText());
         System.out.println(cliente);
         clienteAgregado.insert(cliente);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Éxito");
         alert.setContentText("Cliente, agregado de manera correcta");
          alert.show();
         txtNombre.setText("");
         txtApepat.setText("");
         txtApemat.setText("");
         txtEmail.setText("");
         txtTelefono.setText("");
         txtDireccion.setText("");
         tabla.setItems(clienteAgregado.getAllClientes());
   /*columnId.setCellValueFactory(new PropertyValueFactory<>("Id_cliente"));
   columnNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
   columnApepat.setCellValueFactory(new PropertyValueFactory<>("Apepat"));
   columnApemat.setCellValueFactory(new PropertyValueFactory<>("Apemat"));
   columnEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
   columnTelefono.setCellValueFactory(new PropertyValueFactory<>("Telefono"));
   columnDireccion.setCellValueFactory(new PropertyValueFactory<>("Direccion"));
   
   tabla.getColumns().addAll(columnId, columnNombre, columnApepat, columnApemat, columnEmail, columnTelefono, columnDireccion);
*/
     }
     
      public void actualizarDatos(javafx.event.ActionEvent actionEvent) throws IOException {
        
         FXMLClienteController clienteActualizado = new FXMLClienteController();
         Cliente cliente = new Cliente();
         cliente.setId_cliente(tempoID);
         cliente.setNombre(txtNombre.getText());
         cliente.setApepat(txtApepat.getText());
         cliente.setApemat(txtApemat.getText());
         cliente.setEmail(txtEmail.getText());
         cliente.setTelefono(txtTelefono.getText());
         cliente.setDireccion(txtDireccion.getText());
         System.out.println(cliente);
         clienteActualizado.update(cliente);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Éxito");
         alert.setContentText("Cliente, actualizado de manera correcta");
          alert.show();
         txtNombre.setText("");
         txtApepat.setText("");
         txtApemat.setText("");
         txtEmail.setText("");
         txtTelefono.setText("");
         txtDireccion.setText("");
         tabla.setItems(clienteActualizado.getAllClientes());
          
          
          
     } 
      
     public void eliminarDatos(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLClienteController clienteEliminado = new FXMLClienteController();
        clienteEliminado.delete(tempoID);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Éxito");
         alert.setContentText("Cliente, eliminado de manera correcta");
          alert.show();
        tabla.setItems(clienteEliminado.getAllClientes());
     } 
     
       public void buscarCliente(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLClienteController clienteBuscado = new FXMLClienteController();
        String name = txtBuscar.getText();
        tabla.setItems(clienteBuscado.search(name));
     } 
     
     
     



}

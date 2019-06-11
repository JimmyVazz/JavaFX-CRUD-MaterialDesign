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
 * FXML Controller class del producto
 * 
 *
 * @author jimmy
 */
public class FXMLProductoController implements Initializable {

    /***
     * Encaṕsulamos los atributos del view
     * Por medio de variables los enlazamos para su manipulacion
     */
    @FXML
    private JFXButton regresar;
  
    /****
     * Sentencia SQL para crear un objeto y por medio de este  realizar el query
     */
    Statement st; 
    
    @FXML
    private JFXTextField txtNombre;
    @FXML
    private JFXTextField txtPrecio;
    @FXML
    private JFXTextField txtCantidad;
    @FXML
    JFXButton btnGuardar;
    @FXML
    JFXButton btnEditar;
    @FXML
    JFXButton btnEliminar;
    @FXML
    TableView<Producto> tabla;
    @FXML
    JFXTextField txtBuscar;
    
    int tempoID;
     /***
     * Tabla personalizada para el cliente
     */
   TableColumn<Producto, Integer> columnId = new TableColumn<>("ID");        
   TableColumn<Producto, String> columnNombre = new TableColumn<>("Nombre");
   TableColumn<Producto, Float> columnPrecio = new TableColumn<>("Precio");
   TableColumn<Producto, Integer> columnCantidad = new TableColumn<>("Cantidad");
   TableColumn<Producto, Integer> columnDisponibilidad = new TableColumn<>("Stock");
    
    /**
     * Initializes the controller class.
     * Checamos los datos que se ingresen en el textField
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        validador(txtCantidad,"^[0-9]*$",10);
        validador(txtNombre,"[A-Za-z ]*$",30);

        txtCantidad.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.matches("^[0-9]*$")|| newValue.length()>10) {//^= empieza *=0 o más numeros  $=terine con un número
                txtCantidad.setText(oldValue);
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
    * Inicializamos la tabla
    */
   columnId.setCellValueFactory(new PropertyValueFactory<>("id_producto"));
   columnNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
   columnPrecio.setCellValueFactory(new PropertyValueFactory<>("Precio"));
   columnCantidad.setCellValueFactory(new PropertyValueFactory<>("Cantidad"));
   columnDisponibilidad.setCellValueFactory(new PropertyValueFactory<>("Disponibilidad"));
      //Alineamos las collumnas
   columnId.prefWidthProperty().bind(tabla.widthProperty().divide(5));
   columnNombre.prefWidthProperty().bind(tabla.widthProperty().divide(5));
   columnPrecio.prefWidthProperty().bind(tabla.widthProperty().divide(5));
   columnCantidad.prefWidthProperty().bind(tabla.widthProperty().divide(5));
   columnDisponibilidad.prefWidthProperty().bind(tabla.widthProperty().divide(5));
   //Cargamos los productos
   FXMLProductoController productoAgregado = new FXMLProductoController();
   tabla.getColumns().addAll(columnId, columnNombre, columnPrecio, columnCantidad, columnDisponibilidad);
    tabla.setItems(productoAgregado.getAllProductos());
    
       //Hacemos la tabla editable
    tabla.setOnMouseClicked(new EventHandler<MouseEvent>(){
 
          @Override
          public void handle(MouseEvent arg0) {
             
             Producto producto = tabla.getSelectionModel().getSelectedItem();
                txtNombre.setText(producto.getNombre());
                txtPrecio.setText(producto.getPrecio()+"");
                txtCantidad.setText(producto.getCantidad()+"");
               //Si fuera int le agreariamos cliente.getDireccion()+""
                tempoID =producto.getId_producto();
                
                
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
     * @param producto
     */
    public void insert(Producto producto){
        try {
            st = databaseControl.DatabaseHandler.getConnection().createStatement();
            st.executeUpdate("insert into productos (nombre, precio, cantidad, disponibilidad) values ('"+producto.getNombre()+"', '"+producto.getPrecio()+"', '"+producto.getCantidad()+"', '"+producto.getDisponibilidad()+"' )");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
         /***
     * Metodo actualizar en la BD
     * st es la variable para hacer el query
     * Try
     * Catch
     * Para manejar las excepciones
     * metodo getConnection para obtener el estado de la conexion
     * @param producto 
     */ 
        public void update(Producto producto){
        try {
            st = databaseControl.DatabaseHandler.getConnection().createStatement();
            st.executeUpdate("update productos set nombre = '"+producto.getNombre()+"', precio = '"+producto.getPrecio()+"', cantidad = '"+producto.getCantidad()+"', disponibilidad = '"+producto.getDisponibilidad()+"' where id_producto = '"+producto.getId_producto()+"'");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
      /***
     * Metodo eliminar en la BD
     * st es la variable para hacer el query
     * Try
     * Catch
     * Para manejar las excepciones
     * metodo getConnection para obtener el estado de la conexion
     * @param producto
     */  
     public void delete(int id){
            try {
            st = databaseControl.DatabaseHandler.getConnection().createStatement();
            st.executeUpdate("delete from productos where id_producto = "+id+"");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLProductoController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
      /***
      * Lista observable para ingresar los clientes de la BD
      * @return 
      */
     public ObservableList<Producto> getAllProductos(){
         ObservableList <Producto> productos = FXCollections.observableArrayList();
        try {
            st = databaseControl.DatabaseHandler.getConnection().createStatement();
            ResultSet resulSet = st.executeQuery("select * from productos");
            resulSet.beforeFirst();
            
            while(resulSet.next() ){
               Producto producto = new Producto();
               producto.setId_producto(resulSet.getInt(1));
               producto.setNombre(resulSet.getString(2));
               producto.setPrecio((float) resulSet.getDouble(3));
               producto.setCantidad(resulSet.getInt(4));
               producto.setDisponibilidad(resulSet.getInt(5));
               productos.add(producto);
                System.out.println(producto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
        
        
     }
         /***
      * Lista observable para ingresar los clientes de la BD
      * @return 
      */
     public ObservableList<Producto> search(String name){
         ObservableList <Producto> productos = FXCollections.observableArrayList();
        try {
            st = databaseControl.DatabaseHandler.getConnection().createStatement();
            ResultSet resulSet = st.executeQuery("select * from productos where nombre = '"+name+"'  ");
            resulSet.beforeFirst();
            
            while(resulSet.next() ){
               Producto producto = new Producto();
               producto.setId_producto(resulSet.getInt(1));
               producto.setNombre(resulSet.getString(2));
               producto.setPrecio((float) resulSet.getDouble(3));
               producto.setCantidad(resulSet.getInt(4));
               producto.setDisponibilidad(resulSet.getInt(5));
               productos.add(producto);
                System.out.println(producto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productos;
        
        
     }
     
     
     
     
     
     
           /***
         * Acciones de los botones:
         * @Guardar void que inserta los datos en la BD
         * @Editar void que actualiza la BD
         * @Eliminar void que elimina el registro de la BD 
         */ 
     public void guardarDatos(javafx.event.ActionEvent actionEvent) throws IOException {
         int disponible = 1;
         FXMLProductoController productoAgregado = new FXMLProductoController();
         Producto producto = new Producto();
         producto.setNombre(txtNombre.getText());
         int cantidad = Integer.valueOf(txtCantidad.getText());
         float precio = Float.valueOf(txtPrecio.getText());
         producto.setPrecio(precio);
         producto.setCantidad(cantidad);
         producto.setDisponibilidad(disponible);
         System.out.println(producto);
         productoAgregado.insert(producto);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Éxito");
         alert.setContentText("Producto, agregado de manera correcta");
          alert.show();
         txtNombre.setText("");
         txtPrecio.setText("");
         txtCantidad.setText("");
         tabla.setItems(productoAgregado.getAllProductos());
         
         
     }
      
      public void actualizarDatos(javafx.event.ActionEvent actionEvent) throws IOException {
        
         int disponible = 1;
         FXMLProductoController productoActualizado = new FXMLProductoController();
         Producto producto = new Producto();
         producto.setId_producto(tempoID);
         producto.setNombre(txtNombre.getText());
         int cantidad = Integer.valueOf(txtCantidad.getText());
         float precio = Float.valueOf(txtPrecio.getText());
         producto.setPrecio(precio);
         producto.setCantidad(cantidad);
         producto.setDisponibilidad(disponible);
         System.out.println(producto);
         productoActualizado.update(producto);
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle("Éxito");
         alert.setContentText("Producto, actualizado de manera correcta");
          alert.show();
         txtNombre.setText("");
         txtPrecio.setText("");
         txtCantidad.setText("");
         tabla.setItems(productoActualizado.getAllProductos());
              
     } 
      
     public void eliminarDatos(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLProductoController productoEliminado = new FXMLProductoController();
        productoEliminado.delete(tempoID);
        tabla.setItems(productoEliminado.getAllProductos());
     } 
     
       public void buscarCliente(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLProductoController productoBuscado= new FXMLProductoController();
        String name = txtBuscar.getText();
        tabla.setItems(productoBuscado.search(name));
     } 
     

}

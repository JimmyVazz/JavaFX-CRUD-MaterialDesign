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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jimmy
 */
public class FXMLClienteController implements Initializable {

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
    
   TableColumn<Cliente, Integer> columnId = new TableColumn<>("ID");
   columnId.setCellValueFactory(new PropertyValueFactory<>("ID"));
   TableColumn<Cliente, String> columnNombre = new TableColumn<>("Nombre");
   TableColumn<Cliente, String> columnApepat = new TableColumn<>("Apellido Paterno");
   TableColumn<Cliente, String> columnApemat = new TableColumn<>("Apellido Materno");
   TableColumn<Cliente, String> columnEmail = new TableColumn<>("Email");
   TableColumn<Cliente, String> columnTelefono = new TableColumn<>("Telefono");
   TableColumn<Cliente, String> columnDireccion = new TableColumn<>("Direccion");
   
   
    
    Statement st;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public void regresar(javafx.event.ActionEvent actionEvent) throws IOException{
            regresar.getScene().getWindow().hide();
            Stage dashboardStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLHome.fxml"));
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
    }
    
    public void insert(Cliente cliente){
        try {
            st = databaseControl.DatabaseHandler.getConnection().createStatement();
            st.executeUpdate("insert into clientes (nombre, apepat, apemat, email, telefono, direccion) values ('"+cliente.getNombre()+"', '"+cliente.getApepat()+"', '"+cliente.getApemat()+"', '"+cliente.getEmail()+"', '"+cliente.getTelefono()+"', '"+cliente.getDireccion()+"')");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
        public void update(Cliente cliente){
        try {
            st = databaseControl.DatabaseHandler.getConnection().createStatement();
            st.executeUpdate("update clientes set nombre = '"+cliente.getNombre()+"', apepat = '"+cliente.getApepat()+"', apemat = '"+cliente.getApemat()+"', email = '"+cliente.getEmail()+"', telefono = '"+cliente.getTelefono()+"', direccion = '"+cliente.getDireccion()+"' where id_cliente = '"+cliente.getId_cliente()+"'");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        
     public void delete(int id){
            try {
            st = databaseControl.DatabaseHandler.getConnection().createStatement();
            st.executeUpdate("delete from cliente where id = "+id+"");
        } catch (SQLException ex) {
            Logger.getLogger(FXMLClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
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
         * Acciones de los botones, 
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
         txtNombre.setText("");
         txtApepat.setText("");
         txtApemat.setText("");
         txtEmail.setText("");
         txtTelefono.setText("");
         txtDireccion.setText("");
     }




}

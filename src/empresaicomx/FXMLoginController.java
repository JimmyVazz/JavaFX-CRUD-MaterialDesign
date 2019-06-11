/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresaicomx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import databaseControl.DatabaseHandler;
import static databaseControl.DatabaseHandler.CheckLoginUser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Controlador del login
 * @author jimmy
 */
public class FXMLoginController implements Initializable {
    
    /***
     * Encapsulamos los atributos de la vista 
     * De esta manera con las variables los manipulamos
     */
    @FXML
    private JFXTextField txtUsuario;
    
    @FXML
    private JFXPasswordField txtPassword;
    
    @FXML
    private JFXButton txtEntrar;
    
     /**
     * Initializes the controller class.
     * Verificamos los datos que se ingresen al textfield
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    /***
     * Con este metodo realizamos la accion de hacer el login 
     * Con los datos verificamos si son correctos
     * para abrir la pagina principal
     * Sino, lanzamos una alerta de error
     * @param actionEvent
     * @throws IOException 
     */
    public void setLoginButton(javafx.event.ActionEvent actionEvent) throws IOException {
        String uname = txtUsuario.getText();
        String pass = txtPassword.getText();
        System.out.println(uname);
        System.out.println(pass);
        if (DatabaseHandler.CheckLoginUser(uname, pass)) { // sending data to databasehandler class to connection data
            txtEntrar.getScene().getWindow().hide();
            Stage dashboardStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLHome.fxml"));
            Scene scene = new Scene(root);
            dashboardStage.setScene(scene);
            dashboardStage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setContentText("Usuario no válido");
            alert.show();
//            reseting user and pass field
            txtUsuario.setText("");
            txtPassword.setText("");
        }
    }
   
   
    /***
     * Setters and getters
     * @return 
     */
    public JFXTextField getTxtUsuario() {
        return txtUsuario;
    }

    public void setTxtUsuario(JFXTextField txtUsuario) {
        this.txtUsuario = txtUsuario;
    }

    public JFXPasswordField getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(JFXPasswordField txtPassword) {
        this.txtPassword = txtPassword;
    }

    public JFXButton getTxtEntrar() {
        return txtEntrar;
    }

    public void setTxtEntrar(JFXButton txtEntrar) {
        this.txtEntrar = txtEntrar;
    }
    
}

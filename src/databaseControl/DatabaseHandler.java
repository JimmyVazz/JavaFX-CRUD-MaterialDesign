/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseControl;
import java.sql.*;
import java.sql.Connection;          //Aqui obtenemos el metodo conectar
import java.sql.DriverManager;       //Aqui obtenemos el manejo del driver de java a mysql
import java.sql.PreparedStatement;   //Aqui obtenemos una sintaxis facil de crear sentencias sql
import java.sql.SQLException;        //Aqui obtenemos los metodo para manejo de excepciones
/**
 *
 * @author jimmy
 */
public class DatabaseHandler {
   

    public static Connection GetDatabaseConnection() {
        Connection connection = null;
//    Connection connection;

     String dbUrl = "jdbc:mysql://localhost/empresaICOMX";
        String user = "root";
       String pass = "";
        try {
//            driver setup for database
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, user, pass);

//            System.out.println("connection successful");

        } catch (ClassNotFoundException e) {
            e.getLocalizedMessage();
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            e.getLocalizedMessage();
        }

        return connection;
    }
    
    
    public static boolean CheckLoginUser(String uname, String pass) { //get input from login system module
        Connection connection = GetDatabaseConnection();
        String checkQuery = "select * from login where usuario = ? and password = ? "; // i don't use id from database table.
        
        PreparedStatement preparedStatement = null;
        boolean status = false; //initially false

        try {
            preparedStatement = connection.prepareStatement(checkQuery);
            preparedStatement.setString(1, uname); // dynamically sending username
            preparedStatement.setString(2, pass); // sending password to checkquery statement
            ResultSet resultSet = preparedStatement.executeQuery();

            status = resultSet.next();
            preparedStatement.close();
            return status;

        } catch (SQLException e) {
//            e.getLocalizedMessage();
            e.printStackTrace();
        }
        return status;
    }
}


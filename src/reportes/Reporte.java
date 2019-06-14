/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import java.sql.Connection;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;

/**Clase de reporte con la cual generamos reportes en base a los viewer ya predeterminados con un query
 *
 * @author jimmy
 */

/***
 * 
 * 
 */
public class Reporte {
    private String reporte;
    
    /***
     * Constructor con un string, con el nombre del archivo del reporte a generar
     * @param reporte 
     */
    public Reporte(String reporte){
        this.reporte = reporte;
    }
    
    /***
     * Metodo para generar reporte
     * Se genera una conexion mysql, despues de cargan los parametros, se compila y se muestra el PDF
     */
    public void generarReporte(){
        databaseControl.DatabaseHandler conexionMySQL = new databaseControl.DatabaseHandler();
        Connection conn = conexionMySQL.getConnection();
        try {
            java.io.InputStream dir = getClass().getResourceAsStream("/reportes/"+
                    this.reporte+".jrxml");
            JasperReport jr = JasperCompileManager.compileReport(dir);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, conn);
            
            JRViewer test = new JRViewer(jp);
            JFrame frame = new JFrame("Reporte");
            frame.getContentPane().add(test);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.pack();
            frame.setVisible(true);
        } catch (Exception e) {
            System.out.println("Error generando reporte");
            System.out.println(e);
        }
        
    }
    
    
}

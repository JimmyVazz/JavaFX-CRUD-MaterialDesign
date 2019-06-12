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

/**
 *
 * @author jimmy
 */
public class Reporte {
    private String reporte;
    
    public Reporte(String reporte){
        this.reporte = reporte;
    }
    
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresaicomx;

import java.time.LocalDate;

/**
 *Clase venta 
 * @author jimmy
 */
public class Venta {
    /***
     * Encapsulamos los datos 
     * 
     */
    private int folio_venta;
    private LocalDate fecha_venta;
    private int cantidad;
    private int id_cliente;
    private int id_producto;
    
    /***
     * Setters and getters
     * @return 
     */
    public int getfolio_venta() {
        return folio_venta;
    }

    public void setfolio_venta(int folio_venta) {
        this.folio_venta = folio_venta;
    }

    public LocalDate getfecha_venta() {
        return fecha_venta;
    }

    public void setfecha_venta(LocalDate fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }
    
    /***
     * Sobreescribimos el metodo to String 
     * Retornamos un string con todos los datos objeto
     * @return 
     */
    @Override
    public String toString() {
        return "Venta{" + "folio_venta=" + folio_venta + ", fecha_venta=" + fecha_venta + ", cantidad=" + cantidad + ", id_cliente=" + id_cliente + ", id_producto=" + id_producto + '}';
    }
    
    
    
}

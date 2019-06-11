/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresaicomx;

import java.time.LocalDate;

/** Clase Pedido
 *  De acuerdo con la BD
 *
 * @author jimmy
 */
public class Pedido {

    public int getFolio_pedido() {
        return folio_pedido;
    }

    public void setFolio_pedido(int folio_pedido) {
        this.folio_pedido = folio_pedido;
    }

    public int getFolio_venta() {
        return folio_venta;
    }

    public void setFolio_venta(int folio_venta) {
        this.folio_venta = folio_venta;
    }

    public LocalDate getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(LocalDate fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public LocalDate getFecha_llegada() {
        return fecha_llegada;
    }

    public void setFecha_llegada(LocalDate fecha_llegada) {
        this.fecha_llegada = fecha_llegada;
    }

    public int getId_cliente() {
        return id_cliente;
    }

     /***
      * Sobreescribimos el metodo ToString
      * @return un string con todos los atributos del objeto 
      */
    @Override
    public String toString() {
        return "Pedido{" + "folio_pedido=" + folio_pedido + ", folio_venta=" + folio_venta + ", fecha_salida=" + fecha_salida + ", fecha_llegada=" + fecha_llegada + ", id_cliente=" + id_cliente + ", id_producto=" + id_producto + ", estado=" + estado + '}';
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    /***
     * Atributos encapsulados de la clase pedido
     */
    private int folio_pedido;
    private int folio_venta;
    private LocalDate fecha_salida;
    private LocalDate fecha_llegada;
    private int id_cliente;
    private int id_producto;
    private String estado;
    
}

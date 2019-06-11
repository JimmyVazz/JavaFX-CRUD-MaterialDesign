/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresaicomx;

/**
 *Clase del Producto
 * @author jimmy
 */
public class Producto {
    /***
     * De acuerdo con la BD
     * Encapsulamos los atributos
     */
    private int id_producto;
    private String nombre;
    private int cantidad;
    private float precio;

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    private int disponibilidad;
    
    /***
     * Setters and getters
     * @return 
     */
    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    /***
     * Sobreescribimos el metodo ToString
     * 
     * @return retorna un string con todos los datos del objeto
     */
    @Override
    public String toString() {
        return "Producto{" + "id_producto=" + id_producto + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio=" + precio + ", disponibilidad="+disponibilidad + '}';
    }

 


    
    
}

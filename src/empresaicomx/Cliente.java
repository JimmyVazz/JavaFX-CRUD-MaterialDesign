/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresaicomx;

/**
 *Clase cliente 
 * @author jimmy
 */
public class Cliente {
    
    /***
     * Atributos encapsulados del cliente, para prohibir acceder de forma directa y modificarlos
     * Todo esto de acuerdo a la BD
     */
    private int id_cliente;
    private String nombre;
    private String apepat;
    private String apemat;
    private String direccion;
    private String telefono;
    private String email;
    
    /***
     * Setters and Getters
     * De esta forma podemos meter datos en los atributos y obtenerlos para manipularlos
     * de direferentes formas
     * @return 
     */
    
    /***
     * Sobrecarga de constructores
     * Uno recibe un string nombre y el otro recibe un string nombre y apepat
     * @param nombre 
     */
    public Cliente(String nombre){
       System.out.println(nombre);
    }
    public Cliente(String nombre, String apepat){
        System.out.println(nombre+apepat);
    }
    public Cliente(){
        
    }
    
    public int getId_cliente() {           
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
        
        this.nombre = nombre;
    }

    public String getApepat() {
        return apepat;
    }

    public void setApepat(String apepat) {
        this.apepat = apepat;
    }

    public String getApemat() {
        return apemat;
    }

    public void setApemat(String apemat) {
        this.apemat = apemat;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    /***
     * Sobreescribimos el metodo toString
     * Para obtenerlos de manera corrida
     *  
     */
    @Override
    public String toString() {
        return "Cliente{" + "id_cliente=" + id_cliente + ", nombre=" + nombre + ", apepat=" + apepat + ", apemat=" + apemat + ", direccion=" + direccion + ", telefono=" + telefono + ", email=" + email + '}';
    }

    private String toUpperCase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

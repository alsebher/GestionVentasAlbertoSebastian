/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author Alumno Mañana
 */
public class Producto {
    //atributos, variables estáticas y constantes
    private int idProducto;
    private String nombre;
    private double precio;
    static int contadorProductos;

    //getter & setter
    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    //constructores
    public Producto() {
        contadorProductos++;
    }

    public Producto(String nombre, double precio) {
        this();
        this.nombre = nombre;
        this.precio = precio;
        this.idProducto = contadorProductos;
    }

    //toString
    @Override
    public String toString() {
        return "ID PRODUCTO: " + idProducto + "  |  NOMBRE: " + nombre + "  |  PRECIO: " + precio+" €";
    }
    
    
    
    
    
}

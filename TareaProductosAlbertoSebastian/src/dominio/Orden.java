/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.ArrayList;

/**
 *
 * @author Alumno Mañana
 */
public class Orden {
    //atributos, variables estáticas y constantes
    private int idOrden;
    private ArrayList<Producto> productos; 
    static int contadorOrdenes;
    static final int MAX_PRODUCTOS = 10;
    
    //getter & setter

    public int getIdOrden() {
        return idOrden;
    }
    

    //constructor
    public Orden() {
        contadorOrdenes++;
        this.idOrden = contadorOrdenes;
        ArrayList<Producto> lista = new ArrayList<>();
        this.productos = lista;
    }
    
    //METODOS Y FUNCIONES
    //agregarProducto comprueba si se ha llegado al máximo permitido de productos por orden usando un if-else. En casao de no hacerlo, añade el producto p que le pasamos como parámetro
    public void agregarProducto(Producto p){
        if(this.productos.size()<MAX_PRODUCTOS){
            this.productos.add(p);
        }else{
            System.out.println("Se ha superado el número de productos por orden permitido");
        }
        
    }
    
    //calcularTotal recore los productos de la orden con un for y va haciendo un sumatorio de todos los precios en forma de double, para despuén devolverlo en el return
    public double calcularTotal(){
        double total = 0.00;
        for(Producto i:this.productos){
            total = total + i.getPrecio();
        }
        return total;
    }
    
    //mostrarOrden de nuevo recorre los productos de la orden, y usando el método toString de la clase Producto los va imprimiendo por pantalla
    public void mostrarOrden(){
        System.out.println("LISTADO DE PRODUCTOS DE LA ORDEN "+this.idOrden);
        System.out.println("===============================================================================");
        for(Producto i:this.productos){
            System.out.println(i.toString());
        }
    }
    
    
}

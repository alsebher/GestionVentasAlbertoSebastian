/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;
import dominio.Producto;
import dominio.Orden;
import java.util.ArrayList;
import java.util.Scanner;
import ficheros.Archivos;

/**
 *
 * @author Alumno Mañana
 */
public class VentasTest {
    static Scanner ent = new Scanner(System.in);
    static ArrayList<Producto> catalogo = new ArrayList<>();
    static ArrayList<Orden> listOrden = new ArrayList<>();
    
    public static void main(String[] args) {
        
        
        /*
        Producto p1 = new Producto("Headset",89.99);
        Producto p2 = new Producto("Keyboard",139.99);
        Producto p3 = new Producto("Mouse",99.99);
        Producto p4 = new Producto("Monitor",799.99);
        Producto p5 = new Producto("Alfombrilla",19.99);
        Producto p6 = new Producto("USB",15.99);
        Producto p7 = new Producto("Silla",199.99);
        Producto p8 = new Producto("Torre PC",3799.99);
        Producto p9 = new Producto("Portátil",1089.99);
        Producto p10 = new Producto("Tarjeta gráfica",439.99);
        Producto p11 = new Producto("Altavoces",59.99);
        Producto p12 = new Producto("Mando",45.99);
        Orden o1 = new Orden();
        Orden o2 = new Orden();
        o1.agregarProducto(p1);
        o1.agregarProducto(p2);
        o1.agregarProducto(p3);
        o2.agregarProducto(p1);
        o2.agregarProducto(p2);
        o2.agregarProducto(p3);
        o2.agregarProducto(p4);
        o1.mostrarOrden();
        System.out.println("El coste total asciende a "+o1.calcularTotal()+" euros");
        System.out.println("");
        o2.mostrarOrden();
        System.out.println("El coste total asciende a "+o2.calcularTotal()+" euros");
        */
        Archivos.crearArchivo("ordenes.txt");
        Archivos.crearArchivo("stock.txt");
        menu();
        
        
    }
    //creo un método que servirá como interfaz de menú, en la que gracias a un switch se podrá elegir lo que hay que hacer en cada momento, con llamadas a distintos métodos en función de la opción y siempre volviendo al menú tras cada acción a excepción lógicamente del comando salir
    public static void menu(){
        int opcion=-1;
	while(opcion!=0){
		System.out.println("");
                System.out.println("");
                System.out.println("");
                System.out.println("");
                System.out.println("");
                System.out.println("");
                System.out.println("");
		System.out.printf("\tGESTION DE VENTAS\n");
		System.out.printf("\t=========================================================================\n");
                System.out.printf("1.- INSTANCIAR UN PRODUCTO\n");
		System.out.printf("2.- AÑADIR PRODUCTOS A UNA ORDEN NUEVA\n");
		System.out.printf("3.- VISUALIZAR PRODUCTOS INCLUIDOS EN CADA ORDEN EN CURSO\n");
		System.out.printf("4.- VISUALIZAR COSTE TOTAL DE CADA ORDEN EN CURSO\n");
                System.out.printf("5.- LIMPIAR PRODUCTOS\n");
                System.out.printf("6.- LIMPIAR COLA DE ORDENES EN CURSO\n");
                System.out.printf("7.- LEER ARCHIVO DE STOCK\n");
                System.out.printf("8.- LEER ARCHIVO DE ORDENES\n");
		System.out.printf("0.- SALIR\n");
		System.out.printf("\n\tSeleccione una de las siguientes opciones: ");
		opcion = ent.nextInt();
		switch(opcion){
			case 1:
				instanciarProducto();
				break;
			case 2:
				instanciarOrden();
				
				break;
			case 3:
				visualizar();
				
				break;
			case 4:
                                factura();
				break;
                        case 5:
                                borrarProd();
				break;
                        case 6:
                                borrarOrd();
                                
				break;
                        case 7:
                                Archivos.leerArchivo("stock.txt");
                                
				break;
                        case 8:
                                Archivos.leerArchivo("ordenes.txt");
                                
				break;
			case 0:
                            System.out.println("Gracias por usar la aplicación");
				break;
			default:
				System.out.printf("\nElija entre 0 y 4\n\n");
				break;
		}
                ent.nextLine();
                ent.nextLine();
                
		
	}
    }
    
    //el método instanciar producto sirve para que el usuario cree objetos producto por teclado, indicando el nombre y precio del producto deseado. Entonces este producto creado se añadirá a un ArrayList estático catálogo, que servirá como "recipiente" de los productos creados hasta que decidamos crear una orden con ellos. Es importante saber que en una misma ejecución de la aplicación, se irán añadiendo productoas al catálogo uno a uno las veces deseadas, y el método instanciarProducto NO va a vaciar el catálogo en ningún momento.
    public static void instanciarProducto(){
        String nom;
        double pr;
        System.out.println("Introduzca el nombre del producto que va a desear: ");
        ent.nextLine();
        nom = ent.nextLine();
        System.out.println("Introduzca su precio, recuerde que los decimales se escriben con una coma y NO un punto: ");
        pr = ent.nextDouble();
        Producto p = new Producto(nom,pr);
        catalogo.add(p);
        System.out.println("El producto ha sido creado en el catálogo de manera satisfactoria");
        //en el stock las columnas representan el ID, nombre y precio
        Archivos.agregarArchivo("stock.txt",p.getIdProducto()+"|"+p.getNombre()+"|"+p.getPrecio());
        Archivos.agregarArchivo("stock.txt", "\n");
    }
    
    //instanciarOrden crea un nuevo objeto Orden cada vez que se ejecuta, en el que mediante el método de Orden agregarProducto se añadirán a su atributo productos todos los productos que estén en el catálogo en ese mismo inatnte. Si el catálogo está vacío, lanza un mensaje de alerta. Al igual que con los productos, creo un "contenedor" estático para objetos orden en forma de ArrayList para ir añadiendo las órdenes que se van creando, ya que facilita mucho la aplicación de los métodos de Orden calcularTotal y mostrarOrden.
    public static void instanciarOrden(){
        Orden o = new Orden();
        if(catalogo.isEmpty()){
            System.out.println("Antes de comenzar una orden es necesario crear los productos");
        }else{
            for(Producto i:catalogo){
                o.agregarProducto(i);
            }
            System.out.println("Todos los productos previamente creados han sido añadidos a una nueva orden");
            listOrden.add(o);
            //en ordenes.txt las columnas separadas por pipes representan el ID de la orden, el total de la orden, el número de productos en la orden y el id de cada producto en la orden en forma de clave ajena
            Archivos.agregarArchivo("ordenes.txt",o.getIdOrden()+"|"+o.calcularTotal()+"|"+catalogo.size()+"|"+productosId()+"\n");
            
            
        }
    }
    //sistema para implementar las claves ajenas
    public static String productosId(){
        int seguimiento=0;
        String lista = "";
        for(Producto i:catalogo){
            seguimiento++;
            if(seguimiento==catalogo.size()){
                lista = lista + i.getIdProducto();
            }else{
                lista = lista + i.getIdProducto()+",";
            }
        }
        return lista;
    }
    
    //visualizar no es más que la aplicación del método de Orden mostrarOrden en cada una de las órdenes previamente creadas recorriendo listOrden. De nuevo, si no hay órdenes creadas lanza un mensaje al respecto.
    public static void visualizar(){
        if(listOrden.isEmpty()){
            System.out.println("No existe aún ninguna orden para visualizar");
        }else{
            for(Orden j:listOrden){
                j.mostrarOrden();
                System.out.println("");
            }
        }
    }
    
    //factura funciona de manera análoga a visualizar, solo que esta vez el método de Orden aplicado es calcularTotal
    public static void factura(){
        if(listOrden.isEmpty()){
            System.out.println("No existe aún ninguna orden para facturar");
        }else{
            int cont = 1;
            for(Orden j:listOrden){
                double t = j.calcularTotal();
                System.out.println("El total de la orden "+cont+" asciende a los "+t+" euros");
                System.out.println("");
                cont++;
            }
        }
    }
    
    //borrarProd vacía el ArrayList de productos llamado catalogo, para una mejor usabilidad de la app
    public static void borrarProd(){
        catalogo.clear();
        System.out.println("El catálogo de productos se ha limpiado con éxito");
    }
    
    //borrarOrd vacía el ArrayList de órdenes llamado listOrden, para una mejor usabilidad de la app
    public static void borrarOrd(){
        listOrden.clear();
        System.out.println("La cola de órdenes en curso se ha borrado");
    }
    
    
    //ALBERTO SEBASTIAN HERNANDEZ
}

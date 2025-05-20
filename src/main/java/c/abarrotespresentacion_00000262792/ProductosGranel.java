/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c.abarrotespresentacion_00000262792;

import abarrotesobjetosnegocio_253244.ProductoGranel;
import java.util.HashMap;

/**
 *
 * @author Luis
 */
public class ProductosGranel {
    int clave;
    
    /*
     * producto granel es el inventario donde estan todos los productos
     */
    HashMap<String, ProductoGranel> productosGranel = new HashMap<>();
    
    /*
     * Agregar Producto toma como parametro una clave y un producto granel para despues agregarlo al inventario
     *
     * @param clave es la clave asociada del producto granel que se va a agregar al inventario
     * @param ProductoGranel es el objeto que se agregara al inventario
     */
    public void agregarProducto(String clave, ProductoGranel ProductoGranel){
     productosGranel.put(clave, ProductoGranel);
    }
    
    /*
     * Actualizar producto toma como parametro una clave y un producto granel para despues agregar el producto actualizado
     *
     * @param clave es la clave asociada del producto granel que se va a agregar al inventario
     * @param ProductoGranel es el objeto que se agregara al inventario
     */
    public void actualizarProducto(String clave, ProductoGranel productoGranel){
        productosGranel.put(clave, productoGranel);
    }
    
    /*
     * Eliminar producto toma como parametro una clave asociada al producto que se desea eliminar para eliminar al objeto con esta clave 
     *
     * @param clave es la clave asociada del producto granel que se va a agregar al inventario
     */
    public void eliminarProducto(String clave){
        productosGranel.remove(clave);
    }
    
    /*
     * Consultar producto toma como parametro una clave para acceder en el hashMap y obtener su nombre, clave, unidad, cantidad, tipo etc.
     *
     *@param clave es la clave asociada del producto granel que se va a agregar al inventario
     */
    public void consultarProducto(String clave){
        System.out.println("Nombre: " + productosGranel.get(clave).getNombre() +
                           "\nClave: " + productosGranel.get(clave).getClave() +
                           "\nUnidad: " + productosGranel.get(clave).getUnidad() + 
                           "\nCantidad" + productosGranel.get(clave).getCantidad() + 
                           "\nTipo" + productosGranel.get(clave).getTipo());
        
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }
    
    
}

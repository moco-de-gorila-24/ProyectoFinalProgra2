/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c.abarrotespresentacion_00000262792;

import abarrotesobjetosnegocio_253244.Producto;
import java.util.HashMap;

/**
 *
 * @author Luis
 */
public class Productos {
    String clave;
    
    /*
     * productosP es donde se almacenan los productos agregados por el usuario
     */
    HashMap<String, Producto> productosP = new HashMap<>();
    
    /*
     * Agregar producto toma como parametro una clave y un objeto tipo producto para agregarlo al hashMao productosP
     *
     * @param clave es la clave asociada al producto
     * @param producto es el producto creado por el usuario
     */
    public void agregarProducto(String clave, Producto producto){
        productosP.put(clave, producto);
    }
    
    /*
     * Actualizar producto toma como parametro una clave y un objeto producto para agregar el objeto actualizado y su clave
     * 
     * @param clave es la clave asociada al producto
     * @param producto es el producto creado por el usuario
     */
    public void actualizarProducto(String clave, Producto producto){
        productosP.put(clave, producto);
    }
    
    /*
     * Eliminar producto toma como parametro la clave asociada al producto que se desea eliminar del HashMap
     *
     * @param clave es la clave asociada al producto
     */
    public void eliminarProducto(String clave){
        productosP.remove(clave);
    }
    
    /*
     * Consultar producto toma como parametro la clave asociada al valor que se desea consultar para despues
     * acceder a los atributos del objeto 
     */
    public void consultarProducto(String clave){
        System.out.println("*******Producto*******" +
                           "\nNombre: " + productosP.get(clave).getNombre() +
                           "\nClave: " + productosP.get(clave).getClave() +
                           "\nTipo: "+ productosP.get(clave).getTipo() +
                           "\nUnidad: " + productosP.get(clave).getUnidad() + 
                           "\n*********************") ;
    }
   
    public String getClave() {
        return productosP.get(clave).getClave();
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre(){
        return productosP.get(clave).getNombre();
    }
    
    
    
}

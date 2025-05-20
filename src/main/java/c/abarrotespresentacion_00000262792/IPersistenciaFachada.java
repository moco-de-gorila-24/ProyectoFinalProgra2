/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package c.abarrotespresentacion_00000262792;

import abarrotesobjetosnegocio_253244.MovimientoGranel;
import abarrotesobjetosnegocio_253244.Producto;
import abarrotesobjetosnegocio_253244.ProductoGranel;

/**
 *
 * @author Luis
 */
public interface IPersistenciaFachada {
    
    /*
     * En resumen en esta interface estan declarados los metodos que se van a sobreescribir 
     */
   
    public Producto obtenerProducto(String clave);
    public MovimientoGranel obtenerVenta(MovimientoGranel venta);
    public ProductoGranel obtenerProductoInventario(ProductoGranel productoGranel);
    public MovimientoGranel obtenerCompra(MovimientoGranel compra);
    
    public void agregarProducto(String clave, Producto producto);
    public void agregarVenta(String clave, MovimientoGranel venta);
    public void agregarCompra(String clave, MovimientoGranel compra);
    
    public void actualizarProducto(String clave, Producto producto);
    public void actualizarVenta(String clave, MovimientoGranel venta);
    public void actualizarCompra(String clave, MovimientoGranel compra);
    
    public void eliminarProducto(String clave);
    public void eliminarVenta(String clave);
    public void eliminarCompra(String clave);
    
    public void actualizarInventarioCompras();
    public void actualizarInventarioVentas();
    
}

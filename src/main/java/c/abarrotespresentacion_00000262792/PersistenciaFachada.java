/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package c.abarrotespresentacion_00000262792;

import abarrotesobjetosnegocio_253244.MovimientoGranel;
import abarrotesobjetosnegocio_253244.Producto;
import abarrotesobjetosnegocio_253244.ProductoGranel;

/**
 *
 * @author Luis
 */
public class PersistenciaFachada implements IPersistenciaFachada {
    String clave;
    Productos persistenciaProductos;
    ProductosGranel persistenciaInventario;
    MovimientosGranel persistenciaVentas;
    MovimientosGranel persistenciaCompras;
    
    public PersistenciaFachada(){
        this.persistenciaProductos = new Productos();
        this.persistenciaInventario = new ProductosGranel();
        this.persistenciaVentas = new MovimientosGranel();
        this.persistenciaCompras = new MovimientosGranel();   
    }

    @Override
    public Producto obtenerProducto(String clave) {
        return persistenciaProductos.productosP.get(clave);
    }

    @Override
    public MovimientoGranel obtenerVenta(MovimientoGranel venta) {
        return persistenciaVentas.movimientosVentas.get(clave);
    }

    @Override
    public ProductoGranel obtenerProductoInventario(ProductoGranel productoGranel) {
        persistenciaInventario.consultarProducto(clave);
        return null;
    }

    @Override
    public MovimientoGranel obtenerCompra(MovimientoGranel compra) {
        return persistenciaCompras.movimientosCompras.get(clave);
    }

    @Override
    public void agregarProducto(String clave, Producto producto) {
        persistenciaProductos.agregarProducto(clave, producto);
    }

    @Override
    public void agregarVenta(String clave, MovimientoGranel venta) {
        persistenciaVentas.agregarMoviminetoGranel(clave, venta);
    }

    @Override
    public void agregarCompra(String clave, MovimientoGranel compra) {
        persistenciaCompras.agregarMoviminetoGranel(clave, compra);
    }

    @Override
    public void actualizarProducto(String clave, Producto producto) {
        persistenciaProductos.agregarProducto(clave, producto);
    }

    @Override
    public void actualizarVenta(String clave, MovimientoGranel venta) {
        persistenciaVentas.actualizarMovimeintoGranel(clave, venta);
    }

    @Override
    public void actualizarCompra(String clave, MovimientoGranel compra) {
        this.persistenciaCompras.actualizarMovimeintoGranel(clave, compra);
    }

    @Override
    public void eliminarProducto(String clave) {
        this.persistenciaProductos.eliminarProducto(clave);
    }

    @Override
    public void eliminarVenta(String clave) {
        this.persistenciaVentas.eliminarMovimientoGranel(clave);
    }

    @Override
    public void eliminarCompra(String clave) {
        this.persistenciaCompras.eliminarMovimientoGranel(clave);
    }

    @Override
    public void actualizarInventarioCompras() {
        
    }

    @Override
    public void actualizarInventarioVentas() {
        
    }  
}

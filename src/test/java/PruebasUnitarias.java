/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import abarrotesobjetosnegocio_253244.MovimientoGranel;
import abarrotesobjetosnegocio_253244.Producto;
import abarrotesobjetosnegocio_253244.ProductoGranel;
import c.abarrotespresentacion_00000262792.PersistenciaFachada;
import java.util.HashMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Central
 */
public class PruebasUnitarias {
    
    public PruebasUnitarias() {
       
    }
    
    @BeforeAll
    public static void setUpClass() {
        
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    
    @Test
    public void pruebaAgregarProducto(){   
        
        String claveValida = "AM002";
        char tipoValido = 'E';         
        String nombreValido = "uvas"; 
        String unidadValida = "KG";
                          
        Producto producto = new Producto(claveValida,nombreValido,tipoValido, unidadValida);

        assertTrue(claveValida.matches("[a-zA-Z][a-zA-Z][0-9][0-9][0-9]"));
        assertEquals(tipoValido, 'E');
        assertEquals(nombreValido, producto.getNombre());
        assertEquals(tipoValido, producto.getTipo());
        assertEquals(unidadValida, producto.getUnidad());
    }
    
    @Test
    public void pruebaConsultarClave(){
        String claveValida = "AM002";
        char tipoValido = 'E';         
        String nombreValido = "uvas"; 
        String unidadValida = "KG";
                          
        Producto producto = new Producto(claveValida,nombreValido,tipoValido, unidadValida);
        PersistenciaFachada fachada = new PersistenciaFachada();
        fachada.obtenerProducto(claveValida); 
    }
    
    @Test
    public void pruebaActualizarProducto(){
        String claveValida = "AM002";
        char tipoValido = 'E';         
        String nombreValido = "uvas"; 
        String unidadValida = "KG";
        
        String nuevaClave = "MP001";
        String nuevoNombre = "Manzanas";
        char nuevoTipo = 'G';
        String nuevaUnidad = "L";
        
        Producto producto = new Producto(claveValida,nombreValido,tipoValido, unidadValida);
      
        producto.setClave(nuevaClave);
        producto.setNombre(nuevoNombre);
        producto.setTipo(nuevoTipo);
        producto.setUnidad(nuevaUnidad);
        
        assertEquals(nuevaClave, producto.getClave());
        assertEquals(nuevoNombre, producto.getNombre());
        assertEquals(nuevoTipo, producto.getTipo());
        assertEquals(nuevaUnidad, producto.getUnidad());
        
    }
    
    @Test
    public void pruebaEliminarProducto(){
        HashMap<String, Producto> productos = new HashMap<>();
        
        String claveValida = "AM002";
        char tipoValido = 'E';         
        String nombreValido = "uvas"; 
        String unidadValida = "KG";
                          
        Producto producto = new Producto(claveValida,nombreValido,tipoValido, unidadValida);
        productos.put(claveValida, producto);
        productos.remove(claveValida);
        
        assertTrue(productos.isEmpty());
    }
    
    @Test
    public void compraProductoGranel(){
        String claveValida = "AM002";
        char tipoValido = 'E';         
        String nombreValido = "uvas"; 
        String unidadValida = "KG";
        double cantidadValida = 12.40;
        String claveMovimientoValida = "AM001";
        String fechaValida = "20/5/25";
        
        Producto producto = new Producto(claveValida,nombreValido,tipoValido, unidadValida);
        assertEquals(claveValida, producto.getClave());
        assertEquals(tipoValido, producto.getTipo());
        assertEquals(nombreValido, producto.getNombre());
        assertEquals(unidadValida, producto.getUnidad());        
        
        ProductoGranel productoG = new ProductoGranel((float)cantidadValida, producto);
        assertEquals(cantidadValida, productoG.getCantidad());
        
        MovimientoGranel movimientoG = new MovimientoGranel(productoG, claveMovimientoValida, fechaValida, false);
        assertEquals(claveMovimientoValida, movimientoG.getClaveMovimento());
        assertEquals(fechaValida, movimientoG.getFecha());
        
        PersistenciaFachada fachada = new PersistenciaFachada();
        fachada.agregarCompra(claveValida,movimientoG);
        
    }
    
    
    
}


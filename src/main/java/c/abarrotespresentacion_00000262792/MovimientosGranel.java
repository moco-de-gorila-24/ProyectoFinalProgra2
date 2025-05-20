package c.abarrotespresentacion_00000262792;

import abarrotesobjetosnegocio_253244.MovimientoGranel;
import java.util.HashMap;

/**
 *
 * @author Luis
 */
public class MovimientosGranel {
    String clave;
    
    
    /**
     * Movimientos compras es donde se registrn las compras de productos granel
     */
    HashMap<String,MovimientoGranel> movimientosCompras = new HashMap<>();
    /*
     * Movimientos ventas es donde se registran todas las ventas de productos granel
     */
    HashMap<String,MovimientoGranel> movimientosVentas = new HashMap<>();
    
    
    /*
     * Agregar movimiento toma como parametro una clave asociada a un valor y un un objeto tipo MovimientoGranel
     * para agregarlos al HashMap movimientos compras
     * 
     * @param clave es la clave asociada al producto comprado
     * @param movimientoGranel es el objeto que contiene el producto comprado
     */
    public void agregarMoviminetoGranel(String clave, MovimientoGranel movimientoGranel){
        movimientosCompras.put(clave, movimientoGranel);
    }
    
    /*
     * Actualizae movimiento toma como parametro una clave y un objeto tipo movimientoGranel
     * Promero elimina el objeto asociado a la clave y despues agrega al HashMap el objeto movimientoGranel
     *
     * @param clave es la clave asociada al producto comprado
     * @param movimientoGranel es el objeto que contiene el producto comprado
     */
    public void actualizarMovimeintoGranel(String clave, MovimientoGranel movimientoGranel){
        movimientosCompras.remove(clave);
        movimientosCompras.put(clave, movimientoGranel);
    }
    
    /*
     * Elimina el objeto asociado con la clave tomada como parametro
     * @param clave es la clave asociada al producto comprado
     */
    public void eliminarMovimientoGranel(String clave){
       movimientosCompras.remove(clave);
    }
    
    /*
     * Consultar movimiento toma como parametro una clave para acceder al producto a granel que see desea consultar
     * tambien se accede a la clave de movimiento y la fecha en la que se realizo la compra del producto a granel
     *
     *@param clave es la clave asociada al producto comprado
     */
    public void consultarMovimientoGranel(String clave){
        System.out.println("Producto a granel : " + movimientosCompras.get(clave).getProductoGranel() +
                           "Clave movimiento" + movimientosCompras.get(clave).getClaveMovimento() + 
                           "Fecha: " + movimientosCompras.get(clave).getFecha());
        
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    /*
     * Este metodo toma como parametro la clave asociada al producto comprado y una cantidad que es la comprada
     * primero evalua si el HashMap esta vacio, despues evalua si la cantidad es negativa y al final accede al producto
     * y setea la cantidad del producto a granel sumando su cantidad con la cantidad tomada como parametro
     *
     * @param clave es la clave asociado al producto a granel comprado
     * @param cantidad la cantidad que se compro
     */
    public void sumarCantidad(String clave, float cantidad){
        if(!movimientosCompras.containsKey(clave)){
            System.out.println("El objeto no existe");
            return;
        }
        
        if(cantidad <0){
            System.out.println("Ingrese un numero positivo");
            return;
        }
        movimientosCompras.get(clave).getProductoGranel().setCantidad((movimientosCompras.get(clave).getProductoGranel().getCantidad() + cantidad)/2);
        
    } 
    
    /*
     * EL metodo toma como parametro una clave y una cantidad, primero verifica que el HashMap no este vacio y si lo esta te regresa al menu
     * despues evalua que la cantidad del producto granel no sea menor para que no quede negativo, y por ultimo verifica que la cantidad no sea
     * negativa
     * 
     * @param clave es la clave asociada al producto que se vendio
     * @param cantidad es la cantidad vendida de este
     */
    public void restarCantidad(String clave, float cantidad){
        if(!movimientosVentas.containsKey(clave)){
            System.out.println("El producto no existe");
            return;
        }
        
        if(cantidad <0){
            System.out.println("Ingrese un numero positivo");
            return;
        }
        
        movimientosVentas.get(clave).getProductoGranel().setCantidad(movimientosVentas.get(clave).getProductoGranel().getCantidad() - cantidad);
    }
    
}

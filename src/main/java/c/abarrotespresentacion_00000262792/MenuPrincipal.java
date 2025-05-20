package c.abarrotespresentacion_00000262792;

import abarrotesobjetosnegocio_253244.MovimientoGranel;
import abarrotesobjetosnegocio_253244.Producto;
import abarrotesobjetosnegocio_253244.ProductoGranel;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;


/**
 *
 * @author Luis
 */

public class MenuPrincipal {
    PersistenciaFachada fachada;    
    
    public MenuPrincipal(){
        this.fachada = new PersistenciaFachada();
    }
    
    public void mostrarMenu(){
        Scanner sc = new Scanner(System.in);
        
        while(true){
            System.out.println("***********************" +
                               "\n0. Salir"            + 
                               "\n1. Agregar producto" +
                               "\n2. Consultar producto" +
                               "\n3. Actualizar producto" +
                               "\n4. Eliminar producto" +
                               "\n5. Consultar catalogo de productos" +
                               "\n6. Comprar producto granel" + 
                               "\n7. Vender producto granel" + 
                               "\n8. Consultar compra por clave" + 
                               "\n9. Mostrar inventario" +
                               "\n10. Mostrar el registro de compras" +
                               "\n11. Mostrar el registro de ventas");
            
            try{
                int opcion = sc.nextInt();
                switch(opcion){
                    case 0:
                        return;
                    case 1:
                        opcionAgregarProducto();
                        break;

                    case 2:
                        consultarPorClave();
                        break;
                    case 3:
                        actualizarProducto();
                        break;
                    case 4:
                        eliminarProducto();
                        break;
                    case 5:
                        consultarCatalogoProductos();
                        break;
                    case 6:
                        compraProductoGranel();
                        break;
                    case 7:
                        ventaProductoGranel();
                        break;
                    case 8:
                        consultarCompraPorClave();
                        break;
                    case 9:
                        mostrarInventario();
                        break;
                    case 10:
                        mostrarRegistroCompras();
                        break;
                    case 11:
                        mostrarRegistroVentas();
                        break;

                }
            }
            
            catch(InputMismatchException e){
                System.out.println("Ingrese un numero entero ");
                sc.nextLine();
            }
        } 
    }
    //15.*
    /*
     *El metodo agregar producto primero pide al usuario la clave del producto que se desea agregar para despues validar que este escrita correctamente
     *con una expresion regular para despues pedir los datos nombre, tipo y unidad para despues verificar que estos no esten vacios para despues verificar
     *que el tipo si este correcto junto con la unidad para despues crear el objeto Producto con los parametros clave, nombre, tipo y unidad
     *para despues agregarlos al hashmap y finalmente mostrar el producto agregado
     */
    public void opcionAgregarProducto(){
        Scanner sc = new Scanner(System.in);
        
        try{
            System.out.println("Ingrese la clave del producto en formato MM000");
            String clave = sc.next();

            if(clave.matches("[a-zA-Z][a-zA-Z][0-9][0-9][0-9]")==true){
                 if(fachada.persistenciaProductos.productosP.containsKey(clave)){
                     System.out.println("La clave esta repetida");
                     mostrarMenu();
                 }

            System.out.println("Ingrese el nombre del producto");
            String nombre = sc.next();

            System.out.println("Ingrese el tipo E o G ");
            String tipo = sc.next();

            if(nombre.equals("") || tipo.equals("")){
                    System.out.println("El nombre y/o el producto estan vacios");
                    mostrarMenu();
            }             

            System.out.println("Ingrese la unidad en: KG, L o PZ");
            String unidad = sc.next();
            if(tipo.matches("[E]|[G]")==true){

                if(unidad.equals("KG") || unidad.equals("L") || unidad.equals("PZ")){
                Producto nuevoProducto = new Producto(clave, nombre, tipo.charAt(0), unidad);
                fachada.agregarProducto(clave, nuevoProducto);
                System.out.println("Producto agregado correctamente" + 
                                   "\nClave: " + fachada.persistenciaProductos.productosP.get(clave).getClave() +
                                   "\nNombre: " + fachada.persistenciaProductos.productosP.get(clave).getNombre() +
                                   "\nTipo: " + fachada.persistenciaProductos.productosP.get(clave).getTipo() +
                                   "\nUnidad:" + fachada.persistenciaProductos.productosP.get(clave).getUnidad());
                }

                else{
                    System.out.println("unidad de producto no valida, debe ser Kg o L o PZ");
                    opcionAgregarProducto();
                }
            }

            else{
                System.out.println("Tipo de producto no valida, debe ser E o G");
                opcionAgregarProducto();
            }
        }
            
        else{
            System.out.println("La clave deben ser dos mayusculas y tres numeros");
            opcionAgregarProducto();
        }
    }
        
        catch(InputMismatchException e){
            System.out.append("Ingrese un dato valido" + e);
            sc.nextLine();
        }
        
    }
    //16.*
    /*
     *El metodo consultar por clave primero valida que el hashmap de productos no este vacio para despues pedir la clave y validar que este escrita correctamente con una
     *expresion regular para despues verificar que el producto exista y finalmente consultar el producto
     */
    public void consultarPorClave(){
        Scanner sc = new Scanner(System.in);
         try{
             
            if(fachada.persistenciaProductos.productosP.isEmpty()){
                System.out.println("No hay productos");
                return;
            }
       
            System.out.println("Ingrese la clave del producto que desea consultar en formato MM000");
            String clave = sc.next();

            if(!clave.matches("[a-zA-Z][a-zA-Z][0-9][0-9][0-9]")){
               System.out.println("Ingrese la clave en el formato de 2 mayusculas y 3 numeros");
               return;
            }

            if(!fachada.persistenciaProductos.productosP.containsKey(clave)){
                System.out.println("El producto no existe");
                return;
            }

            fachada.persistenciaProductos.consultarProducto(clave);
        } 
        catch(InputMismatchException e){
            System.out.println("Ingrese un dato valido " + e);
            sc.nextLine();
        }
        catch(NullPointerException e){
            System.out.println("No hay productos" + e);
            sc.nextLine();
        }
    }
    //17.*
    /*
     *El producto actualizar producto primero valida que el hashmap de productos no este vacio despues pide la clave del producto para validar que el producto 
     *exista en el hashmap despues se pide la nueva clave y ambas claves se validan que cumplan con el formato y se valida que la nueva clave no este repetida
     *para luego pedir el nuevo nombre, tipo y unidad validar estos datos y al final setear los datos
     */
    public void actualizarProducto(){
        Scanner sc = new Scanner(System.in);
       
        try{
            
            if(fachada.persistenciaProductos.productosP.isEmpty()){
                System.out.println("No hay productos para modificar");
                mostrarMenu();
            }

            System.out.println("Ingrese la clave del producto que desee actualizar en formato MM000");
            String clave = sc.next();

            System.out.println("Ingrese la nueva clave en formaro MM000");
            String nuevaClave = sc.next();

            if(!clave.matches("[a-zA-Z][a-zA-Z][0-9][0-9][0-9]") && !nuevaClave.matches("[a-zA-Z][a-zA-Z][0-9][0-9][0-9]")){
                System.out.println("La clave debe ser en formato MM000");
                return;
            }    
            
             if(!fachada.persistenciaProductos.productosP.containsKey(clave)){
                System.out.println("El producto no existe");
                return;
            }
            
            if(fachada.persistenciaProductos.productosP.containsKey(nuevaClave)){
                System.out.println("La nueva clave esta repetida");
                return;
            }

            System.out.println("Ingrese el nuevo nombre");
            String nuevoNombre = sc.next();

            System.out.println("Ingrese el nuevo tipo (E o G)");
            String nuevoTipo = sc.next();

            System.out.println("Ingrese la unidad en: KG, L o PZ");
            String nuevaUnidad = sc.next();

            if(!nuevoTipo.matches("[E]|[G]")){
                System.out.println("El tipo debe ser E o G");
                return;         
            }

            if(nuevaUnidad.equals("KG") || nuevaUnidad.equals("L") || nuevaUnidad.equals("PZ")){
                System.out.println("Seguro de realizar los cambios? s/n ");
                String opcion = sc.next();

                if(opcion.equals("n") || opcion.equals("N") ){
                    System.out.println("Descartando cambios....");
                    return;
                }

                if(opcion.equals("s") || opcion.equals("S") ){
                    System.out.println("Guardando cambios....");
                }

                Producto producto = fachada.persistenciaProductos.productosP.get(clave);
                producto.setNombre(nuevoNombre);
                producto.setTipo(nuevoTipo.charAt(0));
                producto.setUnidad(nuevaUnidad);

                fachada.persistenciaProductos.productosP.remove(clave);
                fachada.persistenciaProductos.productosP.put(nuevaClave, producto);

                System.out.println("Cambios realizados con exito");
            }

            else{
                System.out.println("La unidad debe ser en KG 0 L o PZ");
                return;
            }
        }
        
        catch(InputMismatchException e){
            System.out.println("Ingrese un dato valido");
            sc.nextLine();
        }
    }
    //18.*
    /*
     *El producto eliminar producto primero verifica que el hashmap de productos no este vacio luego se pide al usuario la clave del producto que se quiere eliminar para despues 
     *validar que este escrito con el formato pedido con una expresion regular despues se verifica que la clave este asociada a un valor para despues preguntar si se desea borrar
     *el producto para finalmente borrar el producto
     */
    public void eliminarProducto(){
        Scanner sc = new Scanner(System.in);
        
        try{
            if(fachada.persistenciaProductos.productosP.isEmpty()){
                System.out.println("No hay productos para eliminar");
                mostrarMenu();
            }

            String clave = sc.next();

            if(!clave.matches("[a-zA-Z][a-zA-Z][0-9][0-9][0-9]")){
               System.out.println("Ingrese la clave en el formato de 2 mayusculas y 3 numeros");
               return;
            }
            
            if(!fachada.persistenciaProductos.productosP.containsKey(clave)){
                System.out.println("El producto no existe");
                return;
            }
            
            System.out.println("Seguro de eliminar el producto? s/n");
            String opcion = sc.next();

            if(opcion.equals("s") || opcion.equals("S") || opcion.equals("n") || opcion.equals("N") ){
                    System.out.println("Descartando cambios......");
                    mostrarMenu();
                }

                else{
                    System.out.println("Debe ingresar S o n");
                    return;
                }

            fachada.persistenciaProductos.eliminarProducto(clave);
        }
        catch(InputMismatchException e){
            System.out.println("Ingrese un dato valido");
            sc.nextLine();
        }
        catch(NullPointerException e){
            System.out.println("No hay productos");
            sc.nextLine();
        }
        
    }
    //19.*
    /*
     *El metodo consultar catalogo de productos primero revisa que el hashmap del catalogo de productos no este vacio, despues le pide al usuario que ingrese que tipo de filtro
     *quisiera aplicar si por tipo, unidad ambos(tipo y unidad) o sin espesificar que seria mostrar todo el catalogo, en el filtro por tipo se pide el tipo para despues con 
     *un for each iterar en los valores del hashmap hasta que coinsida el tipo dado por el ususario que tambien se validan que esten escritos correctamente, con los siguientes
     *3 casos seria el mismo funcionamiento solo que el caso sin espesificar solo se imprimen los datos no se piden datos por teclado
     */
    public void consultarCatalogoProductos(){
        Scanner sc = new Scanner(System.in);
        
        try{
        if(fachada.persistenciaProductos.productosP.isEmpty()){
            System.out.println("No hay productos para buscar");
            mostrarMenu();
        }
                System.out.println("ingrese como desea filtrar el catalogo " +
                           "\n0. regresar al menu" +
                           "\n1. tipo" + 
                           "\n2. unidad" +
                           "\n3. ambos (tipo y unidad)" + 
                           "\n4. sin espesificar");
                
        while(true){
            int opcion = sc.nextInt();
            
            switch(opcion){
                case 0:
                    mostrarMenu();
                case 1:
                    System.out.println("Ingrese el tipo del producto");
                    String tipo = sc.next();
                    if(tipo.charAt(0) != 'E' || tipo.charAt(0) != 'G'){
                        System.out.println("El tipo debe ser E o G");
                        return;
                    }
                    
                    for(Producto producto: fachada.persistenciaProductos.productosP.values()){
                        if(tipo.charAt(0) == 'E' || tipo.charAt(0) == 'G'){
                        System.out.println("**Productos encontrados**");
                            System.out.println("*************************" +
                                               "\nClave: " + producto.getClave() +
                                               "\nNombre: " + producto.getNombre() +
                                               "\nTipo: " + producto.getTipo() +
                                               "\nUnidad: " + producto.getUnidad() +
                                               "*************************");
                        }    
                    }
                            
                    break;
                case 2:
                    System.out.println("Ingrese la unidad del producto");
                    String unidad = sc.next();
                    if(unidad != "KG" || unidad != "L" || unidad != "PZ"){
                        System.out.println("La unidad debe ser en KG, L o PZ");
                        return;
                    }
                    
                    for(Producto producto: fachada.persistenciaProductos.productosP.values()){
                        if(unidad == "KG" || unidad == "L" || unidad == "PZ"){
                            System.out.println("**Productos encontrados**");
                            System.out.println("*************************" +
                                               "\nClave: " + producto.getClave() +
                                               "\nNombre: " + producto.getNombre() +
                                               "\nTipo: " + producto.getTipo() +
                                               "\nUnidad: " + producto.getUnidad() +
                                               "*************************");
                        }
                    }  
                    
                    
                    break;
                case 3:
                    System.out.println("Ingrese el tipo");
                    String Tipo = sc.next();
                    
                    if(Tipo.charAt(0) != 'E' || Tipo.charAt(0) != 'G'){
                        System.out.println("El tipo debe ser E o G");
                        return;
                    }
                    System.out.println("Ingrese la unidad");
                    String Unidad = sc.next();
                    
                    if(Unidad != "KG" || Unidad != "L" || Unidad != "PZ"){
                        System.out.println("La unidad debe ser en KG, L o PZ");
                        return;
                    }
                    
                    
                    for(Producto producto: fachada.persistenciaProductos.productosP.values()){
                        if(Tipo.charAt(0) == 'E' && Unidad == "KG" ||
                           Tipo.charAt(0) == 'E' && Unidad == "L" ||
                           Tipo.charAt(0) == 'E' && Unidad == "PZ" ||
                           Tipo.charAt(0) == 'G' && Unidad == "KG" ||
                           Tipo.charAt(0) == 'G' && Unidad == "L" ||
                           Tipo.charAt(0) == 'G' && Unidad == "PZ"){
                            
                            System.out.println("**Productos encontrados**");
                            System.out.println("*************************" +
                                               "\nClave: " + producto.getClave() +
                                               "\nNombre: " + producto.getNombre() +
                                               "\nTipo: " + producto.getTipo() +
                                               "\nUnidad: " + producto.getUnidad() +
                                               "*************************");
                        }
                    }
                    

                    break;
                case 4:
                    for(Producto producto: fachada.persistenciaProductos.productosP.values()){
                            System.out.println("**Productos encontrados**");
                            System.out.println("*************************" +
                                               "\nClave: " + producto.getClave() +
                                               "\nNombre: " + producto.getNombre() +
                                               "\nTipo: " + producto.getTipo() +
                                               "\nUnidad: " + producto.getUnidad() +
                                               "*************************");
                    }
                    break;
            }
                
        }
        
        
        }
        catch(InputMismatchException e){
            System.out.println("Ingrese un dato valido");
            sc.nextLine();
        }
        catch(NullPointerException e){
            System.out.println("No hay productos");
            sc.nextLine();
        }
        
    }
    //20.*
    
    /*
    El metodo comprarProductosGranel primero verifica que el hashmap productosP no este vacio despues pide la clavde del producto que se desea comprar
    luego se pide la clave de movimiento que esta sera la clave del productoGranel despues se verifica que ambas claves esten escritas con el formato correcto con una expresion
    regular, despues se verifica que la clave de movimiento no este repetida tambien se verifica que el producto exista para despues pedir la cantidad que se desea comprar
    para despues verificar que no sea un numero negativo, despues se saca la fecha actual y se guarda en otra variable con formato, despues se crean los objetos 
    nuevoProductoG para este agregarlo al hashmap productosGranel, tambien se crea el objeto nuevoMovimiento donde en este se agrega el productoGranel al constructor del objeto
    y el objeto se agrega al hashmap movimientosCompras para al final hacer la suma al productoGranel
    */
    public void compraProductoGranel(){
        Scanner sc = new Scanner(System.in);

        try{
        
            if(fachada.persistenciaProductos.productosP.isEmpty()){
                System.out.println("No hay productos agregados");
                mostrarMenu();
            }

            System.out.println("Ingrese la clave del producto en fortmato MM000");
            String clave = sc.next();

            System.out.println("Ingrese la clave de movimiento en formato MM000");
            String claveMovimiento = sc.next();

            if(!clave.matches("[a-zA-Z][a-zA-Z][0-9][0-9][0-9]") && !claveMovimiento.matches("[a-zA-Z][a-zA-Z][0-9][0-9][0-9]")){
                 System.out.println("La clave debe ser en formato de 2 mayusculas y 3 numeros");
                 return;
            }

            if(fachada.persistenciaInventario.productosGranel.containsKey(claveMovimiento)){
                     System.out.println("La clave de movimiento ya existe");
                     mostrarMenu();
            }

            if(!fachada.persistenciaProductos.productosP.containsKey(clave)){
                System.out.println("El producto no existe");
                mostrarMenu();
            }

            System.out.println("Ingrese la cantidad que desea comprar");
            float cantidad = sc.nextFloat();


            if(cantidad <0){
                System.out.println("No se puede comprar una cantidad negativa");
                compraProductoGranel();
            }

            Calendar fecha = Calendar.getInstance();
            String fechaFormato = String.format("%02d/%02d/%04d",
                                                    fecha.get(Calendar.DAY_OF_MONTH),
                                                    fecha.get(Calendar.MONTH) +1,
                                                    fecha.get(Calendar.YEAR));

            ProductoGranel nuevoProductoG = new ProductoGranel(cantidad, fachada.persistenciaProductos.productosP.get(clave));
            MovimientoGranel nuevoMovimiento = new MovimientoGranel(nuevoProductoG, clave, fechaFormato, true);

            fachada.persistenciaInventario.productosGranel.put(clave, nuevoProductoG);
            fachada.persistenciaCompras.movimientosCompras.put(claveMovimiento, nuevoMovimiento);
            fachada.persistenciaCompras.sumarCantidad(claveMovimiento, cantidad);

            //23.b
            if(fachada.persistenciaProductos.productosP.get(clave).getUnidad() == "KG" && fachada.persistenciaInventario.productosGranel.get(clave).getCantidad() > 1500){
                System.out.println("El limite de la cantidad si el tipo de unidad es en KG es 1500");
                fachada.persistenciaInventario.productosGranel.remove(clave, nuevoProductoG);
                fachada.persistenciaCompras.movimientosCompras.remove(claveMovimiento, nuevoMovimiento);
                return;
            }


            if(fachada.persistenciaProductos.productosP.get(clave).getUnidad() == "L" && fachada.persistenciaInventario.productosGranel.get(clave).getCantidad() > 3000){
                System.out.println("El limite de la cantidad si el tipo de unidad es en L es 3000");
                fachada.persistenciaInventario.productosGranel.remove(clave, nuevoProductoG);
                fachada.persistenciaCompras.movimientosCompras.remove(claveMovimiento, nuevoMovimiento);
                return;
            }



            System.out.println("*****Compra realizada con exito*****");
            //23.e
            for(Map.Entry<String , ProductoGranel> entry: fachada.persistenciaInventario.productosGranel.entrySet()){
            String clavee = entry.getKey();
            ProductoGranel producto = entry.getValue();
            System.out.println("Productos actualizados: " + 
                               "\nClave: " + clavee + 
                               "\nNombre: " + producto.getNombre() + 
                               "\nCantidad: " + producto.getCantidad() +
                               "\nTipo: " + producto.getTipo() + 
                               "\nUnidad: " + producto.getUnidad());
            }
        }
        catch(InputMismatchException e){
            System.out.println("Ingrese un dato valido");
            sc.nextLine();
        }
        catch(NullPointerException e){
            System.out.println("No hay productos");
            sc.nextLine();
        }
    }
    //21.*
    
    /*
     *El metodo venta productos granel primero verifica que el hashmap movimientosCompras no este vacio ya que depende de este para hacer las ventas
     *para despues pedir por teclado la clave del producto que se desea vender en formato despues se verifica que se cumpla el formato con el maches despues 
     *verifica que el producto exista dentro del hashmap para despues pedir al usuario la cantidad que se va a comprar despues se verifica si la cantidad es negatica o
     *mayor si la cantidad disponible del producto para tomar la fecha actual y sacarle formato y crear el objeto movimientoGranel y agregarlo al hashmap de ventas y hacer la resta
     *del producto vendido al producto del inventario
     */
    public void ventaProductoGranel(){
        Scanner sc = new Scanner(System.in);
        
        try{
            
        if(fachada.persistenciaCompras.movimientosCompras.isEmpty()){
            System.out.println("El inventario esta vacio");
            return;
        }
        
            System.out.println("Ingrese la clave del producto que desee vender en formato MM000");
            String clave = sc.next();

            if(!clave.matches("[a-zA-Z][a-zA-Z][0-9][0-9][0-9]")){
                System.out.println("El formato deben ser 2 mayusculas y 3 numeros");
                return;
            }

            if(!fachada.persistenciaCompras.movimientosCompras.containsKey(clave)){
                System.out.println("El producto no esta en el inventario");
                return;
            }
            
            System.out.println("Ingrese la cantidad que desea comprar");
             float cantidad = sc.nextFloat();
            
            if(cantidad <0 || cantidad > fachada.persistenciaCompras.movimientosCompras.get(clave).getProductoGranel().getCantidad()){
            System.out.println("No se puede comprar una cantidad negativa de producto o una cantidad mayor a la que se tine");
            compraProductoGranel();
            }
        
        
        

            Calendar fecha = Calendar.getInstance();
            String fechaFormato = String.format("%02d/%02d/%04d",
                                                fecha.get(Calendar.DAY_OF_MONTH),
                                                fecha.get(Calendar.MONTH) +1,
                                                fecha.get(Calendar.YEAR));
            
            MovimientoGranel nuevaVenta = new MovimientoGranel(fachada.persistenciaCompras.movimientosCompras.get(clave).getProductoGranel(), clave, fechaFormato, false);
            fachada.persistenciaVentas.movimientosVentas.put(clave, nuevaVenta);
            fachada.persistenciaVentas.restarCantidad(clave, cantidad);
                        
            
            for(Map.Entry<String , ProductoGranel> entry: fachada.persistenciaInventario.productosGranel.entrySet()){
                String clavee = entry.getKey();
                ProductoGranel producto = entry.getValue();
                
                if(entry.getKey().equals(clave))
                System.out.println("Productos actualizados: " + 
                                   "\nClave: " + clavee + 
                                   "\nNombre: " + producto.getNombre() + 
                                   "\nCantidad: " + producto.getCantidad() +
                                   "\nTipo: " + producto.getTipo() + 
                                   "\nUnidad: " + producto.getUnidad());
                
            }
            
        }
        
        catch(InputMismatchException e){
            System.out.println("Dato no valido" + e);
            sc.nextLine();
        }
        catch(NullPointerException e){
            System.out.println("No se encontro el producto" + e);
        }
    }
    //22.*
    
    /*
     *El metodo consultar compra por clave primero verifica que el hashmap no este vacio para despues con la clase Scanner pedirle
     *al usuario la clave del producto granel que quiere consultar, despues verifica que se cumpla el formato con el maches y la expresion regular
     *para al final imprimirlo con un for
     */
    public void consultarCompraPorClave(){
        Scanner sc = new Scanner(System.in);
        
        try{
            if(fachada.persistenciaCompras.movimientosCompras.isEmpty()){
                System.out.println("No hay productos");
                mostrarMenu();
            }

            System.out.println("Ingrese la clave del producto que quiere consultar en el formato MM000");
            String clave = sc.next();

            if(!clave.matches("[a-zA-Z][a-zA-Z][0-9][0-9][0-9]")){
                System.out.println("La clave esta mal ingrese el formato pedido");
                return;
            }

            for(Map.Entry<String, MovimientoGranel> entry: fachada.persistenciaCompras.movimientosCompras.entrySet()){
                MovimientoGranel producto = entry.getValue();
                if(entry.getKey().equals(clave))
                System.out.println("Clave: " + clave +
                                   "\nNombre: " + producto.getProductoGranel().getNombre() +
                                   "\nCantidad: " + producto.getProductoGranel().getCantidad() +
                                   "\nFecha: " + producto.getFecha());
            } 
        }
        catch(InputMismatchException e){
            System.out.println("Ingrese un dato valido " + e);
            sc.nextLine();
        }
        catch(NullPointerException e){
            System.out.println("No existe el producto " + e);
            sc.nextLine();
        }
    }
    //25.*
    
    /*
     *El metodo mostrarInventario primero verifica que el hashmap del inventario no este vacio y si lo esta te devuelve al menu
     *despues con un foreach devuelve los valores dentro de este
     */
    public void mostrarInventario(){
        try{
            if(fachada.persistenciaInventario.productosGranel.isEmpty()){
                System.out.println("El inventario esta vacio");
                mostrarMenu();
            }

            for(Map.Entry<String, ProductoGranel> entry: fachada.persistenciaInventario.productosGranel.entrySet()){
                String clave = entry.getKey();
                ProductoGranel producto = entry.getValue();

                System.out.println("\"*****Inventario*****\"");
                System.out.println("********************" +
                                   "\nClave: " + clave + 
                                   "\nNombre: " + producto.getNombre() + 
                                   "\nUnidad: " + producto.getUnidad() + 
                                   "\nCantidad: " + producto.getCantidad());
                System.out.println("********************");
            }
        }
        catch(NullPointerException e){
            System.out.println("el producto no existe " + e);
            
        }
    }
    //26.*
    
    /*
     *EL metodo registro de compras primero verifica que el hashmap movimientosCompras no este vacio y si lo esta te regresa al menu
     *despues sup un for each para que me devuelva los valores dentro del hashmap
     */
    public void mostrarRegistroCompras(){
        try{
            if(fachada.persistenciaCompras.movimientosCompras.isEmpty()){
                System.out.println("Aun no hay compras");
                mostrarMenu();
            }

            for(Map.Entry<String, MovimientoGranel> entry: fachada.persistenciaCompras.movimientosCompras.entrySet()){
                String clave = entry.getKey();
                MovimientoGranel movimiento = entry.getValue();

                System.out.println("******Registro de compras******");
                System.out.println("*******************************" + 
                                   "\nClave: " + clave +
                                   "\nNombre: " + movimiento.getProductoGranel().getNombre() +
                                   "\nUnidad: " + movimiento.getProductoGranel().getUnidad() +
                                   "\nCantidad: " + movimiento.getProductoGranel().getCantidad());
                System.out.println("*******************************");
            }
        }
        catch(NullPointerException e){
            System.out.println("El producto no existe " + e);
        }
    }
    //27.*
    
    /*
     *El metodo mostrar registro de ventas primero verifica que no este vacio y si lo esta te regresa al menu para despues con un for 
     *devuelve los valores dentro de este y al final imprimirlos con formato
     */
    public void mostrarRegistroVentas(){
        try{
            if(fachada.persistenciaVentas.movimientosVentas.isEmpty()){
                System.out.println("No hay ventas");
                mostrarMenu();
            }

            for(Map.Entry<String, MovimientoGranel> entry: fachada.persistenciaVentas.movimientosVentas.entrySet()){
                String clave = entry.getKey();
                MovimientoGranel movimiento = entry.getValue();

                System.out.println("******Registro de ventas******");
                System.out.println("*******************************" + 
                                   "\nClave: " + clave +
                                   "\nNombre: " + movimiento.getProductoGranel().getNombre() + 
                                   "\nUnidad: " + movimiento.getProductoGranel().getUnidad() +
                                   "\nCantidad: " + movimiento.getProductoGranel().getCantidad());
                System.out.println("*******************************");
            }
        }
        catch(NullPointerException e){
            System.out.println("El objeto no existe");
        }
    }
}

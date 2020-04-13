package edu.utn.frba.dds.egreso;

import edu.utn.frba.dds.Item;

import java.util.ArrayList;

public class Egreso {
    ArrayList<Item> listaDeItems;
    Estado unEstado;

    public Egreso(){
        listaDeItems = new ArrayList<>();
        unEstado = new OperacionAbierta(listaDeItems);
    }

    public void agregarItem(Item unItem) throws RuntimeException{
        unEstado.agregarItem(unItem);
    }

    public void eliminarItem(Item unItem) throws RuntimeException{
        unEstado.eliminarItem(unItem);
    }

    public float getValor(){
        return unEstado.calcularPrecio();
    }

    public void cerrarOperacion() throws RuntimeException{
        if(!unEstado.puedeCerrarseOperacion()){
            throw new RuntimeException("La operacion de egreso no puede ser cerrada");
        }
        float precioFinal = unEstado.calcularPrecio();
        unEstado = new OperacionCerrada(precioFinal);
    }

    //Compruebo si se puede generar el remito
    public boolean puedeGenerarRemito(){
        return this.todosLosItemsSonArticulos();
    }

    public boolean todosLosItemsSonArticulos(){
        return listaDeItems.stream().allMatch(Item::esTipoArticulo);
    }
}

//Defino Estado
interface Estado{
    boolean puedeCerrarseOperacion = true;
    float calcularPrecio();
    void agregarItem(Item unItem) throws RuntimeException;
    void eliminarItem(Item unItem) throws RuntimeException;

    boolean puedeCerrarseOperacion();
}

//En caso de la operacion encontrarse abierta:
class OperacionAbierta implements Estado{
    ArrayList<Item> listaDeItems;

    OperacionAbierta(ArrayList<Item> listaDeItems){
        this.listaDeItems = listaDeItems;
    }
    @Override
    public float calcularPrecio() {
        return listaDeItems.stream().map(Item::getPrecio).reduce(0.0f, Float::sum);
    }
    @Override
    public void agregarItem(Item unItem) throws RuntimeException {

    }

    @Override
    public void eliminarItem(Item unItem) throws RuntimeException {

    }

    public boolean puedeCerrarseOperacion() {
        return true;
    }
}
//En caso de la operacion encontrarse cerrada:
class OperacionCerrada implements Estado{
    float precioFinal;

    OperacionCerrada(float precioFinal){
        this.precioFinal = precioFinal;
    }

    @Override
    public float calcularPrecio() {
        return precioFinal;
    }

    @Override
    public void agregarItem(Item unItem) throws RuntimeException {
        throw new RuntimeException("La operacion se encuentra cerrada, no puede modificarse su precio");
    }

    @Override
    public void eliminarItem(Item unItem) throws RuntimeException {
        throw new RuntimeException("La operacion se encuentra cerrada, no puede modificarse su precio");

    }
    public boolean puedeCerrarseOperacion() {
        return false;
    }
}
























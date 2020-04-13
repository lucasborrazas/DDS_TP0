package edu.utn.frba.dds.tp0;

import edu.utn.frba.dds.Item;
import edu.utn.frba.dds.egreso.Egreso;
import org.testng.annotations.AfterTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestEgreso {
    Egreso unEgreso;
    Item unItem;
    Item otroItem;

    @BeforeEach
    void paraTest() {
        unEgreso = new Egreso();
        unItem = new Item(20.5f , "articulo" );
        otroItem = new Item(75.0f, "articulo" );
        unEgreso.agregarItem(unItem);
        unEgreso.agregarItem(otroItem);
    }


    @Test
    void generacionDeRemitoOK(){
        assertTrue(unEgreso.puedeGenerarRemito(),
                "Es una operacion de compra, por ende se puede generar el remito");
    }

    @Test
    void generacionDeRemitoNegativa(){
        Item tercerItem = new Item(500.0f, "servicio");
        unEgreso.agregarItem(tercerItem);
        assertFalse(unEgreso.puedeGenerarRemito(),
                "No es una operacion de compra, no se puede generar el remito");
    }


    @Test
    void comprobandoPrecioFinal(){
        assertEquals(95.5f, unEgreso.getValor(),
            "El precio final esperado del egreso es $95.5");

    }

    @Test
    void eliminoItemYCambiaValor(){
        unEgreso.eliminarItem(otroItem);
        assertEquals(20.5f, unEgreso.getValor(),
                "El nuevo precio esperado es $20.5");
    }
    
    @Test
    void unaOperacionCerradaNoPuedeModificarSuValor(){
        unEgreso.cerrarOperacion();
        Item tercerItem = new Item(150.0f, "servicio");
        assertThrows(RuntimeException.class, () -> unEgreso.agregarItem(tercerItem),
                "La operacion se encuentra cerrada, no se permite modificar su valor");
    }
    



}
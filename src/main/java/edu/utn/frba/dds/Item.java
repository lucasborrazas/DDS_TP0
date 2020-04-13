package edu.utn.frba.dds;

public class Item {
   public float precio;
    public String tipo;

    public Item(float precio, String tipo){
        this.precio = precio;
        this.tipo = tipo;
    }

    public void setPrecio(float nuevoPrecio) {
        this.precio = nuevoPrecio;
    }

    public float getPrecio() {
        return precio;
    }

    public boolean esTipoArticulo(){
        return tipo.equals("articulo");
    }
}

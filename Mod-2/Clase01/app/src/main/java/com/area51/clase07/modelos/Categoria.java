package com.area51.clase07.modelos;

public class Categoria {
    private int id;
    private int padreId;
    private String nombre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPadreId() {
        return padreId;
    }

    public void setPadreId(int padreId) {
        this.padreId = padreId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

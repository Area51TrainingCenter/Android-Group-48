package com.area51.clase07;

import android.os.Parcel;
import android.os.Parcelable;

public class Producto implements Parcelable {
    private int id;
    private String nombre;
    private String descripcion;
    private String categoria;
    private String precio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nombre);
        dest.writeString(this.descripcion);
        dest.writeString(this.categoria);
        dest.writeString(this.precio);
    }

    public Producto() {
    }

    protected Producto(Parcel in) {
        this.id = in.readInt();
        this.nombre = in.readString();
        this.descripcion = in.readString();
        this.categoria = in.readString();
        this.precio = in.readString();
    }

    public static final Parcelable.Creator<Producto> CREATOR = new Parcelable.Creator<Producto>() {
        @Override
        public Producto createFromParcel(Parcel source) {
            return new Producto(source);
        }

        @Override
        public Producto[] newArray(int size) {
            return new Producto[size];
        }
    };
}

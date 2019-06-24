package com.area51.clase;

import android.os.Parcel;
import android.os.Parcelable;

public class Producto implements Parcelable {
    private long codigo;
    private String tipo;
    private String nombre;
    private String descripcion;
    private String rutaImagen;

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.codigo);
        dest.writeString(this.tipo);
        dest.writeString(this.nombre);
        dest.writeString(this.descripcion);
        dest.writeString(this.rutaImagen);
    }

    public Producto() {
    }

    protected Producto(Parcel in) {
        this.codigo = in.readLong();
        this.tipo = in.readString();
        this.nombre = in.readString();
        this.descripcion = in.readString();
        this.rutaImagen = in.readString();
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

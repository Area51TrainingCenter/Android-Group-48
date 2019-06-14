package com.area51.clase07.modelos;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable {
    private int id;
    private String usuario, nombre, apellido, contrasena;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.usuario);
        dest.writeString(this.nombre);
        dest.writeString(this.apellido);
        dest.writeString(this.contrasena);
    }

    public Usuario() {
    }

    protected Usuario(Parcel in) {
        this.id = in.readInt();
        this.usuario = in.readString();
        this.nombre = in.readString();
        this.apellido = in.readString();
        this.contrasena = in.readString();
    }

    public static final Parcelable.Creator<Usuario> CREATOR = new Parcelable.Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel source) {
            return new Usuario(source);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };
}

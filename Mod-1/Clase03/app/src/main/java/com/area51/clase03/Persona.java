package com.area51.clase03;

import android.os.Parcel;
import android.os.Parcelable;

public class Persona implements Parcelable {
    private String nombre,apellido,genero;
    private int edad;
    private boolean acepta;

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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public boolean isAcepta() {
        return acepta;
    }

    public void setAcepta(boolean acepta) {
        this.acepta = acepta;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nombre);
        dest.writeString(this.apellido);
        dest.writeString(this.genero);
        dest.writeInt(this.edad);
        dest.writeByte(this.acepta ? (byte) 1 : (byte) 0);
    }

    public Persona() {
    }

    protected Persona(Parcel in) {
        this.nombre = in.readString();
        this.apellido = in.readString();
        this.genero = in.readString();
        this.edad = in.readInt();
        this.acepta = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Persona> CREATOR = new Parcelable.Creator<Persona>() {
        @Override
        public Persona createFromParcel(Parcel source) {
            return new Persona(source);
        }

        @Override
        public Persona[] newArray(int size) {
            return new Persona[size];
        }
    };
}

package com.area51.clase05;

import android.os.Parcel;
import android.os.Parcelable;

public class Imagen implements Parcelable {
    private String url;
    private String nombre;

    public Imagen(String url, String nombre) {
        this.url = url;
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.nombre);
    }

    protected Imagen(Parcel in) {
        this.url = in.readString();
        this.nombre = in.readString();
    }

    public static final Parcelable.Creator<Imagen> CREATOR = new Parcelable.Creator<Imagen>() {
        @Override
        public Imagen createFromParcel(Parcel source) {
            return new Imagen(source);
        }

        @Override
        public Imagen[] newArray(int size) {
            return new Imagen[size];
        }
    };
}

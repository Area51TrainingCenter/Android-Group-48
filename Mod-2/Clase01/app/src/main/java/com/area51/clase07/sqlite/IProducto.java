package com.area51.clase07.sqlite;

import com.area51.clase07.modelos.Producto;

import java.util.ArrayList;

public interface IProducto {
    ArrayList<Producto> listar();

    long registrar(Producto producto);

    int actualizar(Producto producto);

    int eliminar(int id);
}

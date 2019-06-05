package com.area51.clase07;

import java.util.ArrayList;

public interface IProducto {
    ArrayList<Producto> listar();

    long registrar(Producto producto);

    int actualizar(Producto producto);

    int eliminar(int id);
}

package com.area51.clase07.sqlite;

import com.area51.clase07.modelos.Categoria;

import java.util.ArrayList;

public interface ICategoria {
    ArrayList<Categoria> listar();

    ArrayList<Categoria> listarPorCategoria(int codigoCategoria);
}

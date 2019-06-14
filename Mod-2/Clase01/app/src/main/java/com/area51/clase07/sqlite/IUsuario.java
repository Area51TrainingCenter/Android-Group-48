package com.area51.clase07.sqlite;

import com.area51.clase07.modelos.Usuario;

public interface IUsuario {
    Usuario validar(String usuario, String contrasena);

    long registrar(Usuario usuario);
}

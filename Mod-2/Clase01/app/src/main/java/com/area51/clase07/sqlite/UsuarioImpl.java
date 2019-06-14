package com.area51.clase07.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.area51.clase07.modelos.Usuario;

public class UsuarioImpl implements IUsuario {
    private ManageSQLite conexion;

    public UsuarioImpl(Context context) {
        conexion = new ManageSQLite(context);
    }

    @Override
    public Usuario validar(String usuario, String contrasena) {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "select * from usuario where usuario=? and contrasena=?",
                new String[]{usuario, contrasena}
        );
        if (cursor.moveToFirst()) {
            Usuario objUsuario = new Usuario();
            objUsuario.setId(
                    cursor.getInt(cursor.getColumnIndex("id")));
            objUsuario.setUsuario(
                    cursor.getString(cursor.getColumnIndex("usuario")));
            objUsuario.setNombre(
                    cursor.getString(cursor.getColumnIndex("nombre")));
            objUsuario.setApellido(
                    cursor.getString(cursor.getColumnIndex("apellido")));
            objUsuario.setContrasena(
                    cursor.getString(cursor.getColumnIndex("contrasena")));
            return objUsuario;
        }
        return null;
    }

    @Override
    public long registrar(Usuario usuario) {
        SQLiteDatabase db = conexion.getWritableDatabase();

        //Consultar usuario
        Cursor cursor = db.rawQuery(
                "select * from usuario where usuario=?",
                new String[]{usuario.getUsuario()}
        );
        if (cursor.moveToFirst()) {
            return -1;
        }

        //Registrar
        ContentValues values = new ContentValues();
        values.put("usuario", usuario.getUsuario());
        values.put("nombre", usuario.getNombre());
        values.put("apellido", usuario.getApellido());
        values.put("contrasena", usuario.getContrasena());

        return db.insert("usuario", null, values);
    }
}

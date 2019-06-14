package com.area51.clase07.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.area51.clase07.modelos.Categoria;

import java.util.ArrayList;

public class CategoriaImpl implements ICategoria {
    private ManageSQLite sqLite;

    public CategoriaImpl(Context context) {
        sqLite = new ManageSQLite(context);
    }

    @Override
    public ArrayList<Categoria> listar() {
        SQLiteDatabase db = sqLite.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "select * from categoria where padre_id=0",
                null
        );
        ArrayList<Categoria> lista = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Categoria categoria = new Categoria();
                categoria.setId(
                        cursor.getInt(cursor.getColumnIndex("id"))
                );
                categoria.setNombre(
                        cursor.getString(cursor.getColumnIndex("nombre"))
                );
                categoria.setPadreId(
                        cursor.getInt(cursor.getColumnIndex("padre_id"))
                );
                lista.add(categoria);
            } while (cursor.moveToNext());
        }
        return lista;
    }

    @Override
    public ArrayList<Categoria> listarPorCategoria(int codigoCategoria) {
        SQLiteDatabase db = sqLite.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "select * from categoria where padre_id=?",
                new String[]{String.valueOf(codigoCategoria)}
        );
        ArrayList<Categoria> lista = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Categoria categoria = new Categoria();
                categoria.setId(
                        cursor.getInt(cursor.getColumnIndex("id"))
                );
                categoria.setNombre(
                        cursor.getString(cursor.getColumnIndex("nombre"))
                );
                categoria.setPadreId(
                        cursor.getInt(cursor.getColumnIndex("padre_id"))
                );
                lista.add(categoria);
            } while (cursor.moveToNext());
        }
        return lista;
    }
}

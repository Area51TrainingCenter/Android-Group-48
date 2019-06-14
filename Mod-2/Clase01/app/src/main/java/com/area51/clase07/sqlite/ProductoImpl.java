package com.area51.clase07.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.area51.clase07.modelos.Producto;

import java.util.ArrayList;

public class ProductoImpl implements IProducto {
    private ManageSQLite sqlite;

    public ProductoImpl(Context context) {
        sqlite = new ManageSQLite(context);
    }

    @Override
    public ArrayList<Producto> listar() {
        SQLiteDatabase db = sqlite.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "select * from producto",
                null
        );
        ArrayList<Producto> lista = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Producto producto = new Producto();
                producto.setId(cursor.getInt(cursor.getColumnIndex("id")));
                producto.setNombre(cursor.getString(cursor.getColumnIndex("nombre")));
                producto.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                producto.setCategoria(cursor.getString(cursor.getColumnIndex("categoria")));
                producto.setPrecio(cursor.getString(cursor.getColumnIndex("precio")));

                lista.add(producto);
            } while (cursor.moveToNext());
        }
        return lista;
    }

    @Override
    public long registrar(Producto producto) {
        SQLiteDatabase db = sqlite.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", producto.getNombre());
        values.put("descripcion", producto.getDescripcion());
        values.put("categoria", producto.getCategoria());
        values.put("precio", producto.getPrecio());

        return db.insert("producto", null, values);
    }

    @Override
    public int actualizar(Producto producto) {
        SQLiteDatabase db = sqlite.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre", producto.getNombre());
        values.put("descripcion", producto.getDescripcion());
        values.put("categoria", producto.getCategoria());
        values.put("precio", producto.getPrecio());

        return db.update("producto", values,
                "id=?",
                new String[]{String.valueOf(producto.getId())});
    }

    @Override
    public int eliminar(int id) {
        SQLiteDatabase db = sqlite.getWritableDatabase();
        return db.delete("producto", "id=?",
                new String[]{String.valueOf(id)});
    }
}

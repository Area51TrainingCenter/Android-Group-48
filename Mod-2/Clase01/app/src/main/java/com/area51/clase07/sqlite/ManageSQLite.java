package com.area51.clase07.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.area51.clase07.modelos.Producto;

import java.util.ArrayList;

public class ManageSQLite extends SQLiteOpenHelper {
    public ManageSQLite(@Nullable Context context) {
        super(context, "clase07.db", null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table producto(" +
                        "id integer primary key autoincrement," +
                        "nombre text," +
                        "descripcion text," +
                        "categoria text," +
                        "precio decimal)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i1 <= 3) {
            sqLiteDatabase.execSQL(
                    "create table usuario(" +
                            "id integer primary key autoincrement," +
                            "usuario text," +
                            "nombre text," +
                            "apellido text," +
                            "contrasena text)"
            );
        }
        if (i1 <= 5) {
            sqLiteDatabase.execSQL(
                    "create table categoria(" +
                            "id integer primary key," +
                            "padre_id integer," +
                            "nombre text)"
            );
            for (int con = 1; con < 5; con++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("id", con);
                contentValues.put("padre_id", 0);
                contentValues.put("nombre", "Categoría " + con);
                sqLiteDatabase.insert("categoria", null, contentValues);
            }

            ContentValues contentValues1 = new ContentValues();
            contentValues1.put("id", 5);
            contentValues1.put("padre_id", 1);
            contentValues1.put("nombre", "Categoría 5");
            sqLiteDatabase.insert("categoria", null, contentValues1);

            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("id", 6);
            contentValues2.put("padre_id", 1);
            contentValues2.put("nombre", "Categoría 6");
            sqLiteDatabase.insert("categoria", null, contentValues2);

            ContentValues contentValues3 = new ContentValues();
            contentValues3.put("id", 7);
            contentValues3.put("padre_id", 2);
            contentValues3.put("nombre", "Categoría 7");
            sqLiteDatabase.insert("categoria", null, contentValues3);

            Cursor cursor = sqLiteDatabase.rawQuery(
                    "select * from producto",
                    null
            );
            ArrayList<Producto> lista = new ArrayList<>();
            if (cursor.moveToFirst()) {
                do {
                    Producto producto = new Producto();
                    producto.setId(
                            cursor.getInt(cursor.getColumnIndex("id"))
                    );
                    producto.setCategoria(
                            cursor.getString(cursor.getColumnIndex("categoria"))
                    );
                    producto.setDescripcion(
                            cursor.getString(cursor.getColumnIndex("descripcion"))
                    );
                    producto.setPrecio(
                            cursor.getString(cursor.getColumnIndex("precio"))
                    );
                    producto.setNombre(
                            cursor.getString(cursor.getColumnIndex("nombre"))
                    );
                    lista.add(producto);
                } while (cursor.moveToNext());
            }

            sqLiteDatabase.execSQL(
                    "drop table producto"
            );

            sqLiteDatabase.execSQL(
                    "create table producto(" +
                            "id integer primary key autoincrement," +
                            "nombre text," +
                            "descripcion text," +
                            "categoria integer," +
                            "precio decimal," +
                            "foreign key (categoria) references categoria(id))"
            );

            for (Producto obj : lista) {
                ContentValues valuesAgregar = new ContentValues();
                valuesAgregar.put("nombre", obj.getNombre());
                valuesAgregar.put("descripcion", obj.getDescripcion());
                valuesAgregar.put("categoria", obtenerCategoria(
                        sqLiteDatabase, obj.getCategoria()));
                valuesAgregar.put("precio", obj.getPrecio());

                sqLiteDatabase.insert("producto", null, valuesAgregar);
            }
        }
    }

    private int obtenerCategoria(SQLiteDatabase db, String categoria) {
        Cursor cursor = db.rawQuery(
                "select * from categoria where nombre=?",
                new String[]{categoria}
        );
        if (cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex("id"));
        }
        return 0;
    }
}

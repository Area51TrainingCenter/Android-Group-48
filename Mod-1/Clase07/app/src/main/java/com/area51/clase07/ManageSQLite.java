package com.area51.clase07;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class ManageSQLite extends SQLiteOpenHelper {
    public ManageSQLite(@Nullable Context context) {
        super(context, "clase07.db", null, 1);
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

    }
}

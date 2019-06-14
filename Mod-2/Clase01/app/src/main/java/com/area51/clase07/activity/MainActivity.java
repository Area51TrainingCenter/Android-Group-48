package com.area51.clase07.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.area51.clase07.modelos.Producto;
import com.area51.clase07.ProductoAdapter;
import com.area51.clase07.R;
import com.area51.clase07.sqlite.IProducto;
import com.area51.clase07.sqlite.ProductoImpl;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvLista;
    private FloatingActionButton fabAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvLista = findViewById(R.id.lvLista);
        fabAgregar = findViewById(R.id.fbAgregar);
    }

    @Override
    protected void onStart() {
        super.onStart();

        IProducto manage = new ProductoImpl(this);
        ArrayList<Producto> lista = manage.listar();

        ProductoAdapter adapter = new ProductoAdapter(this, lista);
        lvLista.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        fabAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =
                        new Intent(MainActivity.this,
                                RegistroActivity.class);
                startActivity(intent);
            }
        });
    }
}

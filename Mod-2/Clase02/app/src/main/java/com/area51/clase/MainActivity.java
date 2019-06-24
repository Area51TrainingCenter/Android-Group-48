package com.area51.clase;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.area51.clase.realm.IProducto;
import com.area51.clase.realm.ProductoEntidad;
import com.area51.clase.realm.ProductoImpl;

import java.util.ArrayList;

import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvDatos;
    private FloatingActionButton fbAgregar;
    //public static ArrayList<Producto> lista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvDatos = findViewById(R.id.rvDatos);
        fbAgregar = findViewById(R.id.fbAgregar);
    }

    @Override
    protected void onStart() {
        super.onStart();

        IProducto iProducto = new ProductoImpl();
        RealmResults<ProductoEntidad> listaProductos = iProducto.obtenerProductos();
        ArrayList<Producto> lista = new ArrayList<>();
        for (ProductoEntidad item : listaProductos) {
            Producto producto = new Producto();
            producto.setRutaImagen(item.getRutaImagen());
            producto.setDescripcion(item.getDescripcion());
            producto.setTipo(item.getTipo());
            producto.setNombre(item.getNombre());
            producto.setCodigo(item.getCodigo());
            lista.add(producto);
        }

        ProductoAdapter adapter = new ProductoAdapter(this, lista);
        rvDatos.setLayoutManager(new LinearLayoutManager(this));
        rvDatos.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        fbAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(intent);

            }
        });
    }
}

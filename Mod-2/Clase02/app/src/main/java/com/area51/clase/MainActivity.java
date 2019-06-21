package com.area51.clase;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvDatos;
    private FloatingActionButton fbAgregar;
    public static ArrayList<Producto> lista = new ArrayList<>();

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

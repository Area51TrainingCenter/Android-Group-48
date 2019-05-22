package com.area51.clase03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button agregar;
    private ListView lista;
    public static ArrayList<Persona> personas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        agregar = findViewById(R.id.btnAgregar);
        lista = findViewById(R.id.lvLista);
    }

    @Override
    protected void onStart() {
        super.onStart();

        PersonaAdapter adapter = new PersonaAdapter(this);
        lista.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(
                        MainActivity.this, RegistroActivity.class);
                startActivity(intent);

            }
        });
    }
}

package com.area51.clase03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetalleActivity extends AppCompatActivity {
    private TextView nombre, apellido, edad, genero, acepto;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Detalle");

        nombre = findViewById(R.id.tvNombre);
        apellido = findViewById(R.id.tvApellido);
        edad = findViewById(R.id.tvEdad);
        genero = findViewById(R.id.tvGenero);
        acepto = findViewById(R.id.tvAcepto);

        /* Obtenemos la información que estamos enviando desde el activity
         * anterior */
        Persona persona = getIntent().getParcelableExtra("persona");
        if (persona != null) {
            /* Cargamos los datos a los componentes donde vamos a mostrar
             * los datos obtenidos */
            nombre.setText(persona.getNombre());
            apellido.setText(persona.getApellido());
            edad.setText(String.valueOf(persona.getEdad()));
            acepto.setText(persona.isAcepta() ? "Si" : "No");
            genero.setText(persona.getGenero());
        }
    }
}

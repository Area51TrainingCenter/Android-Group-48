package com.area51.clase02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class SegundoActivity extends AppCompatActivity {
    private TextView valor1, valor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        valor1 = findViewById(R.id.tvTextoUno);
        valor2 = findViewById(R.id.tvTextoDos);

        /* Validamos que se este enviando la información a través
         * del intent y que también se esté enviando los campos de
         * valor1 y valor2 */

        if (getIntent() != null &&
                getIntent().hasExtra("valor1") &&
                getIntent().hasExtra("valor2")) {
            //Obtenemos los datos que se enviaron por el intent
            String valor1Texto = getIntent().getStringExtra("valor1");
            String valor2Texto = getIntent().getStringExtra("valor2");

            //Cambiamos los valores que se muestra en los TextView
            valor1.setText(valor1Texto);
            valor2.setText(valor2Texto);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

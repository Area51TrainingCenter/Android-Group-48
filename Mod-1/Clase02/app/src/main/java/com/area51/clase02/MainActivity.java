package com.area51.clase02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText texto1, texto2, texto1Seccion, texto2Seccion;
    private Button presionar, presionarSeccion, siguiente;
    private TextView textoLabel, textoLabelSeccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto1 = findViewById(R.id.etTextoUno);
        texto2 = findViewById(R.id.etTextoDos);
        texto1Seccion = findViewById(R.id.etTextoUnoSeccion);
        texto2Seccion = findViewById(R.id.etTextoDosSeccion);
        presionar = findViewById(R.id.btnPresionar);
        presionarSeccion = findViewById(R.id.btnPresionarSeccion);
        textoLabel = findViewById(R.id.tvTexto);
        textoLabelSeccion = findViewById(R.id.tvTextoSeccion);
        siguiente = findViewById(R.id.btnSiguiente);
    }

    @Override
    protected void onResume() {
        super.onResume();

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(
                        MainActivity.this, SegundoActivity.class);
                startActivity(intent);

            }
        });

        presionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String texto1Valor = texto1.getText().toString();
                String texto2Valor = texto2.getText().toString();

                textoLabel.setText(texto1Valor + " - " + texto2Valor);
                texto1.setText("");
                texto2.setText("");

            }
        });

        presionarSeccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String texto1Valor = texto1Seccion.getText().toString();
                String texto2Valor = texto2Seccion.getText().toString();

                textoLabelSeccion.setText(texto1Valor + " " + texto2Valor);
                texto1Seccion.setText("");
                texto2Seccion.setText("");

            }
        });
    }
}

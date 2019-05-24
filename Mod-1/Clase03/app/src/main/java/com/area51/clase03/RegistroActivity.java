package com.area51.clase03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {
    private EditText nombre, apellido;
    private Spinner edad;
    private RadioButton masculino, femenino;
    private CheckBox aceptar;
    private Button guardar;
    int posicion;
    Persona personaObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombre = findViewById(R.id.etNombre);
        apellido = findViewById(R.id.etApellido);
        edad = findViewById(R.id.spEdad);
        masculino = findViewById(R.id.rbMasculino);
        femenino = findViewById(R.id.rbFemenino);
        aceptar = findViewById(R.id.cbAceptar);
        guardar = findViewById(R.id.btnGuardar);

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item,
                getResources().getStringArray(R.array.edades));
        edad.setAdapter(adapter);

        if (getIntent() != null) {
            if (getIntent().hasExtra("persona")) {
                personaObj = getIntent().getParcelableExtra("persona");
                posicion = getIntent().getIntExtra("posicion", 0);
                if (personaObj != null) {
                    nombre.setText(personaObj.getNombre());
                    apellido.setText(personaObj.getApellido());
                    int i = 0;
                    for (String itemEdad : getResources().getStringArray(R.array.edades)) {
                        if (itemEdad.equals(String.valueOf(personaObj.getEdad()))) {
                            edad.setSelection(i);
                            break;
                        }
                        i++;
                    }
                    if (personaObj.getGenero().equals("Masculino")) {
                        masculino.setChecked(true);
                    } else {
                        femenino.setChecked(true);
                    }
                    aceptar.setChecked(personaObj.isAcepta());
                }
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String valorNombre = nombre.getText().toString();
                String valorApellido = apellido.getText().toString();
                String valorEdad = edad.getSelectedItem().toString();
                String valorGenero =
                        masculino.isChecked() ? "Masculino" :
                                femenino.isChecked() ? "Femenino" : "";
                boolean valorAceptar = aceptar.isChecked();

                /* Agregamos los datos obtenidos de los componentes
                 * al objeto de tipo persona*/
                Persona persona = new Persona();
                persona.setNombre(valorNombre);
                persona.setApellido(valorApellido);
                persona.setEdad(Integer.parseInt(valorEdad));
                persona.setGenero(valorGenero);
                persona.setAcepta(valorAceptar);

                if (personaObj != null) {
                    MainActivity.personas.get(posicion).setNombre(valorNombre);
                    MainActivity.personas.get(posicion).setApellido(valorApellido);
                    MainActivity.personas.get(posicion).setGenero(valorGenero);
                    MainActivity.personas.get(posicion).setEdad(Integer.parseInt(valorEdad));
                    MainActivity.personas.get(posicion).setAcepta(valorAceptar);

                    Toast.makeText(RegistroActivity.this,
                            "Se modifico correctamente",
                            Toast.LENGTH_SHORT).show();

                    finish();
                } else {
                    //Agregar el objeto a la lista
                    MainActivity.personas.add(persona);

                    Toast.makeText(RegistroActivity.this,
                            "Se agrego correctamente",
                            Toast.LENGTH_SHORT).show();

                    finish();
                }

            }
        });
    }
}

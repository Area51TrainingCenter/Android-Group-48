package com.area51.clase06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {
    private EditText etNombre, etDescripcion, etPrecio;
    private Spinner spCategoria;
    private Button btnGuardar;
    private Producto producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etNombre = findViewById(R.id.etNombre);
        etDescripcion = findViewById(R.id.etDescripcion);
        etPrecio = findViewById(R.id.etPrecio);
        spCategoria = findViewById(R.id.spCatgoria);
        btnGuardar = findViewById(R.id.btnAgregar);

        if (getIntent() != null) {
            if (getIntent().hasExtra("item")) {
                producto = getIntent().getParcelableExtra("item");
                etNombre.setText(producto.getNombre());
                etDescripcion.setText(producto.getDescripcion());
                etPrecio.setText(String.valueOf(producto.getPrecio()));
                String[] categorias = getResources().getStringArray(R.array.categoria);
                for (int i = 0; i < categorias.length; i++) {
                    if (producto.getCategoria().equals(categorias[i])) {
                        spCategoria.setSelection(i);
                        break;
                    }
                }
                btnGuardar.setText("Actualizar");
            }
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

    @Override
    protected void onResume() {
        super.onResume();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre = etNombre.getText().toString();
                String descripcion = etDescripcion.getText().toString();
                Double precio = Double.parseDouble(etPrecio.getText().toString());
                String categoria = spCategoria.getSelectedItem().toString();

                if (producto == null) {
                    Producto producto = new Producto();
                    producto.setNombre(nombre);
                    producto.setDescripcion(descripcion);
                    producto.setPrecio(precio);
                    producto.setCategoria(categoria);

                    ListadoFragment.lista.add(producto);
                    Toast.makeText(RegistroActivity.this,
                            "Se registro", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    int posicion = getIntent().getIntExtra("posicion", 0);

                    ListadoFragment.lista.get(posicion).setNombre(nombre);
                    ListadoFragment.lista.get(posicion).setDescripcion(descripcion);
                    ListadoFragment.lista.get(posicion).setPrecio(precio);
                    ListadoFragment.lista.get(posicion).setCategoria(categoria);

                    Toast.makeText(RegistroActivity.this,
                            "Se actualizo", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });
    }
}

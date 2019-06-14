package com.area51.clase07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {
    private EditText etNombre, etDescripcion, etPrecio;
    private Spinner spCategoria;
    private Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombre = findViewById(R.id.etNombre);
        etDescripcion = findViewById(R.id.etDescripcion);
        etPrecio = findViewById(R.id.etPrecio);
        btnAgregar = findViewById(R.id.btnRegistrar);
        spCategoria = findViewById(R.id.spCategoria);

        btnAgregar.setTag(0);

        if (getIntent().hasExtra("item")) {
            Producto producto = getIntent().getParcelableExtra("item");
            if (producto != null) {
                etNombre.setText(producto.getNombre());
                etDescripcion.setText(producto.getDescripcion());
                etPrecio.setText(producto.getPrecio());
                String[] categorias = getResources().getStringArray(R.array.categorias);
                for (int i = 0; i < categorias.length; i++) {
                    if (producto.getCategoria().equals(categorias[i])) {
                        spCategoria.setSelection(i);
                        break;
                    }
                }
                btnAgregar.setText("Modificar");
                btnAgregar.setTag(producto.getId());
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etNombre.getText().toString();
                String descripcion = etDescripcion.getText().toString();
                String precio = etPrecio.getText().toString();
                String categoria = spCategoria.getSelectedItem().toString();

                Producto producto = new Producto();
                producto.setNombre(nombre);
                producto.setDescripcion(descripcion);
                producto.setPrecio(precio);
                producto.setCategoria(categoria);

                IProducto manage = new ProductoImpl(RegistroActivity.this);

                int codigo = (int) btnAgregar.getTag();
                if (codigo == 0) {
                    long esOk = manage.registrar(producto);
                    if (esOk >= 1) {
                        Toast.makeText(RegistroActivity.this,
                                "Se registro", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(RegistroActivity.this,
                                "Ocurrio un problema", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    producto.setId(codigo);
                    long esOk = manage.actualizar(producto);
                    if (esOk >= 1) {
                        Toast.makeText(RegistroActivity.this,
                                "Se actualizo", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(RegistroActivity.this,
                                "Ocurrio un error", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

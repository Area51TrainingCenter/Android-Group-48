package com.area51.clase;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.area51.clase.realm.IProducto;
import com.area51.clase.realm.ProductoEntidad;
import com.area51.clase.realm.ProductoImpl;

public class RegistroActivity extends AppCompatActivity {
    private AppCompatSpinner spTipo;
    private TextInputLayout tilNombre, tilDescripcion;
    private EditText etNombre, etDescripcion;
    private ImageView ivImagen1, ivImagen2, ivImagen3;
    private Button btnGrabar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        spTipo = findViewById(R.id.spTipo);
        tilNombre = findViewById(R.id.tilNombre);
        tilDescripcion = findViewById(R.id.tilDescripcion);
        etNombre = findViewById(R.id.etNombre);
        etDescripcion = findViewById(R.id.etDescripcion);
        ivImagen1 = findViewById(R.id.ivImagen1);
        ivImagen2 = findViewById(R.id.ivImagen2);
        ivImagen3 = findViewById(R.id.ivImagen3);
        btnGrabar = findViewById(R.id.btnGrabar);
    }

    private String imagenSeleccionada = "";

    @Override
    protected void onResume() {
        super.onResume();

        ivImagen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenSeleccionada = "imagen1";
            }
        });
        ivImagen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenSeleccionada = "imagen2";
            }
        });
        ivImagen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imagenSeleccionada = "imagen3";
            }
        });

        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String tipo = spTipo.getSelectedItem().toString();
                String nombre = etNombre.getText().toString();
                String descripcion = etDescripcion.getText().toString();

                if (nombre.equals("")) {
                    tilNombre.setError("Campo requerido");
                    return;
                } else {
                    tilNombre.setError(null);
                }

                if (descripcion.equals("")) {
                    tilDescripcion.setError("Campo requerido");
                    return;
                } else {
                    tilDescripcion.setError(null);
                }

                if (imagenSeleccionada.equals("")) {
                    Toast.makeText(RegistroActivity.this,
                            "Seleccione una imagen", Toast.LENGTH_SHORT).show();
                    return;
                }

                ProductoEntidad producto = new ProductoEntidad();
                producto.setNombre(nombre);
                producto.setTipo(tipo);
                producto.setDescripcion(descripcion);

                if (imagenSeleccionada.equals("imagen1")) {
                    producto.setRutaImagen("res:/" + R.drawable.ic_imagen1);
                } else if (imagenSeleccionada.equals("imagen2"))
                    producto.setRutaImagen("res:/" + R.drawable.ic_imagen2);
                else if (imagenSeleccionada.equals("imagen3"))
                    producto.setRutaImagen("res:/" + R.drawable.ic_imagen3);

                IProducto iProducto = new ProductoImpl();
                ProductoEntidad resultado = iProducto.guardar(producto);
                if (resultado != null && resultado.getCodigo() > 0) {
                    //MainActivity.lista.add(producto);
                    Toast.makeText(RegistroActivity.this,
                            "Se agrego el producto", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RegistroActivity.this,
                            "Ocurrio un error", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}

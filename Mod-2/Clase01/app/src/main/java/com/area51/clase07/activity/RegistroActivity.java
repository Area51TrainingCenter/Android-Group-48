package com.area51.clase07.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.area51.clase07.modelos.Categoria;
import com.area51.clase07.modelos.Producto;
import com.area51.clase07.R;
import com.area51.clase07.sqlite.CategoriaImpl;
import com.area51.clase07.sqlite.ICategoria;
import com.area51.clase07.sqlite.IProducto;
import com.area51.clase07.sqlite.ProductoImpl;

import java.util.ArrayList;

public class RegistroActivity extends AppCompatActivity {
    private EditText etNombre, etDescripcion, etPrecio;
    private Spinner spCategoria, spSubCategoria;
    private Button btnAgregar;
    ArrayList<Categoria> listaCategorias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etNombre = findViewById(R.id.etNombre);
        etDescripcion = findViewById(R.id.etDescripcion);
        etPrecio = findViewById(R.id.etPrecio);
        btnAgregar = findViewById(R.id.btnRegistrar);
        spCategoria = findViewById(R.id.spCategoria);
        spSubCategoria = findViewById(R.id.spSubCategoria);

        ICategoria iCategoria = new CategoriaImpl(RegistroActivity.this);
        listaCategorias = iCategoria.listar();
        ArrayList<String> categoriasTexto = new ArrayList<>();
        for (Categoria categoria : listaCategorias) {
            categoriasTexto.add(categoria.getNombre());
        }
        ArrayAdapter adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                categoriasTexto
        );
        spCategoria.setAdapter(adapter);


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

        spCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Categoria categoria = listaCategorias.get(i);

                ICategoria iCategoria = new CategoriaImpl(RegistroActivity.this);
                ArrayList<Categoria> subCategorias =
                        iCategoria.listarPorCategoria(categoria.getId());
                ArrayList<String> subCategoriaTexto = new ArrayList<>();
                for (Categoria obj : subCategorias) {
                    subCategoriaTexto.add(obj.getNombre());
                }
                ArrayAdapter adapter = new ArrayAdapter(
                        RegistroActivity.this,
                        android.R.layout.simple_spinner_dropdown_item,
                        subCategoriaTexto
                );
                spSubCategoria.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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

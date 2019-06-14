package com.area51.clase07.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.area51.clase07.R;
import com.area51.clase07.modelos.Usuario;
import com.area51.clase07.sqlite.IUsuario;
import com.area51.clase07.sqlite.UsuarioImpl;

public class RegistroUsuarioActivity extends AppCompatActivity {
    private EditText etUsuario, etNombre, etApellido,
            etContrasena, etRepetirContrasena;
    private Button btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        etUsuario = findViewById(R.id.etUsuario);
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etContrasena = findViewById(R.id.etContrasena);
        etRepetirContrasena = findViewById(R.id.etRepetirContrasena);
        btnRegistro = findViewById(R.id.btnRegistrar);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usuario = etUsuario.getText().toString();
                String nombre = etNombre.getText().toString();
                String apellido = etApellido.getText().toString();
                String contrasena = etContrasena.getText().toString();
                String repContrasena = etRepetirContrasena.getText().toString();

                if (!contrasena.equals(repContrasena)) {
                    Toast.makeText(RegistroUsuarioActivity.this,
                            "Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    return;
                }

                Usuario obj = new Usuario();
                obj.setUsuario(usuario);
                obj.setNombre(nombre);
                obj.setApellido(apellido);
                obj.setContrasena(contrasena);

                IUsuario sql = new UsuarioImpl(RegistroUsuarioActivity.this);
                long resultado = sql.registrar(obj);
                if (resultado >= 1) {
                    Toast.makeText(RegistroUsuarioActivity.this,
                            "Usuario registrado", Toast.LENGTH_SHORT).show();
                    finish();
                } else if (resultado == -1) {
                    Toast.makeText(RegistroUsuarioActivity.this,
                            "Usuario ya existe", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegistroUsuarioActivity.this,
                            "Ocurrio un error de registro", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}

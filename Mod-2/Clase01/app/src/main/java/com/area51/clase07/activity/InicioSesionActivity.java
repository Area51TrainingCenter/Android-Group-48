package com.area51.clase07.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.area51.clase07.R;
import com.area51.clase07.modelos.Usuario;
import com.area51.clase07.sqlite.IUsuario;
import com.area51.clase07.sqlite.UsuarioImpl;

public class InicioSesionActivity extends AppCompatActivity {
    private EditText etUsuario, etContrasena;
    private Button btnInicio;
    private TextView tvRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        etUsuario = findViewById(R.id.etUsuario);
        etContrasena = findViewById(R.id.etContrasena);
        btnInicio = findViewById(R.id.btnInicio);
        tvRegistro = findViewById(R.id.tvRegistro);
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usuario = etUsuario.getText().toString();
                String contrasena = etContrasena.getText().toString();

                IUsuario sql = new UsuarioImpl(InicioSesionActivity.this);
                Usuario respuesta = sql.validar(usuario, contrasena);
                if (respuesta == null) {
                    Toast.makeText(InicioSesionActivity.this,
                            "Usuario y/o contraseña invalidos",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(InicioSesionActivity.this,
                            MainActivity.class);
                    intent.putExtra("item", respuesta);
                    startActivity(intent);
                }

            }
        });

        tvRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        InicioSesionActivity.this,
                        RegistroUsuarioActivity.class);
                startActivity(intent);
            }
        });
    }
}

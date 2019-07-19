package pe.area51.clasews

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import pe.area51.clasews.rest.MetodoRetrofit
import pe.area51.clasews.rest.RetrofitHelper
import pe.area51.clasews.rest.response.UsuarioResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun onResume() {
        super.onResume()

        tvRegistro.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    RegistroActivity::class.java
                )
            )
        }

        btnIniciarSesion.setOnClickListener {
            val usuario = etUsuario.text.toString()
            val contrasenia = etContrasena.text.toString()

            val retrofit = RetrofitHelper.obtenerConfiguracion()
                .create(MetodoRetrofit::class.java)
            val call = retrofit.validarUsuario(usuario, contrasenia)
            call.enqueue(object : Callback<UsuarioResponse> {
                override fun onFailure(
                    call: Call<UsuarioResponse>,
                    t: Throwable
                ) {
                    Log.d("tag_clasews", "error ${t.message}")
                }

                override fun onResponse(
                    call: Call<UsuarioResponse>,
                    response: Response<UsuarioResponse>
                ) {

                    Log.d(
                        "tag_clasews", "respuesta->" +
                                Gson().toJson(response.body())
                    )

                    val resultado = response.body()
                    if (resultado!!.status) {

                        val preferences = getSharedPreferences(
                            "clasews", Context.MODE_PRIVATE
                        ).edit()
                        preferences.putInt("id",resultado.data.id)
                        preferences.putString("usuario", resultado.data.username)
                        preferences.putString("nombre", resultado.data.name)
                        preferences.putString("apellido", resultado.data.lastName)
                        preferences.putString("genero", resultado.data.gender)
                        preferences.apply()

                        val intent=Intent(this@MainActivity,
                                HomeActivity::class.java)
                        startActivity(intent)

                        Toast.makeText(
                            this@MainActivity,
                            "Inicio de sesion correcto",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Usuario y contrasena incorrectos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }

            })
        }
    }
}

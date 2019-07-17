package pe.area51.clasews

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registro.*
import pe.area51.clasews.rest.MetodoRetrofit
import pe.area51.clasews.rest.RetrofitHelper
import pe.area51.clasews.rest.request.UsuarioRequest
import pe.area51.clasews.rest.response.UsuarioResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
    }

    override fun onResume() {
        super.onResume()

        btnGuardar.setOnClickListener {
            val usuario = etUsuario.text.toString()
            val nombre = etNombre.text.toString()
            val apellido = etApellido.text.toString()
            val genero = spGenero.selectedItem.toString()
            val contrasena = etContrasena.text.toString()

            val request = UsuarioRequest()
            request.name = nombre
            request.gender = genero
            request.lastName = apellido
            request.password = contrasena
            request.username = usuario

            val retrofit = RetrofitHelper.obtenerConfiguracion()
                    .create(MetodoRetrofit::class.java)
            val call = retrofit.guardarUsuario(request)
            call.enqueue(object : Callback<UsuarioResponse> {
                override fun onFailure(call: Call<UsuarioResponse>, t: Throwable) {
                    Log.d("testws", t.message)
                }

                override fun onResponse(call: Call<UsuarioResponse>, response: Response<UsuarioResponse>) {
                    val resultado = response.body()
                    if (resultado!!.status) {
                        finish()
                        Toast.makeText(this@RegistroActivity,
                                "Se registro", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@RegistroActivity,
                                "Ocurrio un error", Toast.LENGTH_SHORT).show()
                    }
                }

            })
        }
    }

}

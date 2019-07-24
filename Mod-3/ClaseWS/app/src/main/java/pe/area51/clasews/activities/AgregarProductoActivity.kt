package pe.area51.clasews.activities

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_agregar_producto.*
import pe.area51.clasews.R
import pe.area51.clasews.rest.MetodoRetrofit
import pe.area51.clasews.rest.RetrofitHelper
import pe.area51.clasews.rest.request.ProductRequest
import pe.area51.clasews.rest.response.Datum
import pe.area51.clasews.rest.response.ProductoObjectResponse
import pe.area51.clasews.rest.response.ProductoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgregarProductoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_producto)
    }

    override fun onResume() {
        super.onResume()

        btnGuardar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val descripcion = etDescripcion.text.toString()
            val preferences = getSharedPreferences(
                    "clasews", Context.MODE_PRIVATE)
            val usuarioId = preferences.getInt("id", 0)

            val retrofit = RetrofitHelper.obtenerConfiguracion()
                    .create(MetodoRetrofit::class.java)

            val request = ProductRequest()
            request.name = nombre
            request.description = descripcion
            request.idUser = usuarioId

            val call = retrofit.guardarProducto(request)
            call.enqueue(object : Callback<ProductoObjectResponse> {
                override fun onFailure(call: Call<ProductoObjectResponse>, t: Throwable) {
                    Log.d("AgregarProductoLog", t.message)
                }

                override fun onResponse(call: Call<ProductoObjectResponse>,
                                        response: Response<ProductoObjectResponse>) {
                    val respuesta = response.body()
                    if (respuesta != null) {
                        if (respuesta.status) {
                            finish()
                            Toast.makeText(
                                    this@AgregarProductoActivity,
                                    "Se registro",
                                    Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })
        }
    }
}

package pe.area51.clasews

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
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

        val retrofit = RetrofitHelper.obtenerConfiguracion()
            .create(MetodoRetrofit::class.java)
        val call = retrofit.validarUsuario("johannfjs", "123")
        call.enqueue(object : Callback<UsuarioResponse> {
            override fun onFailure(
                call: Call<UsuarioResponse>,
                t: Throwable
            ) {
                Log.d("tag_clasews","error ${t.message}")
            }

            override fun onResponse(
                call: Call<UsuarioResponse>,
                response: Response<UsuarioResponse>
            ) {

                Log.d("tag_clasews", "respuesta->" +
                        Gson().toJson(response.body()))

            }

        })
    }
}

package pe.area51.clasews.fragmentos


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_listado_producto.*
import pe.area51.clasews.R
import pe.area51.clasews.activities.AgregarProductoActivity
import pe.area51.clasews.adapters.ProductoAdapter
import pe.area51.clasews.rest.MetodoRetrofit
import pe.area51.clasews.rest.RetrofitHelper
import pe.area51.clasews.rest.response.Datum
import pe.area51.clasews.rest.response.ProductoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ListadoProductoFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_listado_producto, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onStart() {
        super.onStart()

        val retrofit = RetrofitHelper.obtenerConfiguracion()
                .create(MetodoRetrofit::class.java)

        val preferences = activity!!.getSharedPreferences(
                "clasews", Context.MODE_PRIVATE
        )

        val call = retrofit.obtenerProductos(preferences.getInt("id", 0))
        call.enqueue(object : Callback<ProductoResponse> {
            override fun onFailure(call: Call<ProductoResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<ProductoResponse>,
                                    response: Response<ProductoResponse>) {

                val respuesta = response.body()
                if (respuesta != null) {
                    if (respuesta.status) {
                        val adapter = ProductoAdapter(
                                respuesta.data as ArrayList<Datum>, activity!!)
                        rvData.layoutManager = LinearLayoutManager(activity!!)
                        rvData.adapter = adapter
                    }
                }

            }
        })
    }

    override fun onResume() {
        super.onResume()

        fabAgregar.setOnClickListener {
            val intent = Intent(activity!!, AgregarProductoActivity::class.java)
            startActivity(intent)
        }
    }
}

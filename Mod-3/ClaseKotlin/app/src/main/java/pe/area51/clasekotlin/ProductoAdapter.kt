package pe.area51.clasekotlin

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.item_producto.view.*

class ProductoAdapter(val context: Context, val lista: ArrayList<Producto>) : BaseAdapter() {
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.item_producto, p2, false)
        val producto = getItem(p0) as Producto
        view.tvProducto.text = producto.producto
        view.tvCantidad.text = producto.cantidad.toString()
        view.tvPrecio.text = producto.precio.toString()

        view.contenedor.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Escoja una opcion")
            builder.setPositiveButton("Actualizar") { dialogInterface: DialogInterface, i: Int ->
                val intent = Intent(context, RegistroActivity::class.java)
                intent.putExtra("producto", producto)
                intent.putExtra("posicion", p0)
                context.startActivity(intent)
            }
            builder.setNegativeButton("Eliminar") { dialogInterface: DialogInterface, i: Int ->
                lista.remove(producto)
                notifyDataSetChanged()
            }
            builder.show()
        }

        return view
    }

    override fun getItem(p0: Int): Any {
        return lista[p0]

    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return lista.size
    }
}
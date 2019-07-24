package pe.area51.clasews.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_producto.view.*
import pe.area51.clasews.R
import pe.area51.clasews.rest.response.Datum

class ProductoAdapter(val lista: ArrayList<Datum>,
                      val context: Context) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ProductoViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
        fun bindView(producto: Datum) {
            itemView.tvNombre.text = producto.name
            itemView.tvDescripcion.text = producto.description
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int):
            RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.item_producto, p0, false)
        return ProductoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        val viewHolder = p0 as ProductoViewHolder
        viewHolder.bindView(lista[p1])
    }
}
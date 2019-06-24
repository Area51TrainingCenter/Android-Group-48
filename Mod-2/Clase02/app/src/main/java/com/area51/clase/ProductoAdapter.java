package com.area51.clase;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.area51.clase.realm.IProducto;
import com.area51.clase.realm.ProductoImpl;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

public class ProductoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Producto> lista;

    public ProductoAdapter(Context context, ArrayList<Producto> lista) {
        this.context = context;
        this.lista = lista;
    }

    class ProductoViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView ivImagen;
        TextView tvTipo, tvNombre, tvDescripcion;
        TextView tvModificar, tvEliminar;

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.ivImagen);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvTipo = itemView.findViewById(R.id.tvTipo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvModificar = itemView.findViewById(R.id.tvModificar);
            tvEliminar = itemView.findViewById(R.id.tvEliminar);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.item_producto, viewGroup, false);
        return new ProductoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ProductoViewHolder productoViewHolder = (ProductoViewHolder) viewHolder;

        final Producto producto = lista.get(i);
        productoViewHolder.tvNombre.setText(producto.getNombre());
        productoViewHolder.tvDescripcion.setText(producto.getDescripcion());
        productoViewHolder.ivImagen.setImageURI(Uri.parse(producto.getRutaImagen()));
        productoViewHolder.tvTipo.setText(producto.getTipo());

        productoViewHolder.tvModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RegistroActivity.class);
                intent.putExtra("item", producto);
                context.startActivity(intent);
            }
        });
        productoViewHolder.tvEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IProducto iProducto = new ProductoImpl();
                iProducto.eliminar(producto.getCodigo());
                lista.remove(producto);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}

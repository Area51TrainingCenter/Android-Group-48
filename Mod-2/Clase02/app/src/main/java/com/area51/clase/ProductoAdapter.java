package com.area51.clase;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        public ProductoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.ivImagen);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvTipo = itemView.findViewById(R.id.tvTipo);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
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

        Producto producto = lista.get(i);
        productoViewHolder.tvNombre.setText(producto.getNombre());
        productoViewHolder.tvDescripcion.setText(producto.getDescripcion());
        productoViewHolder.ivImagen.setImageURI(Uri.parse(producto.getRutaImagen()));
        productoViewHolder.tvTipo.setText(producto.getTipo());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}

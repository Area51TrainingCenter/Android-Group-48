package com.area51.clase06;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductoAdapter extends BaseAdapter {
    private ArrayList<Producto> lista;
    private Context context;

    public ProductoAdapter(ArrayList<Producto> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int posicion, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context)
                .inflate(R.layout.item_producto, viewGroup, false);

        TextView nombre, descripcion;
        LinearLayout contenedor;

        nombre = view.findViewById(R.id.tvTitulo);
        descripcion = view.findViewById(R.id.tvDescripcion);
        contenedor = view.findViewById(R.id.contenedor);

        final Producto producto = (Producto) getItem(posicion);

        nombre.setText(producto.getNombre());
        descripcion.setText(producto.getDescripcion());
        contenedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder =
                        new AlertDialog.Builder(context);
                builder.setTitle("Opciones");
                builder.setMessage("Seleccione una opción");
                builder.setPositiveButton("Modificar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int ie) {

                                Intent intent = new Intent(context, RegistroActivity.class);
                                intent.putExtra("item", producto);
                                intent.putExtra("posicion", posicion);
                                context.startActivity(intent);

                            }
                        });
                builder.setNegativeButton("Eliminar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                lista.remove(producto);
                                notifyDataSetChanged();
                            }
                        });
                builder.show();

            }
        });

        return view;
    }
}

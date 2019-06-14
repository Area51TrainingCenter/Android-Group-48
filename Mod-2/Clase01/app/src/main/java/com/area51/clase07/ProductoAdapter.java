package com.area51.clase07;

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

import com.area51.clase07.activity.RegistroActivity;
import com.area51.clase07.modelos.Producto;
import com.area51.clase07.sqlite.IProducto;
import com.area51.clase07.sqlite.ProductoImpl;

import java.util.ArrayList;

public class ProductoAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Producto> lista;

    public ProductoAdapter(Context context, ArrayList<Producto> lista) {
        this.context = context;
        this.lista = lista;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context)
                .inflate(R.layout.item_producto, viewGroup, false);

        TextView nombre, categoria, precio;
        LinearLayout contenedor;

        nombre = view.findViewById(R.id.tvNombre);
        categoria = view.findViewById(R.id.tvCategoria);
        precio = view.findViewById(R.id.tvPrecio);
        contenedor = view.findViewById(R.id.contenedor);

        final Producto producto = (Producto) getItem(i);
        nombre.setText(producto.getNombre());
        categoria.setText(producto.getCategoria());
        precio.setText("S/ " + producto.getPrecio());
        contenedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Opciones");
                builder.setMessage("Seleccione una");
                builder.setPositiveButton("Modificar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(context, RegistroActivity.class);
                                intent.putExtra("item", producto);
                                context.startActivity(intent);
                            }
                        });
                builder.setNegativeButton("Eliminar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                AlertDialog.Builder builderOpcion = new AlertDialog.Builder(context);
                                builderOpcion.setTitle("Pregunta");
                                builderOpcion.setMessage("Está seguro de eliminar");
                                builderOpcion.setPositiveButton("Si",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                IProducto manage = new ProductoImpl(context);
                                                manage.eliminar(producto.getId());
                                                lista.remove(producto);
                                                notifyDataSetChanged();
                                            }
                                        });
                                builderOpcion.setNegativeButton("No",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                            }
                                        });
                                builderOpcion.show();
                            }
                        });
                builder.show();
            }
        });

        return view;
    }
}

package com.area51.clase05;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class ImagenAdapter extends ArrayAdapter<Imagen> {
    private Context context;
    private ArrayList<Imagen> lista;

    public ImagenAdapter(@NonNull Context context,
                         int resource, @NonNull ArrayList<Imagen> objects) {
        super(context, resource, objects);
        this.context = context;
        this.lista = objects;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Nullable
    @Override
    public Imagen getItem(int position) {
        return lista.get(position);
    }

    @NonNull
    @Override
    public View getView(final int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context)
                .inflate(R.layout.item, parent, false);

        final Imagen imagen = getItem(position);

        SimpleDraweeView sdvImagen = convertView.findViewById(R.id.sdvImagen);
        TextView tvDescripcion = convertView.findViewById(R.id.tvDescripcion);
        FrameLayout contenedor = convertView.findViewById(R.id.contenedor);

        sdvImagen.setImageURI(Uri.parse(imagen.getUrl()));
        tvDescripcion.setText(imagen.getNombre());
        contenedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, DetalleActivity.class);
                intent.putExtra("lista", lista);
                intent.putExtra("item", imagen);
                intent.putExtra("posicion", position);
                context.startActivity(intent);

            }
        });

        return convertView;
    }
}

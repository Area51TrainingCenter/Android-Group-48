package com.area51.clase03;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PersonaAdapter extends BaseAdapter {
    private Context context;

    public PersonaAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return MainActivity.personas.size();
    }

    @Override
    public Object getItem(int i) {
        return MainActivity.personas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int posicion, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context)
                .inflate(R.layout.item_persona, viewGroup, false);

        //Balsamiq Mockups
        //Adobe XD, Sketch, Figma, MarvelApp

        TextView nombre, apellido, edad;
        LinearLayout contenedor;

        nombre = view.findViewById(R.id.tvNombre);
        apellido = view.findViewById(R.id.tvApellido);
        edad = view.findViewById(R.id.tvEdad);
        contenedor = view.findViewById(R.id.contenedor);

        final Persona persona = (Persona) getItem(posicion);
        nombre.setText(persona.getNombre());
        apellido.setText(persona.getApellido());
        edad.setText(String.valueOf(persona.getEdad()));
        contenedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("Acción");
                dialog.setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(context, RegistroActivity.class);
                        intent.putExtra("persona", persona);
                        intent.putExtra("posicion", posicion);
                        context.startActivity(intent);

                    }
                });
                dialog.setNegativeButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //Quitamos el elemento de la lista
                        MainActivity.personas.remove(posicion);
                        //Notificamos el cambio al adapter
                        notifyDataSetChanged();

                    }
                });
                dialog.setNeutralButton("Detalle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(context, DetalleActivity.class);
                        intent.putExtra("persona", persona);
                        context.startActivity(intent);

                    }
                });
                dialog.show();

            }
        });

        return view;
    }
}

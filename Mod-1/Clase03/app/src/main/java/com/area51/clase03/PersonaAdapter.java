package com.area51.clase03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context)
                .inflate(R.layout.item_persona, viewGroup, false);

        //Balsamiq Mockups
        //Adobe XD, Sketch, Figma, MarvelApp

        TextView nombre, apellido, edad;
        nombre = view.findViewById(R.id.tvNombre);
        apellido = view.findViewById(R.id.tvApellido);
        edad = view.findViewById(R.id.tvEdad);

        Persona persona = (Persona) getItem(i);
        nombre.setText(persona.getNombre());
        apellido.setText(persona.getApellido());
        edad.setText(String.valueOf(persona.getEdad()));

        return view;
    }
}

package com.area51.clase06;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListadoFragment extends Fragment {

    private ListView lvLista;
    private FloatingActionButton fabAgregar;
    public static ArrayList<Producto> lista = new ArrayList<>();

    public ListadoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();

        //llamamos al adapter
        ProductoAdapter adapter = new ProductoAdapter(lista, getActivity());
        lvLista.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_listado, container, false);

        lvLista = view.findViewById(R.id.lvLista);
        fabAgregar = view.findViewById(R.id.fbAgregar);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        fabAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RegistroActivity.class);
                startActivity(intent);
            }
        });
    }
}

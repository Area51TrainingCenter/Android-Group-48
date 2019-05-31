package com.area51.clase05;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImagenFragment extends Fragment {
    private SimpleDraweeView sdvImagen;
    private TextView tvTexto;

    public ImagenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_imagen, container, false);

        sdvImagen = view.findViewById(R.id.sdvImagen);
        tvTexto = view.findViewById(R.id.tvTexto);

        Imagen imagen = getArguments().getParcelable("item");
        if (imagen != null) {
            sdvImagen.setImageURI(Uri.parse(imagen.getUrl()));
            tvTexto.setText(imagen.getNombre());
            //getActivity().setTitle(imagen.getNombre());
        }

        return view;
    }

}

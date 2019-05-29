package com.area51.clase05;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ImagenDetalleAdapter extends FragmentPagerAdapter {
    private ArrayList<Imagen> lista;

    public ImagenDetalleAdapter(FragmentManager fm, ArrayList<Imagen> lista) {
        super(fm);
        this.lista = lista;
    }

    @Override
    public Fragment getItem(int i) {
        ImagenFragment fragment = new ImagenFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable("item", lista.get(i));
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return lista.size();
    }
}

package com.area51.clase05;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

public class DetalleActivity extends AppCompatActivity {
    private Imagen imagen;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = findViewById(R.id.viewPager);


        if (getIntent() != null) {
            if (getIntent().hasExtra("item")) {
                imagen = getIntent().getParcelableExtra("item");
                if (imagen != null) {
                    setTitle(imagen.getNombre());
                }
            }
        }
        ArrayList<Imagen> lista =
                getIntent().getParcelableArrayListExtra("lista");
        int posicion =
                getIntent().getIntExtra("posicion", 0);
        if (lista != null) {
            ImagenDetalleAdapter adapter =
                    new ImagenDetalleAdapter(getSupportFragmentManager(), lista);
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(posicion);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

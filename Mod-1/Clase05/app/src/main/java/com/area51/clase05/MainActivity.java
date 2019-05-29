package com.area51.clase05;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridView);

        ArrayList<Imagen> lista = new ArrayList<>();
        lista.add(new Imagen("https://www.dzoom.org.es/wp-content/uploads/2017/07/seebensee-2384369-734x488.jpg", "Imagen 1"));
        lista.add(new Imagen("https://www.tourinews.es/uploads/s1/16/86/27/paisaje-2_4_732x400.jpeg", "Imagen 2"));
        lista.add(new Imagen("https://www.turismodeobservacion.com/media/fotografias/paisaje-del-delta-del-ebro-17970-xl.jpg", "Imagen 3"));
        lista.add(new Imagen("https://www.lifeder.com/wp-content/uploads/2017/07/paisaje-cultural.jpg", "Imagen 4"));
        lista.add(new Imagen("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRppYUDdL8dKfi_2ElPr_Pu15-OB6Rjrinsj7ZYM1LYPvtJ6K-R", "Imagen 5"));
        lista.add(new Imagen("https://mott.pe/noticias/wp-content/uploads/2017/08/Belleza-y-armon%C3%ADa-en-la-fotograf%C3%ADa-de-paisaje-de-Sveta-Imnadze-1-compressor.png", "Imagen 6"));
        lista.add(new Imagen("res:/" + R.drawable.imagen_micky, "Micky"));

        ImagenAdapter adapter = new ImagenAdapter(this, 0, lista);
        gridView.setAdapter(adapter);
    }
}

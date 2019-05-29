package com.area51.clase04;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends AppCompatActivity {
    private SimpleDraweeView sdvImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sdvImagen = findViewById(R.id.sdvImagen);
        sdvImagen.setImageURI(
                Uri.parse("https://www.dzoom.org.es/wp-content/uploads/2017/07/seebensee-2384369-734x488.jpg")
        );
    }
}

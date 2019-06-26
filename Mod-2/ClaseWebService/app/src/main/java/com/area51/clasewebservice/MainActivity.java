package com.area51.clasewebservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvDatos = findViewById(R.id.rvDatos);

        RetrofitServicios retrofit = RetrofitHelper.obtenerConfiguracion()
                .create(RetrofitServicios.class);
        Call<ArrayList<PostResponse>> call = retrofit.obtenerPost();
        call.enqueue(new Callback<ArrayList<PostResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<PostResponse>> call,
                                   Response<ArrayList<PostResponse>> response) {
                ArrayList<PostResponse> respuesta = response.body();

                ArrayList<Post> listaPost = new ArrayList<>();
                for (PostResponse item : respuesta) {
                    Post post = new Post();
                    post.setId(item.getId());
                    post.setCuerpo(item.getBody());
                    post.setTitulo(item.getTitle());
                    post.setUsuarioId(item.getUserId());
                    listaPost.add(post);
                }
                PostAdapter adapter = new PostAdapter(MainActivity.this, listaPost);
                rvDatos.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                rvDatos.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<ArrayList<PostResponse>> call, Throwable t) {

            }
        });
    }
}

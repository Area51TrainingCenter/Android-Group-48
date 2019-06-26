package com.area51.clasewebservice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Post> lista;

    public PostAdapter(Context context, ArrayList<Post> lista) {
        this.context = context;
        this.lista = lista;
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitulo, tvCuerpo;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvCuerpo = itemView.findViewById(R.id.tvCuerpo);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_post, viewGroup, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        PostViewHolder postViewHolder = (PostViewHolder) viewHolder;

        Post post = lista.get(i);

        postViewHolder.tvTitulo.setText(post.getTitulo());
        postViewHolder.tvCuerpo.setText(post.getCuerpo());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }
}

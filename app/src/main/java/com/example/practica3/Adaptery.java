package com.example.practica3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adaptery extends RecyclerView.Adapter<Adaptery.MyViewHolder> {

    private Context mContext;
    private List<CatalogoModelClas> mData;

    public Adaptery(Context mContext, List<CatalogoModelClas> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        v = inflater.inflate(R.layout.catalogo_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.id.setText(mData.get(position).getId());
        holder.titulo.setText(mData.get(position).getTitulo());
        holder.descripcion.setText(mData.get(position).getDescripcion());
        holder.url.setText(mData.get(position).getUrl());
        holder.articulo.setText(mData.get(position).getArticulo());

    }

    @Override
    public int getItemCount() {
        return mData.size();

    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id;
        TextView titulo;
        TextView descripcion;
        TextView url;
        TextView articulo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id_text);
            titulo = itemView.findViewById(R.id.name_txt);
            descripcion = itemView.findViewById(R.id.descripcion_text);
            url = itemView.findViewById(R.id.url_text);
            articulo = itemView.findViewById(R.id.articulo_text);


        }
    }
}

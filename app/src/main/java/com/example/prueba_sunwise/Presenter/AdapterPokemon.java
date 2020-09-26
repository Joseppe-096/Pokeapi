package com.example.prueba_sunwise.Presenter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba_sunwise.Model.Pokemon;
import com.example.prueba_sunwise.R;

import java.util.ArrayList;

public class AdapterPokemon extends RecyclerView.Adapter<AdapterPokemon.menuViewHolder> {
    private ArrayList<Pokemon> list;

    public AdapterPokemon() {
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public menuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemo,parent,false);
        return new menuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull menuViewHolder holder, int position) {
        Pokemon oPokemon = list.get(position);
        holder.txtNombrePokemon.setText(oPokemon.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void adicionarListaPokemon(ArrayList<Pokemon> listaPokemon) {
        list.addAll(listaPokemon);
        notifyDataSetChanged();
    }
    public static class menuViewHolder extends RecyclerView.ViewHolder{
        private TextView txtNombrePokemon;

        public menuViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombrePokemon = itemView.findViewById(R.id.txtNombrePokemon);
        }
    }
}


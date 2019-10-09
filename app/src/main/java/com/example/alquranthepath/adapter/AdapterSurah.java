package com.example.alquranthepath.adapter;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alquranthepath.R;
import com.example.alquranthepath.adapter.AdapterHome;
import com.example.alquranthepath.modal.ModalAyatHome;

import java.util.ArrayList;

public class AdapterSurah extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    View view;
    ArrayList<ModalAyatHome> modalAyatHomes;

    public AdapterSurah(View view, ArrayList modalAyatHomes) {
        this.view = view;
        this.modalAyatHomes = modalAyatHomes;
    }

    class AdapterSurahKu extends RecyclerView.ViewHolder {

        TextView nomor,ar,id,tr;

        public AdapterSurahKu(@NonNull View itemView) {
            super(itemView);
            nomor = itemView.findViewById(R.id.nomor);
            ar = itemView.findViewById(R.id.ar);
            id = itemView.findViewById(R.id.id);
            tr = itemView.findViewById(R.id.tr);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_surah, parent, false);
        return new AdapterSurahKu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int i) {
        ((AdapterSurahKu)holder).nomor.setText(modalAyatHomes.get(i).getNomor());
        ((AdapterSurahKu)holder).id.setText(modalAyatHomes.get(i).getArti());
        ((AdapterSurahKu)holder).ar.setText(modalAyatHomes.get(i).getAsma());
        ((AdapterSurahKu)holder).tr.setText(Html.fromHtml(modalAyatHomes.get(i).getName()));
        
    }

    @Override
    public int getItemCount() {
        return modalAyatHomes.size();
    }
}

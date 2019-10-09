package com.example.alquranthepath.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alquranthepath.R;
import com.example.alquranthepath.activity.SurahActivity;
import com.example.alquranthepath.base.Constant;
import com.example.alquranthepath.modal.ModalAyatHome;

import java.util.ArrayList;

public class AdapterHome extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    GridLayoutManager glm;
    RecyclerView rc;
    View view;
    ArrayList<ModalAyatHome> modalAyatHomes;

    public AdapterHome(Context context, ArrayList<ModalAyatHome> modalAyatHomes) {
        this.context = context;
        this.modalAyatHomes = modalAyatHomes;
    }

    class AdapterHomeku extends RecyclerView.ViewHolder {

        TextView nomor,name, asma, arti;
        LinearLayout cover;

        public AdapterHomeku(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.cover);
            nomor = itemView.findViewById(R.id.nomor);
            name = itemView.findViewById(R.id.name);
            asma = itemView.findViewById(R.id.asma);
            arti = itemView.findViewById(R.id.arti);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home, parent, false);
        return new AdapterHomeku(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int i) {

        ((AdapterHomeku)holder).arti.setText(modalAyatHomes.get(i).getArti());
        ((AdapterHomeku)holder).asma.setText(modalAyatHomes.get(i).getAsma());
        ((AdapterHomeku)holder).nomor.setText(modalAyatHomes.get(i).getNomor());
        ((AdapterHomeku)holder).name.setText(modalAyatHomes.get(i).getName());

        ((AdapterHomeku)holder).cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), SurahActivity.class);
                intent.putExtra("ids", modalAyatHomes.get(i).getNomor());
                context.startActivity(intent);
                Log.d("IDKU", modalAyatHomes.get(i).getNomor() + "");
                Log.d("FULLID", Constant.ROOT_SURAT1 + modalAyatHomes.get(i).getNomor() + Constant.ROOT_END);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modalAyatHomes.size();
    }
}

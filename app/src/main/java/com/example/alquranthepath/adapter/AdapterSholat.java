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
import androidx.recyclerview.widget.RecyclerView;

import com.example.alquranthepath.R;
import com.example.alquranthepath.activity.JadwalSholatActivity;
import com.example.alquranthepath.modal.ModalSholat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AdapterSholat extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ModalSholat> modalSholat;
    View view;
    Context context;

    public AdapterSholat(Context context, ArrayList<ModalSholat> modalSholat) {
        this.modalSholat = modalSholat;
        this.context = context;
    }

    class MyAdapterSholatku extends RecyclerView.ViewHolder {

        TextView namakota;
        LinearLayout covers;

        public MyAdapterSholatku(@NonNull View itemView) {
            super(itemView);
            namakota = itemView.findViewById(R.id.namakota);
            covers = itemView.findViewById(R.id.covers);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_sholat, parent, false);
        return new MyAdapterSholatku(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int i) {
        ((MyAdapterSholatku)holder).namakota.setText(modalSholat.get(i).getNamakota());
        final Date date = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        final String Dateku = simpleDateFormat.format(date);
        ((MyAdapterSholatku)holder).covers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), JadwalSholatActivity.class);
                intent.putExtra("id", modalSholat.get(i).getIds());
                intent.putExtra("tanggal", Dateku);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modalSholat.size();
    }
}

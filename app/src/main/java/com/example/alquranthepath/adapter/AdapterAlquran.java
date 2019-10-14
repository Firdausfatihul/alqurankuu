package com.example.alquranthepath.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alquranthepath.R;
import com.example.alquranthepath.activity.Server;
import com.example.alquranthepath.modal.ModalAyatHome;
import com.example.alquranthepath.modal.ModalMPTri;

import java.util.ArrayList;

public class AdapterAlquran extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    GridLayoutManager glm;
    RecyclerView rc;
    View view;
    ArrayList<ModalMPTri> modalMPTris;

    public AdapterAlquran(Context context, ArrayList<ModalMPTri> modalMPTris) {
        this.context = context;
        this.modalMPTris = modalMPTris;
    }

    class MyAdapterMPtriKu extends RecyclerView.ViewHolder {

        TextView nama, server, ids;
        LinearLayout cd;

        public MyAdapterMPtriKu(@NonNull View itemView) {
            super(itemView);
            ids = itemView.findViewById(R.id.ids);
            cd = itemView.findViewById(R.id.coverss);
            nama = itemView.findViewById(R.id.nama);
            server = itemView.findViewById(R.id.server);


        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_mptri, parent, false);
        return new MyAdapterMPtriKu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((MyAdapterMPtriKu)holder).ids.setText(modalMPTris.get(position).getIdss());
        ((MyAdapterMPtriKu)holder).nama.setText(modalMPTris.get(position).getNames());
        ((MyAdapterMPtriKu)holder).server.setText(modalMPTris.get(position).getServer());

        ((MyAdapterMPtriKu)holder).cd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Server.class);
                intent.putExtra("Server", modalMPTris.get(position).getServer());
                Log.d("IDKU", modalMPTris.get(position).getServer() + "");
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return modalMPTris.size();
    }
}

package com.example.alquranthepath.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.alquranthepath.R;
import com.example.alquranthepath.adapter.AdapterHome;
import com.example.alquranthepath.adapter.AdapterSurah;
import com.example.alquranthepath.base.Constant;
import com.example.alquranthepath.modal.ModalAyatHome;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SurahActivity extends AppCompatActivity {
    View v;
    RecyclerView rc;
    AdapterSurah adapterSurah;
    GridLayoutManager glm;
    ArrayList<ModalAyatHome> modalAyatHomes;
    RequestQueue requestQueue;
    Context context;
    String ids;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.surah_activity);

        ids = getIntent().getStringExtra("ids");

        context = getApplicationContext();

        rc = findViewById(R.id.recycler_views);
        modalAyatHomes = new ArrayList<>();
        glm = new GridLayoutManager(getApplicationContext(), 1);
        adapterSurah = new AdapterSurah(v, modalAyatHomes);

        rc.setLayoutManager(glm);
        rc.setAdapter(adapterSurah);

        //RequestVolleyArray
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        requestJsonArray();
    }

    private void requestJsonArray(){



        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, Constant.ROOT_SURAT1 + ids + Constant.ROOT_END, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++){
                        JSONObject object = response.getJSONObject(i);
                        String nomor = object.getString("nomor");
                        String asma = object.getString("ar");
                        String arti = object.getString("id");
                        String nama = object.getString("tr");

                        ModalAyatHome modalAyatHome = new ModalAyatHome(asma, nama, arti, nomor);
                        modalAyatHomes.add(modalAyatHome);
                        rc.setAdapter(adapterSurah);

                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ArrayGagal", error + "");
            }
        });
        requestQueue.add(request);
    }

}

package com.example.alquranthepath.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.alquranthepath.R;
import com.example.alquranthepath.adapter.AdapterHome;
import com.example.alquranthepath.base.Constant;
import com.example.alquranthepath.modal.ModalAyatHome;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentHome extends Fragment {

    View v;
    RecyclerView rc;
    AdapterHome adapterHome;
    GridLayoutManager glm;
    ArrayList<ModalAyatHome> modalAyatHomes;
    RequestQueue requestQueue;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_home, container, false);
        context = getActivity();

        rc = v.findViewById(R.id.recycler_view);
        modalAyatHomes = new ArrayList<>();
        glm = new GridLayoutManager(getActivity(), 2);
        adapterHome = new AdapterHome(getActivity(), modalAyatHomes);

        rc.setLayoutManager(glm);
        rc.setAdapter(adapterHome);

        //RequestVolleyArray
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        requestJsonArray();

        return v;

    }

    private void requestJsonArray(){



        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, Constant.ROOT_AYAT, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++){
                        JSONObject object = response.getJSONObject(i);
                        String nomor = object.getString("nomor");
                        String asma = object.getString("asma");
                        String arti = object.getString("arti");
                        String nama = object.getString("nama");

                        ModalAyatHome modalAyatHome = new ModalAyatHome(asma, nama, arti, nomor);
                        modalAyatHomes.add(modalAyatHome);
                        rc.setAdapter(adapterHome);

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

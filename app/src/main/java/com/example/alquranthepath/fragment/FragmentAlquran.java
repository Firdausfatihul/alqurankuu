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
import com.example.alquranthepath.adapter.AdapterAlquran;
import com.example.alquranthepath.adapter.AdapterHome;
import com.example.alquranthepath.base.Constant;
import com.example.alquranthepath.modal.ModalAyatHome;
import com.example.alquranthepath.modal.ModalMPTri;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentAlquran extends Fragment {

    View v;
    RecyclerView rc;
    AdapterAlquran adapterAlquran;
    GridLayoutManager glm;
    ArrayList<ModalMPTri> modalMPTris;
    RequestQueue requestQueue;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_alquran, container, false);
        context = getActivity();
        glm = new GridLayoutManager(context, 2);
        modalMPTris = new ArrayList<>();
        adapterAlquran = new AdapterAlquran(context, modalMPTris);

        rc = v.findViewById(R.id.recycler_viewsss);
        rc.setLayoutManager(glm);

        //RequestVolleyArray
        requestQueue = Volley.newRequestQueue(context);
        requestJsonArray();

        return v;
    }
    private void requestJsonArray(){



        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Constant.ROOT_MPTRI, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray array = response.getJSONArray("reciters");
                    for (int i = 0; i < array.length(); i++){
                        JSONObject ar = array.getJSONObject(i);
                        String idss = ar.getString("id");
                        String name = ar.getString("name");
                        String server = ar.getString("Server");


                        ModalMPTri modalMPTri = new ModalMPTri(idss, name, server);
                        modalMPTris.add(modalMPTri);
                        rc.setAdapter(adapterAlquran);

                        Log.d("ARRAY", idss + "" + name + "" + "" + server + "" );

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

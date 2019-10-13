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
import com.example.alquranthepath.adapter.AdapterSholat;
import com.example.alquranthepath.base.Constant;
import com.example.alquranthepath.modal.ModalAyatHome;
import com.example.alquranthepath.modal.ModalSholat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentSholat extends Fragment {

    View v;
    RecyclerView rc;
    AdapterSholat adapterSholat;
    GridLayoutManager glm;
    ArrayList<ModalSholat> modalSholats;
    RequestQueue requestQueue;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_sholat, container, false);
        context = getActivity();

        rc = v.findViewById(R.id.recycler_viewer);
        modalSholats = new ArrayList<>();
        glm = new GridLayoutManager(context, 2);
        adapterSholat = new AdapterSholat(context, modalSholats);

        rc.setAdapter(adapterSholat);
        rc.setLayoutManager(glm);

        requestQueue = Volley.newRequestQueue(context);
        requestJsonArray();


        return v;
    }

    private void requestJsonArray(){



        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Constant.FIRST_SHOLAT, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    JSONArray array = response.getJSONArray("kota");

                    for (int i = 0; i < array.length(); i++){
                        JSONObject object = array.getJSONObject(i);

                        String ids = object.getString("id");
                        String nama = object.getString("nama");

                        ModalSholat modalSholat = new ModalSholat( nama, ids);
                        modalSholats.add(modalSholat);
                        rc.setAdapter(adapterSholat);

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

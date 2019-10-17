package com.example.alquranthepath.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
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
import java.util.Calendar;
import java.util.Date;

public class FragmentHome extends Fragment {

    View v;
    RecyclerView rc;
    AdapterHome adapterHome;
    GridLayoutManager glm;
    ArrayList<ModalAyatHome> modalAyatHomes;
    RequestQueue requestQueue;
    Context context;
    Toolbar toolbar;
    SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        v = inflater.inflate(R.layout.fragment_home, container, false);
        context = getActivity();

        rc = v.findViewById(R.id.recycler_view);
        modalAyatHomes = new ArrayList<>();
        glm = new GridLayoutManager(getActivity(), 2);


        rc.setLayoutManager(glm);

        toolbar = v.findViewById(R.id.toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //RequestVolleyArray
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        requestJsonArray();

        return v;

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.search_view, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        MenuItemCompat.setShowAsAction(menuItem, MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setOnQueryTextListener(queryTes);
        super.onCreateOptionsMenu(menu, inflater);
    }

    SearchView.OnQueryTextListener queryTes = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            if (adapterHome != null){
                if (!searchView.isIconified()){
                    adapterHome.getFilter().filter(newText);
                    adapterHome.notifyDataSetChanged();
                }
            }
            return true;
        }
    };

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
                        adapterHome = new AdapterHome(getActivity(), modalAyatHomes);
                        rc.setAdapter(adapterHome);

                        Log.d("log nama", nama + "");

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

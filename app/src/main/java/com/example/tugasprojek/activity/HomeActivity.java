package com.example.tugasprojek.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tugasprojek.DokterAdapter;
import com.example.tugasprojek.DokterModel;
import com.example.tugasprojek.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements DokterAdapter.OnItemClickListener {
    RecyclerView recyclerView;
    DokterAdapter dokterAdapter;
    List<DokterModel> dokterModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);

        dokterModels = new ArrayList<>();
        dokterAdapter = new DokterAdapter(dokterModels, this, this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dokterAdapter);
        getDataDoctor();
    }

    public void onItemClick(int position) {
        DokterModel selectedDokter = dokterModels.get(position);
        String id_dokter = String.valueOf(selectedDokter.getId_dokter());
        Log.d("HomeActivity", "Selected id_dokter: " + id_dokter);
        Intent intent = new Intent(HomeActivity.this, JadwalActivity.class);
        intent.putExtra("id_dokter", id_dokter);
        startActivity(intent);
    }

    private void  getDataDoctor() {
        RequestQueue requestQueue = Volley.newRequestQueue(HomeActivity.this);
        String url = "http://192.168.5.35:8080/api/dokter";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject data = response.getJSONObject(i);

                                // Buat objek DokterModel di dalam loop
                                DokterModel dokterModel = new DokterModel();
                                dokterModel.setId_dokter(data.getInt("id_dokter"));
                                dokterModel.setDoctorName(data.getString("nama_dokter"));
                                dokterModel.setDoctorSpesialis(data.getString("spesialis"));
                                dokterModel.setDoctorAddress(data.getString("alamat_dokter"));
                                dokterModel.setBiaya_konsultasi(data.getString("biaya_konsultasi"));
                                String formatBiaya = "Rp " + NumberFormat.getInstance().format(Integer.parseInt(data.getString("biaya_konsultasi")));
                                dokterModel.setFormatBiaya(formatBiaya);
                                dokterModels.add(dokterModel);
                            }

                            // Perbarui adapter setelah mendapatkan data
                            dokterAdapter.notifyDataSetChanged();
                            recyclerView.setAdapter(dokterAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(HomeActivity.this, "Tidak bisa memuat data", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonArrayRequest);
    }
}

package com.example.tugasprojek;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugasprojek.DokterAdapter;
import com.example.tugasprojek.DokterDataSource;
import com.example.tugasprojek.DokterModel;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DokterAdapter dokterAdapter;
    List<DokterModel> dokterModels;
    DokterDataSource dokterDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Inisialisasi RecyclerView dari layout XML
        recyclerView = findViewById(R.id.recyclerView); // Sesuaikan dengan ID yang benar

        // Inisialisasi sumber data dan adapter
        dokterDataSource = new DokterDataSource();
        dokterModels = dokterDataSource.getDokterModels();
        dokterAdapter = new DokterAdapter(dokterModels);

        // Set LayoutManager dan adapter ke RecyclerView
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dokterAdapter);
    }
}

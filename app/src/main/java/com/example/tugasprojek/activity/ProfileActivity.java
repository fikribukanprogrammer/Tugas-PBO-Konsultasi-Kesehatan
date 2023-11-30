package com.example.tugasprojek.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tugasprojek.R;

public class ProfileActivity extends AppCompatActivity {
    TextView nama_lengkap_pro, jenis_kelamin_pro, tanggal_lahir_pro, alamat_pro, nomor_hp_pro, email_pro;
    Button goToHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //Hook
        nama_lengkap_pro = findViewById(R.id.nama_lengkap);
        jenis_kelamin_pro = findViewById(R.id.jenis_kelamin);
        tanggal_lahir_pro = findViewById(R.id.tanggal_lahir);
        alamat_pro = findViewById(R.id.alamat);
        nomor_hp_pro = findViewById(R.id.nomor_hp);
        email_pro = findViewById(R.id.email);

        String nama_lengkap = getIntent().getStringExtra("nama_lengkap");
        String jenis_kelamin = getIntent().getStringExtra("jenis_kelamin");
        String tanggal_lahir = getIntent().getStringExtra("tanggal_lahir");
        String alamat = getIntent().getStringExtra("alamat");
        String nomor_hp = getIntent().getStringExtra("nomor_hp");
        String email = getIntent().getStringExtra("email");

        nama_lengkap_pro.setText(nama_lengkap);
        jenis_kelamin_pro.setText(jenis_kelamin);
        tanggal_lahir_pro.setText(tanggal_lahir);
        alamat_pro.setText(alamat);
        nomor_hp_pro.setText(nomor_hp);
        email_pro.setText(email);

        goToHome = findViewById(R.id.goToHome);
        goToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHomePage();
            }
        });
    }
    public void goToHomePage(){
        nama_lengkap_pro.setText(null);
        jenis_kelamin_pro.setText(null);
        tanggal_lahir_pro.setText(null);
        alamat_pro.setText(null);
        nomor_hp_pro.setText(null);
        email_pro.setText(null);

        Intent goToHomePage = new Intent(ProfileActivity.this, HomeActivity.class);
        startActivity(goToHomePage);
        finish();
    }


}



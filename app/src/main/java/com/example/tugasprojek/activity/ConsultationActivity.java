package com.example.tugasprojek.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tugasprojek.R;

public class ConsultationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);
    }

    public void goToHomePage(View view){
        Intent intent = new Intent(ConsultationActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
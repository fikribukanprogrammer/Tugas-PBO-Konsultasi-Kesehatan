package com.example.tugasprojek.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tugasprojek.R;

public class SuccessReservationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_reservation);
    }

    public void goToConsultation(View view){
        Intent intent = new Intent(SuccessReservationActivity.this, ConsultationActivity.class);
        startActivity(intent);
        finish();
    }
}
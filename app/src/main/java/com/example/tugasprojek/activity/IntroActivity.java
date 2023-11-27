package com.example.tugasprojek.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tugasprojek.R;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
    }

    public void goToLogin(View view){
        Intent intent = new Intent(IntroActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


}
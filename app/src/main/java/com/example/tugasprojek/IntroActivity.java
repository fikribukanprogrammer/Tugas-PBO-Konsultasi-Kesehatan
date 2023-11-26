package com.example.tugasprojek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Button btnLoginIntro = (Button) findViewById(R.id.btnLoginIntro);
        btnLoginIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this, LoginActivity.class));
            }
        });

//        //Button btnSignUpIntro = (Button) findViewById(R.id.btnSignUpIntro);
//        //btnSignUpIntro.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(IntroActivity.this, LoginActivity.class));
//            }
//        });
    }


}
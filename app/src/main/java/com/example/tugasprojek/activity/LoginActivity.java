package com.example.tugasprojek.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tugasprojek.R;
import com.example.tugasprojek.validation.RegexEmailValidation;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    Button buttonLoginToProfile;
    EditText emailLogin, passwordLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Hook
        buttonLoginToProfile = findViewById(R.id.buttonLoginToProfile);
        emailLogin = findViewById(R.id.EmailLogin);
        passwordLogin = findViewById(R.id.PasswordLogin);
        buttonLoginToProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticateUser();
            }
        });
    }

    public void authenticateUser(){
        if(!validateEmail() || !validatePassword()){
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        String url = "http://192.168.5.35:8080/api/v1/user/login";

        //set parameters
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("email", emailLogin.getText().toString());
        params.put("kata_sandi", passwordLogin.getText().toString());

        //set request object
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //get values from response object
                    String nama_lengkap =(String) response.getString("nama_lengkap");
                    String jenis_kelamin =(String) response.getString("jenis_kelamin");
                    String tanggal_lahir = (String) response.getString("tanggal_lahir");
                    String alamat = (String) response.getString("alamat");
                    String nomor_hp = (String) response.getString("nomor_hp");
                    String email =(String) response.getString("email");

                    Intent goToProfile = new Intent(LoginActivity.this, ProfileActivity.class);
                    goToProfile.putExtra("nama_lengkap", nama_lengkap);
                    goToProfile.putExtra("jenis_kelamin", jenis_kelamin);
                    goToProfile.putExtra("tanggal_lahir", tanggal_lahir);
                    goToProfile.putExtra("alamat", alamat);
                    goToProfile.putExtra("nomor_hp", nomor_hp);
                    goToProfile.putExtra("email", email);
                    startActivity(goToProfile);
                    finish();

                } catch (JSONException e){
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println(error.getMessage());
                Toast.makeText(LoginActivity.this, "Gagal Masuk", Toast.LENGTH_LONG).show();
            }
        });
        queue.add(jsonObjectRequest);
    }

    //Untuk validasi input email
    public boolean validateEmail(){
        String email_l = emailLogin.getText().toString();

        if(email_l.isEmpty()){
            emailLogin.setError("Email belum diisi");
            return false;
        }else if(!RegexEmailValidation.regexEmailValidationPattern(email_l)){
            emailLogin.setError("Email tidak valid");
            return false;
        }
        else{
            emailLogin.setError(null);
            return true;
        }
    }

    //Untuk validasi input kata sandi dan konfirmasi kata sandi
    public boolean validatePassword(){
        String password_e = passwordLogin.getText().toString();

        if(password_e.isEmpty()){
            passwordLogin.setError("Kata sandi belum diisi");
            return false;
        } else{
            passwordLogin.setError(null);
            return true;
        }
    }

    //Untuk pindah page
    public void goToSignUp(View view){
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }
}
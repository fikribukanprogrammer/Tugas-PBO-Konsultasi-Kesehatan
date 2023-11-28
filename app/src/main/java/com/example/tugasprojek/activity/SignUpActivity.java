package com.example.tugasprojek.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tugasprojek.R;
import com.example.tugasprojek.validation.RegexEmailValidation;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    EditText PersonName, Gender, DateofBirth, Address, PhoneNumber, EmailSignUp, PasswordSignUp, ConfirmPass;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Hook
        btnSignUp = findViewById(R.id.btnSignUp);
        PersonName = findViewById(R.id.PersonName);
        Gender = findViewById(R.id.Gender);
        DateofBirth= findViewById(R.id.DateOfBirth);
        Address = findViewById(R.id.Address);
        PhoneNumber = findViewById(R.id.PhoneNumber);
        EmailSignUp = findViewById(R.id.EmailSignUp);
        PasswordSignUp = findViewById(R.id.PasswordSignUp);
        ConfirmPass = findViewById(R.id.ConfirmPass);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processFormFields();

            }
        });

    }


    public void processFormFields(){
        if(!validatePersonName() || !validateGender() || !validateDateofBirth()|| !validateAddress() || !validatePhoneNumber() || !validateEmail() || !validatePasswordandConfirm()){
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
        String url = "http://172.20.10.4:8080/api/v1/user/signup";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equalsIgnoreCase("success")) {
                    Toast.makeText(SignUpActivity.this, "Berhasil", Toast.LENGTH_LONG).show();
                    clearFormFields();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                System.out.println(error.getMessage());
                Toast.makeText(SignUpActivity.this, "Gagal", Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nama_lengkap", PersonName.getText().toString());
                params.put("jenis_kelamin", Gender.getText().toString());
                params.put("tanggal_lahir", DateofBirth.getText().toString());
                params.put("alamat", Address.getText().toString());
                params.put("nomor_hp", PhoneNumber.getText().toString());
                params.put("email", EmailSignUp.getText().toString());
                params.put("kata_sandi", PasswordSignUp.getText().toString());
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void clearFormFields() {
        PersonName.setText(null);
        Gender.setText(null);
        DateofBirth.setText(null);
        Address.setText(null);
        PhoneNumber.setText(null);
        EmailSignUp.setText(null);
        PasswordSignUp.setText(null);
        ConfirmPass.setText(null);
    }

    //Untuk validasi input nama lengkap
    public boolean validatePersonName(){
        String personName = PersonName.getText().toString();
        if(personName.isEmpty()){
            PersonName.setError("Nama lengkap belum diisi");
            return false;
        }else{
            PersonName.setError(null);
            return true;
        }
    }

    //untuk validasi input jenis kelamin
    public boolean validateGender(){
        String gender = Gender.getText().toString();

        if(gender.isEmpty()){
            Gender.setError("Jenis kelamin belum diisi");
            return false;
        }else{
            Gender.setError(null);
            return true;
        }
    }

    //untuk validasi input tanggal lahir
    public boolean validateDateofBirth(){
        String birth = DateofBirth.getText().toString();
        if(birth.isEmpty()){
            DateofBirth.setError("Tanggal lahir belum diisi");
            return false;
        }else{
            DateofBirth.setError(null);
            return true;
        }
    }

    //untuk validasi input alamat
    public boolean validateAddress(){
        String address = Address.getText().toString();

        if(address.isEmpty()){
            Address.setError("Alamat belum diisi");
            return false;
        }else{
            Address.setError(null);
            return true;
        }
    }
    //untuk validasi input Nomor Telepon
    public boolean validatePhoneNumber(){
        String phoneNumber = PhoneNumber.getText().toString();
        if(phoneNumber.isEmpty()){
            PhoneNumber.setError("Nomor telepon belum diisi");
            return false;
        }else{
            PhoneNumber.setError(null);
            return true;
        }
    }

    //Untuk validasi input email
    public boolean validateEmail(){
        String email = EmailSignUp.getText().toString();

        if(email.isEmpty()){
            EmailSignUp.setError("Email belum diisi");
            return false;
        }else if(!RegexEmailValidation.regexEmailValidationPattern(email)){
            EmailSignUp.setError("Email tidak valid");
            return false;
        }
        else{
            EmailSignUp.setError(null);
            return true;
        }
    }

    //Untuk validasi input kata sandi dan konfirmasi kata sandi
    public boolean validatePasswordandConfirm(){
        String password = PasswordSignUp.getText().toString();
        String confirmPass = ConfirmPass.getText().toString();

        if(password.isEmpty() ){
            PasswordSignUp.setError("Kata sandi belum diisi");
            return false;
        }else if(confirmPass.isEmpty()){
            ConfirmPass.setError("Konfirmasi kata sandi belum diisi");
            return false;
        }else if(!confirmPass.equals(password)){
            PasswordSignUp.setError("Kata sandi tidak sama");
            return false;
        } else{
            PasswordSignUp.setError(null);
            ConfirmPass.setError(null);
            return true;
        }
    }
    //Pindah ke halaman Login
    public void goToLoginPage(View view){
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
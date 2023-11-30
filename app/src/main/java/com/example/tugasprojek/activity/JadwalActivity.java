package com.example.tugasprojek.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tugasprojek.R;
import com.google.android.material.textview.MaterialTextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class JadwalActivity extends AppCompatActivity {
    String idDokter;
    TextView nama_dokter, spesialis_dokter, biaya_konsultasi, total_bayar;
    CardView cardViewJadwal, cardJadwalKonsultasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        nama_dokter = findViewById(R.id.txtName);
        spesialis_dokter = findViewById(R.id.txtSpesialis);
        biaya_konsultasi = findViewById(R.id.txtBiayaKonsultasi);
        total_bayar = findViewById(R.id.txtBiayaKonsultasi1);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("id_dokter")) {
            idDokter = intent.getStringExtra("id_dokter");
            if (idDokter != null && !idDokter.isEmpty()) {
                fetchDataForDokter(idDokter);
            } else {
                Toast.makeText(this, "Tidak menemukan id dokter", Toast.LENGTH_SHORT).show();
            }
        }

        cardViewJadwal = findViewById(R.id.cardViewJadwal);
        cardJadwalKonsultasi = findViewById(R.id.cardJadwalKonsultasi);
        cardJadwalKonsultasi.setVisibility(View.GONE);

        cardViewJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimePickers();
            }
        });
    }

    public void fetchDataForDokter(String id_dokter) {
        RequestQueue requestQueue = Volley.newRequestQueue(JadwalActivity.this);
        String url = "http://192.168.5.35:8080/api/dokter/" + id_dokter;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String doctorName = response.getString("nama_dokter");
                            String spesialis = response.getString("spesialis");
                            String biayaKonsultasi = response.getString("biaya_konsultasi");

                            nama_dokter.setText(doctorName);
                            spesialis_dokter.setText(spesialis);
                            biaya_konsultasi.setText("Rp" + NumberFormat.getInstance().format(Integer.parseInt(biayaKonsultasi)));
                            total_bayar.setText("Rp" + NumberFormat.getInstance().format(Integer.parseInt(biayaKonsultasi)));

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(JadwalActivity.this, "Tidak bisa memuat data", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(JadwalActivity.this, "Tidak bisa memuat data", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }
    private void showDateTimePickers() {
        Calendar calendar = Calendar.getInstance();

        // Show DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Handle the selected date
                        showTimePicker(year, monthOfYear, dayOfMonth);
                    }
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    private void showTimePicker(final int selectedYear, final int selectedMonth, final int selectedDay) {
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Show TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        handleDateTimeSelection(selectedYear, selectedMonth, selectedDay, hourOfDay, minute);
                    }
                },
                hourOfDay,
                minute,
                true);
        timePickerDialog.show();
    }
    private void handleDateTimeSelection(int year, int month, int day, int hour, int minute) {
        String selectedDate = String.format("%02d-%02d-%04d", day, month + 1, year);
        String selectedTime = String.format("%02d:%02d", hour, minute);

        MaterialTextView txtSelectedDate = findViewById(R.id.txtSelectedDate);
        MaterialTextView txtSelectedTime = findViewById(R.id.txtSelectedTime);

        if (!isFinishing() && !isDestroyed()) {
            txtSelectedDate.setText(selectedDate);
            txtSelectedTime.setText(selectedTime);
            cardJadwalKonsultasi.setVisibility(View.VISIBLE);
            timeSelect(selectedDate, selectedTime);
        }
    }
    private void timeSelect(final String selectedDate, final String selectedTime) {
        RequestQueue requestQueue = Volley.newRequestQueue(JadwalActivity.this);
        String url = "http://192.168.5.35:8080/api/jadwal_konsultasi/simpan";
        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("id_dokter", idDokter);
            System.out.println(idDokter);
            jsonBody.put("tanggal_konsultasi", selectedDate);
            jsonBody.put("waktu_konsultasi", selectedTime);

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // Handle the response from the server (if needed)
                            Toast.makeText(JadwalActivity.this, "Jadwal konsultasi berhasil disimpan", Toast.LENGTH_SHORT).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
//                            Toast.makeText(JadwalActivity.this, "Gagal menyimpan jadwal konsultasi", Toast.LENGTH_SHORT).show();
                        }
                    });

            // Add the request to the queue
            requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void goToSuccessReservation(View view){
        Intent intent = new Intent(JadwalActivity.this, SuccessReservationActivity.class);
        startActivity(intent);
        finish();
    }
}

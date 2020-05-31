package com.fikran.silocvid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class TambahData extends AppCompatActivity {


    private Button btnTambah;
    private EditText email;
    private EditText no_ktp;
    private EditText no_hp;
    private EditText latitude;
    private EditText longitude;
    private EditText instansi;
    private EditText alamat;
    private EditText informasi;
    private ProgressBar progressBar1;
    private static String URL_REGIST = "https://silcovid.000webhostapp.com/tambah.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_data);

        progressBar1 = (ProgressBar) findViewById(R.id.progress1);
        email = (EditText) findViewById(R.id.tEmail);
        latitude = (EditText) findViewById(R.id.tLatitude);
        longitude = (EditText) findViewById(R.id.tLongitude);
        instansi = (EditText) findViewById(R.id.pilihan_item);
        alamat = (EditText) findViewById(R.id.alamat);
        informasi = (EditText) findViewById(R.id.informasi);
        btnTambah = (Button) findViewById(R.id.tambah_info);


        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tambah();
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("SILCOVID | Tambah Data");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


    private void Tambah() {
        progressBar1.setVisibility(View.VISIBLE);
        btnTambah.setVisibility(View.GONE);
        final String latitude = this.latitude.getText().toString().trim();
        final String longitude = this.longitude.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String instansi = this.instansi.getText().toString().trim();
        final String alamat = this.alamat.getText().toString().trim();
        final String informasi = this.informasi.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                Toast.makeText(TambahData.this, "Informasi Anda Berhasil di Tambahkan", Toast.LENGTH_SHORT).show();
                                progressBar1.setVisibility(View.GONE);
                                btnTambah.setVisibility(View.VISIBLE);
                                Intent moveToLogin = new Intent(TambahData.this, DetailInformasi.class);
                                startActivity(moveToLogin);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(TambahData.this, "" + response, Toast.LENGTH_SHORT).show();
                            progressBar1.setVisibility(View.GONE);
                            btnTambah.setVisibility(View.VISIBLE);

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(TambahData.this, "Pendaftaran Gagal \n Periksa Koneksi Internet Anda", Toast.LENGTH_SHORT).show();
                progressBar1.setVisibility(View.GONE);
                btnTambah.setVisibility(View.VISIBLE);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("latitude", latitude);
                params.put("longitude", longitude);
                params.put("email", email);
                params.put("instansi", instansi);
                params.put("alamat", alamat);
                params.put("informasi", informasi);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}


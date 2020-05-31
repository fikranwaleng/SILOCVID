package com.fikran.silocvid;

import androidx.appcompat.app.AppCompatActivity;;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class Daftar extends AppCompatActivity {


    private Button btnDaftar;
    private EditText email;
    private EditText no_ktp;
    private EditText no_hp;
    private EditText kata_sandi;
    private EditText konf_kata_sandi;
    private ProgressBar progressBar1;
    private static String URL_REGIST = "https://silcovid.000webhostapp.com/register.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        progressBar1 = (ProgressBar) findViewById(R.id.progress1);
        email = (EditText) findViewById(R.id.email);
        no_ktp = (EditText) findViewById(R.id.no_ktp);
        no_hp = (EditText) findViewById(R.id.no_hp);
        kata_sandi = (EditText) findViewById(R.id.kata_sandi);
        konf_kata_sandi = (EditText) findViewById(R.id.konf_kata_sandi);
        btnDaftar = (Button) findViewById(R.id.btn_daftar);

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Regist();
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("SILCOVID | Tambah Data");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


    }

    private void Regist() {
        progressBar1.setVisibility(View.VISIBLE);
        btnDaftar.setVisibility(View.GONE);
        final String email = this.email.getText().toString().trim();
        final String no_ktp = this.no_ktp.getText().toString().trim();
        final String no_hp = this.no_hp.getText().toString().trim();
        final String kata_sandi = this.kata_sandi.getText().toString().trim();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String success = jsonObject.getString("success");

                                    if (success.equals("1")) {
                                        Toast.makeText(Daftar.this, "Pendaftaran Berhasil", Toast.LENGTH_SHORT).show();
                                        progressBar1.setVisibility(View.GONE);
                                        btnDaftar.setVisibility(View.VISIBLE);
                                        Intent moveToLogin = new Intent(Daftar.this, Login.class);
                                        startActivity(moveToLogin);
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(Daftar.this, "" + response, Toast.LENGTH_SHORT).show();
                                    progressBar1.setVisibility(View.GONE);
                                    btnDaftar.setVisibility(View.VISIBLE);

                                }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(Daftar.this, "Pendaftaran Gagal \n Periksa Koneksi Internet Anda" , Toast.LENGTH_SHORT).show();
                            progressBar1.setVisibility(View.GONE);
                            btnDaftar.setVisibility(View.VISIBLE);


                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("email", email);
                    params.put("no_ktp", no_ktp);
                    params.put("no_hp", no_hp);
                    params.put("kata_sandi", kata_sandi);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

        }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

package com.fikran.silocvid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Queue;

public class DetailInformasi extends AppCompatActivity {

    private ProgressDialog pDialog;
    private Context context;
    private ImageView btnTambah;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_informasi);
        context = DetailInformasi.this;
        btnTambah = (ImageView) findViewById(R.id.btnTambah);
        pDialog = new ProgressDialog(context);

        ambil_data();

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent DetailIntent = new Intent(DetailInformasi.this, Login.class);
                startActivity(DetailIntent);
            }
        });


        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("SILCOVID | List Lokasi Informasi");
        }


    }

    void ambil_data(){
        String link = "https://silcovid.000webhostapp.com/list.php";
        pDialog.setMessage("Pengambilan Data\nMohon Tunggu...");
        showDialog();
        StringRequest respon = new StringRequest(
                Request.Method.POST,
                link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            ArrayList<Get_data> list_data;
                            list_data = new ArrayList<>();
                            for (int i = 0; i<jsonArray.length(); i++ ){
                                JSONObject data = jsonArray.getJSONObject(i);
                                String instansi=data.getString("instansi");
                                String email=data.getString("email");
                                String informasi=data.getString("informasi");
                                String latitude=data.getString("latitude");
                                String longitude=data.getString("longitude");
                                list_data.add(new Get_data(
                                        instansi,
                                        email,
                                        informasi,
                                        latitude,
                                        longitude

                                ));



                            }

                            ListView listView = findViewById(R.id.list_view);
                            final Custom_adapter adapter = new Custom_adapter(DetailInformasi.this, list_data);
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                                    Get_data get_data = (Get_data)adapter.getItem(position);
                                    Toast.makeText(context,  "Pengambilan Data\nMohon Tunggu...", Toast.LENGTH_LONG).show();

                                    Intent intent = new Intent(DetailInformasi.this,MapsActivity.class);
                                    intent.putExtra("latitude",get_data.getLatitude());
                                    intent.putExtra("longitude",get_data.getLongitude());
                                    intent.putExtra("nama",get_data.instansi);
                                    startActivity(intent);

                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        hideDialog();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailInformasi.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(respon);
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();

    }


    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }




    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }


}

class Get_data{
    String instansi="", email="", informasi="", latitude="", longitude="";

    Get_data(String instansi, String email, String informasi, String latitude, String longitude){

        this.instansi=instansi;
        this.email=email;
        this.informasi=informasi;
        this.latitude=latitude;
        this.longitude=longitude;
    }

    public String getInstansi() {
        return instansi;
    }

    public String getEmail() {
        return email;
    }

    public String getInformasi() {
        return informasi;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}

class Custom_adapter extends BaseAdapter{

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Get_data>model;
    Custom_adapter(Context context, ArrayList<Get_data> model){
        layoutInflater=LayoutInflater.from(context);
        this.context = context;
        this.model = model;
    }

    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public Object getItem(int position) {
        return model.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = layoutInflater.inflate(R.layout.custom_list, null);
        TextView instansi, email, informasi, latitude, longitude;

        instansi = view.findViewById(R.id.showInstansi);
        email = view.findViewById(R.id.showEmail);
        informasi = view.findViewById(R.id.showInformasi);
        latitude = view.findViewById(R.id.latitude);
        longitude = view.findViewById(R.id.longitude);

        instansi.setText(model.get(position).getInstansi());
        email.setText(model.get(position).getEmail());
        informasi.setText(model.get(position).getInformasi());
        latitude.setText(model.get(position).getLatitude());
        longitude.setText(model.get(position).getLongitude());
        return view;
    }
}

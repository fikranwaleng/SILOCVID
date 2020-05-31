package com.fikran.silocvid;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private EditText latitude,longitude,judul;
    private LatLng latLng;

    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);





        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }





    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {


        latitude =(EditText) findViewById(R.id.latitude);
        longitude =(EditText) findViewById(R.id.longitude);
        judul = (EditText)findViewById(R.id.nama);

        Intent intent = getIntent();
        String extralatitude = intent.getStringExtra("latitude");
        String extralongitude = intent.getStringExtra("longitude");
        String extranama = intent.getStringExtra("nama");


        latitude.setText(extralatitude);
        longitude.setText(extralongitude);
        judul.setText(extranama);


        mMap = googleMap;


        LatLng undata = new LatLng(-0.858129, 119.884030);
        mMap.addMarker(new MarkerOptions().position(undata).title(extranama));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(undata, 18),5000,null);



    }

}





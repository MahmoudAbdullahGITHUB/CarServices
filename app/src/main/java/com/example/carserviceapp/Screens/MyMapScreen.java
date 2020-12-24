package com.example.carserviceapp.Screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.carserviceapp.R;
import com.example.carserviceapp.data.CarModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MyMapScreen extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    ArrayList<Double> hopa;
    float lat;
    float longtuide;
    String caeModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_map_screen);

        hopa = new ArrayList<Double>();
        Intent i = getIntent();
        CarModel mod= (CarModel) i.getSerializableExtra("MyClass");
        assert mod != null;
        lat= (float) mod.getLat();
        longtuide= (float) mod.getLongtu();
        caeModel = mod.getModel();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        LatLng Maharashtra = new LatLng(lat, longtuide);
        map.addMarker(new MarkerOptions().position(Maharashtra).title("toyota"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Maharashtra));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(Maharashtra, 15.0f));

/*        map.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(19,19)),12.0f);

        map.setMaxZoomPreference(17f);
        map.setMinZoomPreference(19f);*/
    }
}
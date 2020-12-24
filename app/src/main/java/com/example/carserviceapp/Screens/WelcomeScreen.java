package com.example.carserviceapp.Screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carserviceapp.R;
import com.example.carserviceapp.data.CarModel;
import com.example.carserviceapp.data.ClientModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class WelcomeScreen extends AppCompatActivity {

    Button getLocation;
    Button myRequest;

    FusedLocationProviderClient fusedLocationProviderClient;
    FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    String userId;
    List<Address> addresses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        getLocation = findViewById(R.id.get_location);
        myRequest = findViewById(R.id.send_request);
        addresses = new ArrayList<>();


        userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(WelcomeScreen.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED

                        && ActivityCompat.checkSelfPermission(WelcomeScreen.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getCurrentLocation();
                } else {
                    System.out.println("Not Entered");
                    ActivityCompat.requestPermissions(WelcomeScreen.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION ,
                            Manifest.permission.ACCESS_COARSE_LOCATION}, 44);
                }

            }
        });

        myRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("hh = "+addresses.size());
                if (addresses.size() !=0) {
                    System.out.println("hhyy "+addresses.get(0).getLatitude());
                    sendRequest(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
                    Toast.makeText(getApplicationContext(), "done : your request sent to drivers", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "get your location firstly", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 44 && grantResults.length > 0 && (grantResults[0]+grantResults[1]
                ==PackageManager.PERMISSION_GRANTED)){
            getCurrentLocation();
        }else{
            System.out.println("Permission denied");
        }
    }

    @SuppressLint("MissingPermission")
    void getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {
                        Geocoder geocoder = new Geocoder(WelcomeScreen.this, Locale.getDefault());

                        try {
                            addresses = geocoder.getFromLocation(
                                    location.getLatitude(),
                                    location.getLongitude(), 1
                            );

                            System.out.println("Lat : " + addresses.get(0).getLatitude()
                                    + " , long : " + addresses.get(0).getLongitude()
                                    + " , country : " + addresses.get(0).getCountryName()
                                    + " , locality : " + addresses.get(0).getLocality()
                                    + " , address line : " + addresses.get(0).getAddressLine(0)
                            );

                           // myRequest.setText("Lat : " + addresses.get(0).getLatitude());


                            Toast.makeText(WelcomeScreen.this,addresses.get(0).getLocality(),Toast.LENGTH_SHORT).show();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        LocationRequest locationRequest = new LocationRequest()
                                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(10000)
                                .setFastestInterval(1000)
                                .setNumUpdates(1);

                        LocationCallback locationCallback = new LocationCallback(){
                            @Override
                            public void onLocationResult(LocationResult locationResult) {
                                Location location1 = locationResult.getLastLocation();
                                System.out.println("lat : = "+String.valueOf(location1.getLatitude()));
                            }
                        };

                        fusedLocationProviderClient.requestLocationUpdates(locationRequest,
                                locationCallback, Looper.myLooper());


                    }
                }
            });

        }else{
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }

    }

    public void sendRequest(double lat, double longt){
        databaseReference = firebaseDatabase.getReference();

        CarModel clientCarModel = new CarModel("toyota" , "taxi" , lat , longt);
        //ClientModel clientModel = new ClientModel(userId,"mahmoud","123456",true, clientCarModel);

        databaseReference.child("Requests").child(userId).setValue(clientCarModel);
    }




}


















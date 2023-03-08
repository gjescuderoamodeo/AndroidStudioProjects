package com.example.recuexamenmapas_guillermojoseescuderoamodeo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.recuexamenmapas_guillermojoseescuderoamodeo.databinding.ActivityMainBinding;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;


import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private ActivityMainBinding binding;
    private String provider;
    private LocationManager locationManager;
    private Location referencia;
    private double latitud,longitud,distancia,distanciaTotal;
    EditText etlongitud,etlatitud,etdistancia,etrumbo;
    Button aceptar, cancelar;
    Dialog dialog;
    private ArrayList<LatLng> posiciones = new ArrayList<>();
    private LatLng previa, siguiente, ultima;
    private String dirRumbo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //mapa
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        provider = locationManager.getBestProvider(criteria, false);


        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1000
            );
        }

        Location location = locationManager.getLastKnownLocation(provider);
        referencia=location;

        if (location != null) {
            onLocationChanged(location);
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    //menu en la toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.posicionLogLat:
                ejercicio2();
                break;
            case R.id.borrarPosiciones:
                borrarPosicionesMapa();
                break;
            case R.id.ubicacionActual:
                //ubicacionActual();
                break;
            case R.id.posicionDistRumbo:
                ejercicio3();
                break;

        }
        return true;
    }

    //mapa
    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //al estar listo el mapa,
        LatLng defecto = new LatLng(0, 0);
        LatLng posicion1 = new LatLng(53.499424, 1.873112);
        mMap.addMarker(new MarkerOptions().position(posicion1).title("Marker in 53.499424/1.873112"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(defecto));

    }

    //ej2
    public void ejercicio2() {
        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.ejercicio2);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        aceptar = dialog.findViewById(R.id.aceptar);
        cancelar = dialog.findViewById(R.id.cancelar);
        etlatitud = dialog.findViewById(R.id.et_rumbo);
        etlongitud = dialog.findViewById(R.id.et_distancia);

        //lo trae al frente
        dialog.show();

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etlongitud.getText()!=null && etlatitud!=null){
                    Double lat = Double.valueOf(etlatitud.getText().toString());
                    Double lng = Double.valueOf(etlongitud.getText().toString());
                    LatLng nuevo = new LatLng(lat, lng);
                    mMap.addMarker(new MarkerOptions().position(nuevo).title("Marcador en " + lat + " latitud, " + lng + " longitud"));
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this, "Marcador en " + lat + " latitud, " + lng + " longitud", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    //ej3
    public void ejercicio3() {

        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.ejercicio3);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        aceptar = dialog.findViewById(R.id.b_aceptar);
        cancelar = dialog.findViewById(R.id.b_cancelar);
        etdistancia = dialog.findViewById(R.id.et_distancia);
        etrumbo = dialog.findViewById(R.id.et_rumbo);
        dirRumboSpinner = dialog.findViewById(R.id.spinner_dir_rumbo);

        //lo trae al frente
        dialog.show();

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double dist = Double.parseDouble(etdistancia.getText().toString());
                double rum = Double.parseDouble(etrumbo.getText().toString());
                String dirRumbo = dirRumboSpinner.getSelectedItem().toString();

                ultima = new LatLng(dist, rum);
                previa = new LatLng(0, 0);

                posiciones.add(ultima);
                if (ultima != null) {

                    double distanciaConRespectoUltimaPosicion = Double.parseDouble(etdistanciaConRespectoUltimaPosicion.getText().toString());
                    double rumboEnGrados = getRumboEnGrados(dirRumbo);

                    LatLng nuevaPosicion = SphericalUtil.computeOffset(ultima, distanciaConRespectoUltimaPosicion, rumboEnGrados);
                    posiciones.add(nuevaPosicion);

                    distancia = SphericalUtil.computeDistanceBetween(ultima, previa) + distanciaConRespectoUltimaPosicion;
                    distanciaTotal += distancia / 1000;

                    rum = SphericalUtil.computeHeading(previa, ultima);
                    if (rum < 0) rum += 360;

                    previa = ultima;
                    ultima = nuevaPosicion;

                    MarkerOptions markerOptions = new MarkerOptions().position(nuevaPosicion);
                    mMap.addMarker(markerOptions);
                    dialog.dismiss();

                    DecimalFormat df = new DecimalFormat("#.##");
                    String distanciaFormat = df.format(distancia / 1000);

                    Toast.makeText(MainActivity.this, "Distancia: " + distanciaFormat + " km Rumbo: " + rum, Toast.LENGTH_SHORT).show();

                    PolylineOptions polylineOptions = new PolylineOptions().addAll(posiciones).color(Color.RED);
                    mMap.addPolyline(polylineOptions);
                }

            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    //ej5
    public void borrarPosicionesMapa(){
        mMap.clear();
        Toast.makeText(this,"Posiciones borradas",Toast.LENGTH_SHORT).show();

    }

    //obtener rumbo
    private double getRumboEnGrados(String rumbo) {
        double rumboEnGrados = 0;

        switch(rumbo) {
            case "Norte":
                rumboEnGrados = 0;
                break;
            case "Noreste":
                rumboEnGrados = 45;
                break;
            case "Este":
                rumboEnGrados = 90;
                break;
            case "Sureste":
                rumboEnGrados = 135;
                break;
            case "Sur":
                rumboEnGrados = 180;
                break;
            case "Suroeste":
                rumboEnGrados = 225;
                break;
            case "Oeste":
                rumboEnGrados = 270;
                break;
            case "Noroeste":
                rumboEnGrados = 315;
                break;
        }

        return rumboEnGrados;
    }

}
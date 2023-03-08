package com.example.probarmapa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import com.example.probarmapa.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MapsActivity extends  AppCompatActivity implements OnMapReadyCallback,LocationListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private String provider;
    private LocationManager locationManager;
    private Location referencia;
    private double latitud,longitud,distancia,distanciaTotal;
    EditText etlongitud,etlatitud,etdistancia,etrumbo;
    Button b_aceptar, b_cancelar;
    Dialog dialog;
    private ArrayList<LatLng> posiciones = new ArrayList<>();
    private LatLng previa, siguiente, ultima;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //TOOLBAR
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Mapa Rafa");
//*****************************END TOOLBAR*********************************************************

        //UBICACION
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        //false se establece para que no esté activo permanentemente
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
//*******************************END UBICACION*************************************************************//

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }//end_onCreate


        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;

            LatLng defaul = new LatLng(0, 0);
            LatLng posicion1 = new LatLng(53.499424, 1.873112);
            mMap.addMarker(new MarkerOptions().position(posicion1).title("Marker in 53.499424/1.873112"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(defaul));


        }//end_onMapReady





    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return true;
    }//end_onCreateOptionsMenu


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.posicionLogLat:
                posicionLogCat();
                break;
            case R.id.borrarPosiciones:
                borrarPosiciones();
                break;
            case R.id.ubicacionActual:
                ubicacionActual();
                break;
            case R.id.posicionDistRumbo:
                posicionDistRumbo();
                break;

        }
        return true;
    }//end_onOptionsItemSelected



    public void posicionDistRumbo(){


        dialog = new Dialog(MapsActivity.this);
        dialog.setContentView(R.layout.dialog_dist_rumbo);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);


        b_aceptar = dialog.findViewById(R.id.b_aceptar);
        b_cancelar = dialog.findViewById(R.id.b_cancelar);
        etdistancia = dialog.findViewById(R.id.et_distancia);
        etrumbo = dialog.findViewById(R.id.et_rumbo);

        b_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double dist= Double.parseDouble(etdistancia.getText().toString());
                double rum= Double.parseDouble(etrumbo.getText().toString());

                ultima = new LatLng(dist, rum);
                previa = new LatLng(0, 0);

                posiciones.add(ultima);
                if (ultima != null) {

                    dist = SphericalUtil.computeDistanceBetween(ultima, previa);
                    dist /= 1000;
                    distanciaTotal += dist;
                    rum=SphericalUtil.computeHeading(previa, ultima);
                    if (rum<0)rum+=360;
                    previa = ultima;
                    MarkerOptions markerOptions = new MarkerOptions().position(ultima);
                    mMap.addMarker(markerOptions);
                    dialog.dismiss();

                    DecimalFormat df = new DecimalFormat("#.##");
                    String distanciaFormat = df.format(dist);

                    Toast.makeText(MapsActivity.this, "Distancia: " + distanciaFormat+ "Rumbo: "+rum, Toast.LENGTH_SHORT).show();

                    PolylineOptions polylineOptions = new PolylineOptions().addAll(posiciones).color(Color.RED);
                    mMap.addPolyline(polylineOptions);


                }

            }
        });

        b_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();



    }//end_posicionDistRumbo




    public void posicionLogCat(){

    dialog = new Dialog(MapsActivity.this);
    dialog.setContentView(R.layout.dialog_lat_long);
    dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);


    b_aceptar = dialog.findViewById(R.id.b_aceptar);
    b_cancelar = dialog.findViewById(R.id.b_cancelar);
    etlatitud = dialog.findViewById(R.id.et_rumbo);
    etlongitud = dialog.findViewById(R.id.et_distancia);

  b_aceptar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

          Double lat = Double.valueOf(etlatitud.getText().toString());
          Double lng = Double.valueOf(etlongitud.getText().toString());



          LatLng nuevo = new LatLng(lat, lng);
          mMap.addMarker(new MarkerOptions().position(nuevo).title("Marker in "+lat+" latitud, "+lng+" longitud"));
          dialog.dismiss();

          Toast.makeText(MapsActivity.this,"Marker in "+lat+" latitud, "+lng+" longitud",Toast.LENGTH_SHORT).show();


      }
  });

  b_cancelar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          dialog.dismiss();
      }
  });

            dialog.show();

    }//end_posicionLogCat


    public void borrarPosiciones(){

        mMap.clear();
        Toast.makeText(MapsActivity.this,"Posiciones borradas",Toast.LENGTH_SHORT).show();

    }//end_borrarPosiciones


    public void ubicacionActual(){

    latitud=referencia.getLatitude();
    longitud=referencia.getLongitude();

    LatLng actual = new LatLng(latitud, longitud);
    mMap.addMarker(new MarkerOptions().position(actual).title("Marker in ACTUAL"));
        Toast.makeText(MapsActivity.this,"Marker in UBICACION ACTUAL",Toast.LENGTH_SHORT).show();

    }//end_ubicacionActual


    @Override
    public void onLocationChanged(@NonNull Location location) {}




}
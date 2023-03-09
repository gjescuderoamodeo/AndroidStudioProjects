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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.recuexamenmapas_guillermojoseescuderoamodeo.databinding.ActivityMainBinding;
import com.google.android.gms.maps.model.Polyline;
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
    private LatLng previa, ultima;

    private Spinner spinnerRumbo;
    private String[] rumboOptions = {"Norte", "Noreste", "Este", "Sureste", "Sur", "Suroeste", "Oeste", "Noroeste"};



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
                ubicacionActual();
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
        //al estar listo el mapa, se añade posicion por defecto
        LatLng defecto = new LatLng(0, 0);
        //posiciones.add(defecto);
        //mMap.addMarker(new MarkerOptions().position(defecto).title("marcador en 0/0"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(defecto));
    }

    //ej2
    public void ejercicio2() {
        dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.ejercicio2);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        aceptar = dialog.findViewById(R.id.aceptar);
        cancelar = dialog.findViewById(R.id.cancelar);
        etlatitud = dialog.findViewById(R.id.latitud);
        etlongitud = dialog.findViewById(R.id.longitud);

        //lo trae al frente
        dialog.show();

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etlongitud.getText()!=null && etlatitud!=null){
                    Double lat = Double.valueOf(etlatitud.getText().toString());
                    Double lng = Double.valueOf(etlongitud.getText().toString());
                    LatLng nuevo = new LatLng(lng, lat);
                    posiciones.add(nuevo);
                    mMap.addMarker(new MarkerOptions().position(nuevo).title("Marcador en " + lng + " longitud" + lat + " latitud"));
                    dialog.dismiss();
                    Toast.makeText(MainActivity.this, "Marcador en " + lng + " longitud" + lat + " latitud" , Toast.LENGTH_SHORT).show();

                    //Si añado más, se añadirá linea
                    if(posiciones.size()>=2) {
                        mMap.addPolyline(new PolylineOptions()
                                .add(posiciones.get(posiciones.size() - 1), posiciones.get(posiciones.size() - 2))
                                .color(Color.GREEN)
                                .width(10f));
                    }

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

        aceptar = dialog.findViewById(R.id.aceptar);
        cancelar = dialog.findViewById(R.id.cancelar);
        etdistancia = dialog.findViewById(R.id.distancia);

        etrumbo = dialog.findViewById(R.id.rumbo);
        Spinner spinner = (Spinner) findViewById(R.id.dir_rumbo);

        //lo trae al frente
        dialog.show();

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double dist = Double.parseDouble(etdistancia.getText().toString());
                double rum = Double.parseDouble(etrumbo.getText().toString());

                ultima = new LatLng(dist, rum);
                previa = new LatLng(0, 0);

                posiciones.add(ultima);
                //en caso de que no hayan aún marcadores
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
                    Toast.makeText(MainActivity.this, "Distancia: " + dist+ "Rumbo: "+rum, Toast.LENGTH_SHORT).show();

                    //LatLng latLng3 = new LatLng(53.979900, -0.757300);
                    //LatLng latLng2 = new LatLng(40.7128, -74.0060);

                    if(posiciones.size()<=2) {
                        mMap.addPolyline(new PolylineOptions()
                                .add(posiciones.get(posiciones.size() - 1), posiciones.get(posiciones.size() - 2))
                                .color(Color.GREEN)
                                .width(10f));
                    }else{
                        mMap.addPolyline(new PolylineOptions()
                                .add(posiciones.get(posiciones.size() - 1), posiciones.get(posiciones.size() - 2))
                                .color(Color.YELLOW)
                                .width(10f));
                    }
                }
            }
        });


                /*
                Spinner dirRumboSpinner =(Spinner)findViewById(R.id.dir_rumbo);
                dirRumboSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String dirRumbo = parent.getItemAtPosition(position).toString();
                        // Utiliza el valor de dirRumbo como necesites
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // No hacer nada si no se ha seleccionado ninguna opción
                    }
                });

                String dirRumbo = dirRumboSpinner.getSelectedItem().toString();

                dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.ejercicio3);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

                aceptar = dialog.findViewById(R.id.b_aceptar);
                cancelar = dialog.findViewById(R.id.b_cancelar);
                etdistancia = dialog.findViewById(R.id.et_distancia);
                etrumbo = dialog.findViewById(R.id.et_rumbo);

                ultima = new LatLng(dist, rum);
                previa = new LatLng(0, 0);

                posiciones.add(ultima);
                if (ultima != null) {

                    double distanciaConRespectoUltimaPosicion = 22.2;
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
        });*/

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
        posiciones.clear();
        Toast.makeText(this,"Posiciones borradas",Toast.LENGTH_SHORT).show();

    }

    //ej4
    public void ubicacionActual(){

        latitud=referencia.getLatitude();
        longitud=referencia.getLongitude();

        LatLng actual = new LatLng(latitud, longitud);
        mMap.addMarker(new MarkerOptions().position(actual).title("marcador en posicion actual"));

        posiciones.add(actual);

        if(posiciones.size()>1) {
            mMap.addPolyline(new PolylineOptions()
                    .add(posiciones.get(posiciones.size() - 1), posiciones.get(posiciones.size() - 2))
                    .color(Color.MAGENTA)
                    .width(20f));
        }


        Toast.makeText(MainActivity.this,"ubicacion actual cogida",Toast.LENGTH_SHORT).show();

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
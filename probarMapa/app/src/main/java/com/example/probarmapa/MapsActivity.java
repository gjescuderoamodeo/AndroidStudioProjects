package com.example.probarmapa;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.probarmapa.databinding.ActivityMapsBinding;

import android.view.Menu;
import android.view.MenuItem;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

//intento menu
/*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Manejar la selección de opciones de menú aquí
        switch (item.getItemId()) {
            case R.id.menu_item1:
                // colocar posicionLogLat
                //posicion 53.499424, 1.873112



                return true;
            case R.id.menu_item2:
                // Acción para la opción 2
                return true;
            case R.id.menu_item3:
                // Acción para la opción 3
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;
    private Marker mMarker1;
    private Marker mMarker2;
    private Polyline Polyline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // Obtener el fragmento del mapa y notificar cuando el mapa está listo para ser usado
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                    if (mMarker1 == null) {
                        mMarker1 = mMap.addMarker(new MarkerOptions().position(latLng));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 6));
                        //Añadir marcador a latituds y longitud dada
                        mMarker2 = mMap.addMarker(new MarkerOptions().position(new LatLng(53.979900, -0.757300)).title("Punto 2"));
                        mMarker2.setDraggable(true);
                        mMarker2.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                        LatLng latLng3 = new LatLng(53.979900, -0.757300);
                        LatLng latLng2 = new LatLng(40.7128, -74.0060);
                        Marker mMarker3 = mMap.addMarker(new MarkerOptions().position(latLng3).title("Punto 3"));
                        //Dibujar linea
                        Polyline = mMap.addPolyline(new PolylineOptions().add(latLng, latLng2, latLng3));
                        //Calcular distancia entre 2 puntos
                        calculateDistance();
                    } else if (mMarker2 == null) {
                        mMarker2 = mMap.addMarker(new MarkerOptions().position(latLng).title("Punto 2"));
                    } else {
                        mMarker1.setPosition(mMarker2.getPosition());
                        mMarker2.setPosition(latLng);
                    }
                    calculateDistance();
                }
            }
        };
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(this);
        mMap.getUiSettings().setCompassEnabled(true);

        // Pedir permiso de ubicación
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            startLocationUpdates();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocationUpdates();
            } else {
                Toast.makeText(this, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.requestLocationUpdates(locationRequest, mLocationCallback, null);
    }

    //calcular distancia
    private void calculateDistance() {
        if (mMarker1 == null || mMarker2 == null) {
            Toast.makeText(this, "seleccione dos puntos", Toast.LENGTH_SHORT).show();
            return;
        }
        LatLng pos1 = mMarker1.getPosition();
        LatLng pos2 = mMarker2.getPosition();
        float[] results = new float[1];
        Location.distanceBetween(pos1.latitude, pos1.longitude, pos2.latitude, pos2.longitude, results);
        float distance = results[0];
        String distanceStr = String.format("%.2f", distance / 1000) + " km";
        Toast.makeText(this, "La distancia entre los dos puntos es: " + distanceStr, Toast.LENGTH_LONG).show();
    }

    //punto fijo (ejercicio1) y posicion 2
    //luego seleciono un tercer punto y calculo la distancia entre el segundo punto y el tercero
    @Override
    public void onMapClick(LatLng latLng) {
        if (mMarker2 == null) {
            mMarker2 = mMap.addMarker(new MarkerOptions().position(latLng).title("Punto 2"));
            calculateDistance();
        } else {
            mMarker2.setPosition(latLng);
            calculateDistance();
        }
    }
}



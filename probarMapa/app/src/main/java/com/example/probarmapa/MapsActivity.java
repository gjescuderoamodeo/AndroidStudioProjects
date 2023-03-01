package com.example.probarmapa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.probarmapa.databinding.ActivityMapsBinding;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
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

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;
    private Marker mMarker1;
    private Marker mMarker2;
    private Polyline mPolyline;

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
                    } else if (mMarker2 == null) {
                        mMarker2 = mMap.addMarker(new MarkerOptions().position(latLng));
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
        mFusedLocationClient.requestLocationUpdates(locationRequest, mLocationCallback, null);
    }

    private void calculateDistance() {
        if (mMarker1 == null || mMarker2 == null) {
            Toast.makeText(this, "Por favor, seleccione dos puntos en el mapa", Toast.LENGTH_SHORT).show();
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

    //version 1 punto fijo (mi posicion) y posicion 2
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

    //version 2 puntos

    /*
    @Override
    public void onMapClick(LatLng latLng) {
        if (mMarker1 == null) {
            mMarker1 = mMap.addMarker(new MarkerOptions().position(latLng).title("Punto 1"));
        } else if (mMarker2 == null) {
            mMarker2 = mMap.addMarker(new MarkerOptions().position(latLng).title("Punto 2"));
        } else {
            mMarker1.remove();
            mMarker1 = mMarker2;
            mMarker2 = mMap.addMarker(new MarkerOptions().position(latLng).title("Punto 2"));
        }
        calculateDistance();
    }*/
}





/*public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationCallback mLocationCallback;
    private Marker mMarker;

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
                    if (mMarker == null) {
                        mMarker = mMap.addMarker(new MarkerOptions().position(latLng));
                    } else {
                        mMarker.setPosition(latLng);
                    }
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
                }
            }
        };
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Pedir permiso de ubicación
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            startLocationUpdates();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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
        mFusedLocationClient.requestLocationUpdates(locationRequest, mLocationCallback, null);
    }
}*/
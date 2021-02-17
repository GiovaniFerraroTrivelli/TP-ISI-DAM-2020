package isi.dam.sendmeal.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.SphericalUtil;

import java.util.Random;

import isi.dam.sendmeal.R;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener {
    private GoogleMap myMap;
    PolylineOptions linea;
    Polyline polyline;
    LatLng posicionOriginal;
    LatLng nuevaPosicion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    9999);
            return;
        }
        setupMap();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 9999 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            setupMap();
        }
    }

    @SuppressLint("MissingPermission")
    public void setupMap(){
        myMap.setMyLocationEnabled(true);
        myMap.setOnMyLocationButtonClickListener(this);
        myMap.getUiSettings().setMyLocationButtonEnabled(true);
        myMap.getUiSettings().setZoomControlsEnabled(true);
        myMap.getUiSettings().setCompassEnabled(true);
        myMap.getUiSettings().setRotateGesturesEnabled(true);
        myMap.getUiSettings().setTiltGesturesEnabled(true);
        myMap.setMinZoomPreference(15.0f);

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location dir = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        LatLng dirltlng = new LatLng(dir.getLatitude(), dir.getLongitude());

        myMap.moveCamera(CameraUpdateFactory.newLatLng(dirltlng));

        Random r = new Random();
        int direccionRandomEnGrados = r.nextInt(360);
        int distanciaMinima = 100;
        int distanciaMaxima = 1000;
        int distanciaRandomEnMetros = r.nextInt(distanciaMaxima - distanciaMinima) + distanciaMinima;

        posicionOriginal = dirltlng;

        nuevaPosicion = SphericalUtil.computeOffset(
                posicionOriginal,
                distanciaRandomEnMetros,
                direccionRandomEnGrados
        );

        myMap.addMarker(new MarkerOptions()
                .position(nuevaPosicion)
                .title("Restaurante"));
        myMap.moveCamera(CameraUpdateFactory.newLatLng(nuevaPosicion));

        linea = new PolylineOptions().add(posicionOriginal).add(nuevaPosicion).color(0xFF000000);
        polyline = myMap.addPolyline(linea);

    }



    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    public void onClickConfirmarCoordenadas(View view) {
        Intent intent = new Intent();
        intent.putExtra("Coordenadas", nuevaPosicion);
        MapActivity.this.setResult(Activity.RESULT_OK, intent);
        MapActivity.this.finish();
    }
}
package com.example.slide13_multimedia_location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class LocationActivity extends AppCompatActivity implements IBaseGpsListener {
    TextView locationValues;
    private static final int PERMISSION_LOCATION = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        /** TODO: Định vị bằng GPS, Cell_ID hoặc Wifi */

        locationValues = findViewById(R.id.txtValues);
        // Check Location Permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_LOCATION);
        } else {
            showLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showLocation();
            }
        } else {
            Toast.makeText(LocationActivity.this, "Permission not granted!", Toast.LENGTH_SHORT).show();
            finish();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @SuppressLint("MissingPermission")
    private void showLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Check if GPS enabled
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // Start locating
            locationValues.setText("Loading location...");
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        } else {
            // Enable GPA
            Toast.makeText(LocationActivity.this, "Enable GPS!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.multimedia) {
            Intent intent = new Intent(LocationActivity.this, MultimediaActivity.class);
            startActivity(intent);
        } else if (id == R.id.location) {
            Intent intent = new Intent(LocationActivity.this, LocationActivity.class);
            startActivity(intent);
        } else if (id == R.id.ggmaps) {
            Intent intent = new Intent(LocationActivity.this, GgMapsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(Location location) {
        // update location
        locationValues.setText(hereLocation(location));
    }

    /**
     * show location as string
     * @param location
     * @return
     */
    private String hereLocation(Location location) {
        return "Lat: " + location.getLatitude() + "\nLong: " + location.getLongitude();
    }

    @Override
    public void onProviderDisabled(String provider) {
        // empty
    }

    @Override
    public void onProviderEnabled(String provider) {
        // empty
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // empty
    }

    @Override
    public void onGpsStatusChanged(int event) {
        // empty
    }
}
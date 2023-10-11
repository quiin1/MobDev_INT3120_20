package com.example.slide12devices_sensors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**  TODO: sensors list [of a given type] */
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        List<Sensor> deviceSensorsGravity = sensorManager.getSensorList(Sensor.TYPE_GRAVITY);
        List<Sensor> deviceSensorsLinearA = sensorManager.getSensorList(Sensor.TYPE_LINEAR_ACCELERATION);
        Toast.makeText(this, "Device Sensors" + deviceSensors.toString(), Toast.LENGTH_SHORT).show();
        Log.i("Device Sensors", deviceSensors.toString());

        /** TODO: determine whether a specific type of sensor exists on a device */
        if (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {
            // Success! There's a magnetometer.
        } else {
            // Failure! No magnetometer.
        }

        /** TODO: test sensor Accelerometer */
        if (sensorManager != null) {
            Sensor accleroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (accleroSensor != null) {
                sensorManager.registerListener(this, accleroSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        } else {
            Toast.makeText(this, "Sensor service not detected.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        ((TextView) findViewById(R.id.txtValues)).setText("x: " + sensorEvent.values[0] + ",\n"
                                                        + "y: " + sensorEvent.values[1] + ",\n"
                                                        + "z: " + sensorEvent.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sensor) {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.network) {
            Intent intent = new Intent(MainActivity.this, NetworkActivity.class);
            startActivity(intent);
        } else if (id == R.id.telephony) {
            Intent intent = new Intent(MainActivity.this, TelephonyActivity.class);
            startActivity(intent);
        } else if (id == R.id.camera) {
            Intent intent = new Intent(MainActivity.this, CameraActivity.class);
            startActivity(intent);
        } else if (id == R.id.bluetooth) {
            Intent intent = new Intent(MainActivity.this, BluetoothActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
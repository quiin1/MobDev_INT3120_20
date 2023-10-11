package com.example.slide12devices_sensors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class BluetoothActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        /** TODO: 5. Discovery Bluetooth devices */
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        // Get SCAN MODE
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.BLUETOOTH_SCAN) != PackageManager.PERMISSION_GRANTED) {
            // TO-DO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        int scanMode = bluetoothAdapter.getScanMode();
        Toast.makeText(this, "scan mode: " + scanMode, Toast.LENGTH_SHORT).show();

        // Discovery
        if (bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.startDiscovery();
        }


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
            Intent intent = new Intent(BluetoothActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.network) {
            Intent intent = new Intent(BluetoothActivity.this, NetworkActivity.class);
            startActivity(intent);
        } else if (id == R.id.telephony) {
            Intent intent = new Intent(BluetoothActivity.this, TelephonyActivity.class);
            startActivity(intent);
        } else if (id == R.id.camera) {
            Intent intent = new Intent(BluetoothActivity.this, CameraActivity.class);
            startActivity(intent);
        } else if (id == R.id.bluetooth) {
            Intent intent = new Intent(BluetoothActivity.this, BluetoothActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
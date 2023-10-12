package com.example.slide12devices_sensors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

/** TODO: FIX 5. Discovery Bluetooth devices */
// https://www.youtube.com/watch?v=KfM5N6m10kY
public class BluetoothActivity extends AppCompatActivity {
    Button btnTurnOn, btnTurnOff, btnShowList;
    TextView listDevicesView;
    Set<BluetoothDevice> ad;
    private static final int REQUEST_ENABLE_BLUETOOTH = 2;
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        btnTurnOn = findViewById(R.id.btnTurnOn);
        btnTurnOff = findViewById(R.id.btnTurnOff);
        btnShowList = findViewById(R.id.btnShowList);
        listDevicesView = findViewById(R.id.textView);

        // Create object of BluetoothAdapter class
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter == null) {
            Toast.makeText(BluetoothActivity.this, "Bluetooth is not supported!!!", Toast.LENGTH_LONG).show();
        } else {
            // Get Scan Mode
            int scanMode = bluetoothAdapter.getScanMode();
            Toast.makeText(this, "scan mode: " + scanMode, Toast.LENGTH_SHORT).show();
        }

        // Enable Bluetooth
        btnTurnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!bluetoothAdapter.isEnabled()) {
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intent, REQUEST_ENABLE_BLUETOOTH);
                }
            }
        });

        // Disable Bluetooth
        btnTurnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bluetoothAdapter.disable();
            }
        });

        // Show the list of Bonded Bluetooth devices
        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder listDevicesSb = new StringBuilder();
                ad = bluetoothAdapter.getBondedDevices();
                for (BluetoothDevice temp: ad) {
                    listDevicesSb.append("\n" + temp.getName() + "\n");
                }
                listDevicesView.setText(listDevicesSb.toString());
            }
        });
        // Discovery
//        if (bluetoothAdapter.isEnabled()) {
//            bluetoothAdapter.startDiscovery();
//        }
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
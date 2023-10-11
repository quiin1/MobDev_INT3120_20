package com.example.slide12devices_sensors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class NetworkActivity extends AppCompatActivity {
    ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        /** TODO: 2.1. Get Network Info */
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        NetworkInfo wifiNetwork = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo vpnNetwork = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_VPN);
        Log.i("2.1. Get Network Info", "NETWORK INFO active: " + activeNetwork);
        Log.i("2.1. Get Network Info", "NETWORK INFO wifi: " + wifiNetwork);
        Log.i("2.1. Get Network Info", "NETWORK INFO vpn: " + vpnNetwork);

        /** TODO: 2.2. Theo dõi kết nối mạng và get Info */
        final NetworkRequest request =
                new NetworkRequest.Builder()
                        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                        .build();
        final ConnectivityManager connectivityManager =
                this.getSystemService(ConnectivityManager.class);
        final ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {
            @Override
            public void onAvailable(Network network) {
            }

            @Override
            public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                    WifiInfo wifiInfo = (WifiInfo) networkCapabilities.getTransportInfo();
                    Toast.makeText(NetworkActivity.this, "wifi info: " + wifiInfo, Toast.LENGTH_LONG).show();
                    Log.i("2.2. Get Wifi Info", "WIFI INFO: " + wifiInfo.toString());
                }
            }
            // etc.
        };
        connectivityManager.requestNetwork(request, networkCallback); // For request
        connectivityManager.registerNetworkCallback(request, networkCallback); // For listen

        /** TODO: 2.3. Wifi configuration */
        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        // TODO: 2.3.1. List available configurations
        // Permission check
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TO-DO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        // Get a list of available configurations
        List<WifiConfiguration> configurations = wifiManager.getConfiguredNetworks();
        Toast.makeText(this, "WIFI List Configs: " + configurations.toString(), Toast.LENGTH_LONG).show();
        Log.i("Wifi Configuration", "WIFI List Configs: " + configurations.toString());

        // TODO: 2.3.2. Use a specific configuration
        // Get the network ID for the first one.
        if (configurations.size() > 0) {
            int netID = configurations.get(0).networkId;
            // Enable that network.
            boolean disableAllOthers = true;
            wifiManager.enableNetwork(netID, disableAllOthers);
        }

        // TODO: 2.3.3. Tạo thiết lập Wifi

        // TODO: 2.3.4. Sửa các thiết lập Wifi
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
            Intent intent = new Intent(NetworkActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.network) {
            Intent intent = new Intent(NetworkActivity.this, NetworkActivity.class);
            startActivity(intent);
        } else if (id == R.id.telephony) {
            Intent intent = new Intent(NetworkActivity.this, TelephonyActivity.class);
            startActivity(intent);
        } else if (id == R.id.camera) {
            Intent intent = new Intent(NetworkActivity.this, CameraActivity.class);
            startActivity(intent);
        } else if (id == R.id.bluetooth) {
            Intent intent = new Intent(NetworkActivity.this, BluetoothActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
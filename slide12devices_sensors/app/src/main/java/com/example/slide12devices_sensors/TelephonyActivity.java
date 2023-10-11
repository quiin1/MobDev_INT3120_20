package com.example.slide12devices_sensors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.CellInfo;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class TelephonyActivity extends AppCompatActivity {

    Button btnStartIntentActionDial;
    Button btnStartIntentSendTo;
    Button btnSmsManagerSendTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephony);

        /** TODO: 3.1. Khởi tạo cuộc gọi = Intent */
        btnStartIntentActionDial = findViewById(R.id.btnCall);
        btnStartIntentActionDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent whoYouGonnaCall = new Intent(Intent.ACTION_DIAL);
                whoYouGonnaCall.setData(Uri.parse("tel:555-2368"));
                startActivity(whoYouGonnaCall);
            }
        });

        /** TODO: 3.2. Truy cập attribute Telephony & trạng thái điện thoại */
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
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
        List<CellInfo> allCellInfo = telephonyManager.getAllCellInfo();
        Toast.makeText(this, "All Cell Info: " + allCellInfo.toString(), Toast.LENGTH_LONG).show();
        Log.i("All Cell Info", "ALL CELL INFO: " + allCellInfo.toString());

        /** TODO: 3.3. Theo dõi thay đổi trạng thái điện thoại */
        // TODO: 3.3.1. SMS/MMS gửi từ ứng dụng = Intent (Cách 1) & = SmsManager (Cách 2)
        btnStartIntentSendTo = findViewById(R.id.btnSendTo);
        btnStartIntentSendTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:555-2368"));
                smsIntent.putExtra("sms_body", "Press send to send me");
                startActivity(smsIntent);
            }
        });

        btnSmsManagerSendTo = findViewById(R.id.btnSmsManagerSendTo);
        btnSmsManagerSendTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SmsManager smsManager = SmsManager.getDefault();
                String sendTo = "5551234";
                String myMessage = "Android supports programmatic SMS messaging!";
                smsManager.sendTextMessage(sendTo, null, myMessage, null, null);
            }
        });

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
            Intent intent = new Intent(TelephonyActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.network) {
            Intent intent = new Intent(TelephonyActivity.this, NetworkActivity.class);
            startActivity(intent);
        } else if (id == R.id.telephony) {
            Intent intent = new Intent(TelephonyActivity.this, TelephonyActivity.class);
            startActivity(intent);
        } else if (id == R.id.camera) {
            Intent intent = new Intent(TelephonyActivity.this, CameraActivity.class);
            startActivity(intent);
        } else if (id == R.id.bluetooth) {
            Intent intent = new Intent(TelephonyActivity.this, BluetoothActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
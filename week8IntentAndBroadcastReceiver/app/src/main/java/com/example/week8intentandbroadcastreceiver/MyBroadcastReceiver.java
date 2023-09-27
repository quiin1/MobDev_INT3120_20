package com.example.week8intentandbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(MyBroadcastReceiver.class.getSimpleName(), "Airplane mode change!!!!");
        Toast.makeText(context, "Airplane mode change!!!!", Toast.LENGTH_LONG).show();
    }
}

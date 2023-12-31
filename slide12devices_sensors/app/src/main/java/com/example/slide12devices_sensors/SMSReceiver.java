package com.example.slide12devices_sensors;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: 3.3.2. SMS Receiver
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage[] messages = new SmsMessage[pdus.length];
            for (int i = 0; i < pdus.length; i++) {
                messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
            }
            for (SmsMessage message : messages) {
                String msg = message.getMessageBody();
                long when = message.getTimestampMillis();
                String from = message.getOriginatingAddress();
                Toast.makeText(context, from + ": " + msg, Toast.LENGTH_LONG).show();
            }
        }
    }
}

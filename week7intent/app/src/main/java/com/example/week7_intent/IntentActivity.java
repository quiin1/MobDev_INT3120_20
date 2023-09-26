package com.example.week7_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class IntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        Intent searchIntent = new Intent(Intent.ACTION_WEB_SEARCH);
        searchIntent.putExtra(SearchManager.QUERY, "straight hitting golf clubs");

        Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:555-1234"));

        Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:5551234"));
        smsIntent.putExtra("sms body", "are we playing golf next Saturday?");

        Intent pictureIntent = new Intent();
        pictureIntent.setType("image/pictures/");
        pictureIntent.setAction(Intent.ACTION_GET_CONTENT);

        String myData = "content://contacts/people/";
        Intent contactsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(myData));

        String geoCode = "geo:0,0?q=1860+east+18th+street+cleveland+oh";
        String geoCode2 = "geo:41.5020952,-81.6789717";
        Intent geoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoCode));


    }
}
package com.example.week7_intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MusicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        Intent musicIntent = new Intent("android.intent.action.MUSIC_PLAYER");
        startActivity(musicIntent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout_menu, menu); // inflate your menu resource (defined in XML) into the Menu provided in the callback.
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.intent) {
            Intent intent = new Intent(MusicActivity.this, IntentActivity.class);
            startActivity(intent);
        } else if (id == R.id.sendMessages) {
            Intent intent = new Intent(MusicActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.music) {
            Intent intent = new Intent(MusicActivity.this, MusicActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
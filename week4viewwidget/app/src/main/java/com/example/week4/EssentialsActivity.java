package com.example.week4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

public class EssentialsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.essentials_constraint);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu); // inflate your menu resource (defined in XML) into the Menu provided in the callback.

        MenuItem menuItem = menu.findItem(R.id.list_view);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.list_view) {
            Intent intent = new Intent(EssentialsActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.donation) {
            Intent intent = new Intent(EssentialsActivity.this, DonationActivity.class);
            startActivity(intent);
        } else if (id == R.id.essentials) {
            Intent intent = new Intent(EssentialsActivity.this, EssentialsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
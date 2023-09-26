package com.example.week4;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

public class DonationActivity extends AppCompatActivity {
    private NumberPicker numberPicker;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donation_constraint);

        numberPicker = findViewById(R.id.numberPicker);
        numberPicker.setValue(999);
        numberPicker.setMaxValue(1999);
        numberPicker.setMinValue(0);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                Toast.makeText(DonationActivity.this, "Selected: " + i, Toast.LENGTH_SHORT).show();
            }
        });

        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(20);
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
            Intent intent = new Intent(DonationActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.donation) {
            Intent intent = new Intent(DonationActivity.this, DonationActivity.class);
            startActivity(intent);
        } else if (id == R.id.essentials) {
            Intent intent = new Intent(DonationActivity.this, EssentialsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

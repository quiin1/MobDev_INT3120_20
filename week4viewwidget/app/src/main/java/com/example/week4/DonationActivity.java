package com.example.week4;

import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
}

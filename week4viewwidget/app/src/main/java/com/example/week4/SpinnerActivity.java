package com.example.week4;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SpinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] items = {"Android", "Apple", "IOS", "Window7", "MacOS", "..."};
    TextView selection;
    Spinner spinner;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner);

        selection = findViewById(R.id.selection);
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selection.setText(items[position]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        selection.setText("");
    }
}

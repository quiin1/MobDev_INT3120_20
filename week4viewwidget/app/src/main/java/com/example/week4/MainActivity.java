package com.example.week4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView citiesList;
    private Spinner studentsSpinner;
    private GridView gridView;
    private AutoCompleteTextView edit;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        /*** 1. List View */
        citiesList = findViewById(R.id.citiesList);

        // Data
        ArrayList<String> cities = new ArrayList<>();
        cities.add("Zurich");
        cities.add("New York");
        cities.add("Berlin");
        cities.add("Moscow");
        cities.add("Madrid");

        // Data Adapter: ArrayAdapter
        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                cities
        );

        citiesList.setAdapter(citiesAdapter);

        /*** 2. Spinner (like dropdown) */
        studentsSpinner = findViewById(R.id.studentsSpinner);
        text = findViewById(R.id.text);

        /** 1st way **/
        // Data
        ArrayList<String> students = new ArrayList<>();
        students.add("Meisam");
        students.add("Brad");
        students.add("Sarah");
        students.add("Madeline");
        students.add("Tom");

        // Data Adapter
        ArrayAdapter<String> studentsAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item
        );
        studentsAdapter.add("Choose a name");
        studentsAdapter.addAll(students);

        studentsSpinner.setAdapter(studentsAdapter);
        studentsSpinner.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    // Spinner lost focus, set the default selection here
                    studentsSpinner.setSelection(0);
                }
            }
        });
        studentsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    // Toast.makeText(MainActivity.this, students.get(position-1) + " Selected", Toast.LENGTH_SHORT).show();
                    text.setText(students.get(position-1));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                text.setText("");
            }
        });

        /** 2nd way */
        /*
         * use data from res>values>strings.xml
         * by: add attribute android:entries="@array/students" in Spinner tag in xml file
         */
//        studentsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, studentsSpinner.getSelectedItem() + " Selected", Toast.LENGTH_SHORT).show();
//                text.setText(studentsSpinner.getSelectedItem().toString());
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
//                text.setText("");
//            }
//        });


        /*** 3. Grid View */
        gridView = findViewById(R.id.gridView);

        ArrayAdapter<String> gvAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                cities
        );

        gridView.setAdapter(gvAdapter);

        /*** 4. AutoCompleteTextView */
        edit = findViewById(R.id.edit);

        // edit.addTextChangedListener((TextWatcher) this);
        edit.setAdapter(new ArrayAdapter<String>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                cities
        ));
    }
}
package com.example.week5_date_time_tabs;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DateTimeActivity extends AppCompatActivity {
    private TextView text;
    private Button btnDatePicker;
    private Button btnTimePicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_time);
        text = findViewById(R.id.lblDateAndTime);
        btnDatePicker = findViewById(R.id.btnDate);
        btnTimePicker = findViewById(R.id.btnTime);

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePickerDialog();
            }
        });

        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimePickerDialog();
            }
        });
    }
    private void openDatePickerDialog() {
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                text.setText(String.valueOf(year)+"/"+String.valueOf(month+1)+"/"+String.valueOf(day));
            }
        }, 2023,8,1);
        dialog.show();
    }

    private void openTimePickerDialog() {
        TimePickerDialog dialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hours, int minutes) {
                text.setText(String.valueOf(hours) + ":" + String.valueOf(minutes) + (minutes == 0 ? "0" : ""));
            }
        }, 00, 00, false);
        dialog.show();
    }
}

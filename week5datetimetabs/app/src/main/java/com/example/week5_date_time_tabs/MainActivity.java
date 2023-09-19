package com.example.week5_date_time_tabs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);

        TabHost tabs = (TabHost) findViewById(R.id.tabhost);
        tabs.setup();
        TabHost.TabSpec spec; // Khai báo 1 biến spec kiểu TabHost.TabSpec; sử dụng để cấu hình và tạo tab.

        spec = tabs.newTabSpec("tag1"); // spec = đối tượng TabSpec mới với tên tag1
        spec.setContent(R.id.tab1);         // gán nội dung cho tab1
        spec.setIndicator("1-Clock");       // đặt tiêu đề hiển thị của tab
        tabs.addTab(spec);          // thêm tab đã được cấu hình vào TabHost để hiển thị

        spec = tabs.newTabSpec("tag2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("2-Login", getResources().getDrawable(R.drawable.ic_menu_info));
        tabs.addTab(spec);

        tabs.setCurrentTab(0);

        Button btnGo = (Button) findViewById(R.id.btnGo);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                EditText txtPerson = (EditText) findViewById(R.id.txtPerson);
                String theUser = txtPerson.getText().toString();
                txtPerson.setText("Hola " + theUser);
            }
        });
    }
}

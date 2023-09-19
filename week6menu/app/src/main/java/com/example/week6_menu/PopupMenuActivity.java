package com.example.week6_menu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

public class PopupMenuActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** 3. Popup Menu */
        Button btnCheckMe = (Button) findViewById(R.id.btnCheckMe);
        Button btnPopupMenu = (Button) findViewById(R.id.btnPopupMenu);

        btnCheckMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnCheckMeClicked();
            }

            private void btnCheckMeClicked() {
                PopupMenu popupMenu = new PopupMenu(PopupMenuActivity.this, btnPopupMenu);
                popupMenu.inflate(R.menu.popup_menu);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return menuItemClicked(item);
                    }

                    private boolean menuItemClicked(MenuItem item) {
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }
}

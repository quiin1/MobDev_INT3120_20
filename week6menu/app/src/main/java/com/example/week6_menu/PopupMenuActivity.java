package com.example.week6_menu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class PopupMenuActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_popup);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu); // inflate your menu resource (defined in XML) into the Menu provided in the callback.
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Context) {
            Intent intent = new Intent(PopupMenuActivity.this, ContextMenuActivity.class);
            startActivity(intent);
        } else if (id == R.id.Popup){
            Intent intent = new Intent(PopupMenuActivity.this, PopupMenuActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

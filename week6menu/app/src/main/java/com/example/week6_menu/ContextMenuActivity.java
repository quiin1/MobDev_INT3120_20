package com.example.week6_menu;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ContextMenuActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** 2. Contextual Menu */
        TextView textFloatingContextMenu = findViewById(R.id.textFloatingContextMenu);
        registerForContextMenu(textFloatingContextMenu);
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.download) {
            Toast.makeText(this, "Download", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.open) {
            Toast.makeText(ContextMenuActivity.this, "Open", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.search) {
            Toast.makeText(ContextMenuActivity.this, "Search", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.share) {
            Toast.makeText(ContextMenuActivity.this, "Share", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onContextItemSelected(item);
    }
}

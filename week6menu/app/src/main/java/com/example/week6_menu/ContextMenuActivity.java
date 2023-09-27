package com.example.week6_menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ContextMenuActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_context);

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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu); // inflate your menu resource (defined in XML) into the Menu provided in the callback.
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Context) {
            Intent intent = new Intent(ContextMenuActivity.this, ContextMenuActivity.class);
            startActivity(intent);
        } else if (id == R.id.Popup){
            Intent intent = new Intent(ContextMenuActivity.this, PopupMenuActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

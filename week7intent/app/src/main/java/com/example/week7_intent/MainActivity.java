package com.example.week7_intent;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button buttonSendMessage;
    TextView textFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextFullName = findViewById(R.id.editText_fullName);
        textFeedback = findViewById(R.id.textView_feedback);

        buttonSendMessage = findViewById(R.id.button_sendMessage);
        buttonSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
            private void sendMessage() {
                String fullName = editTextFullName.getText().toString();
                String message = "Hello, Please say hello me!";

                Intent intent = new Intent(MainActivity.this, GreetingActivity.class); // != slide
                intent.putExtra("fullName", fullName);
                intent.putExtra("message", message);

                activityResultLauncher.launch(intent);
            }
        });
    }

    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        textFeedback.setText(intent.getStringExtra("feedback"));
                    }
                }
            }
    );
//            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//                super.onActivityResult(requestCode, resultCode, data);
//
//                if (resultCode == Activity.RESULT_OK && requestCode == MY_REQUEST_CODE) {
//                    String feedback = data.getStringExtra("feedback");
//                    textFeedback.setText(feedback);
//                } else {
//                    textFeedback.setText("!?");
//                }
//            }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout_menu, menu); // inflate your menu resource (defined in XML) into the Menu provided in the callback.

        MenuItem menuItem = menu.findItem(R.id.intent);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.intent) {
            Intent intent = new Intent(MainActivity.this, IntentActivity.class);
            startActivity(intent);
        } else if (id == R.id.sendMessages) {
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.example.week7_intent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Integer MY_REQUEST_CODE = 123;
    Button buttonSendMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextFullName = findViewById(R.id.editText_fullName);
        TextView textFeedback = findViewById(R.id.textView_feedback);

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

                startActivity(intent);
//                startActivityForResult(intent, MY_REQUEST_CODE);
            }

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
        });
    }
}

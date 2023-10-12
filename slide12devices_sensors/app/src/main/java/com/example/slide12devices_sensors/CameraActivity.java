package com.example.slide12devices_sensors;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class CameraActivity extends AppCompatActivity {
    Button btnStartIntentCamera;
    Button btnStartIntentVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        /** TODO: 4.1. Chụp ảnh bằng Intent hoặc dùng lớp Camera */
        // TODO: CODE thầy muốn chụp ảnh ko cần xác nhận mà lưu luôn / tự chụp tự lưu gì đó
        btnStartIntentCamera = findViewById(R.id.btnStartIntentCamera);
        btnStartIntentCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
            }
        });

        /** TODO: 4.2. Quay video = Intent hoặc lớp MediaRecorder */
        btnStartIntentVideo = findViewById(R.id.btnStartIntentVideo);
        btnStartIntentVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                startActivity(intent);
            }
        });

        /** TODO: FIX 4.3. Ghi âm = lớp MediaRecorder */
//        MediaRecorder recorder = new MediaRecorder();
//        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//        recorder.setOutputFile(PATH_NAME);
//        recorder.prepare();
//        recorder.start();   // Recording is now started
// ...
//        recorder.stop();
//        recorder.reset();   // You can reuse the object by going back to setAudioSource() step
//        recorder.release(); // Now the object cannot be reused
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sensor) {
            Intent intent = new Intent(CameraActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.network) {
            Intent intent = new Intent(CameraActivity.this, NetworkActivity.class);
            startActivity(intent);
        } else if (id == R.id.telephony) {
            Intent intent = new Intent(CameraActivity.this, TelephonyActivity.class);
            startActivity(intent);
        } else if (id == R.id.camera) {
            Intent intent = new Intent(CameraActivity.this, CameraActivity.class);
            startActivity(intent);
        } else if (id == R.id.bluetooth) {
            Intent intent = new Intent(CameraActivity.this, BluetoothActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
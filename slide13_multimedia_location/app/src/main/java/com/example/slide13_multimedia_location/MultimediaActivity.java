package com.example.slide13_multimedia_location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MultimediaActivity extends AppCompatActivity {
    Button btnPlayMusicFromLocal;
    Button btnPlayMusicFromInternalURI;
    Button btnPlayMusicFromStreaming;
    private class SongData{
        public String artist;
        public String title;
        public String path;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);

        /** TODO: Phát nhạc từ local */
        btnPlayMusicFromLocal = findViewById(R.id.btnPlayMusicFromLocal);
        btnPlayMusicFromLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = MediaPlayer.create(MultimediaActivity.this, R.raw.mozart);
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        stopMusic(mediaPlayer);
                    }
                });
            }
        });

        /** TODO: FIX Phát nhạc từ internal URI */
        btnPlayMusicFromInternalURI = findViewById(R.id.btnPlayMusicFromInternalURI);
        btnPlayMusicFromInternalURI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MultimediaActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(MultimediaActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        Toast.makeText(MultimediaActivity.this, "EXPLANATION", Toast.LENGTH_LONG).show();
                    } else {
                        ActivityCompat.requestPermissions(MultimediaActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                        Toast.makeText(MultimediaActivity.this, "EXPLANATION NO NEEDED", Toast.LENGTH_LONG).show();
                    }
                }

//                ContentResolver contentResolver = getContentResolver();
//                Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
//                Cursor songCursor = contentResolver.query(songUri, null, null, null, null);
//
//                ArrayList<SongData> songsList = new ArrayList<>();
//                if (songCursor != null && songCursor.moveToFirst()){
//                    int songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
//                    int songArtist = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
//                    do {
//                        String currentTitle = songCursor.getString(songTitle);
//                        String currentArtist = songCursor.getString(songArtist);
//                        String path = songCursor.getString(Integer.parseInt(MediaStore.Audio.Media.DATA));
//                        SongData songData = new SongData();
//                        songData.artist = currentArtist;
//                        songData.title = currentTitle;
//                        songData.path = "file:///" + path;
//                        songsList.add(songData);
//                    } while (songCursor.moveToNext());
//                }

                String songPath = Environment.getExternalStorageDirectory().getPath() + "/Music/Sway.mp3";
                File file = new File(songPath);
                Toast.makeText(MultimediaActivity.this, "file exists: " + file.exists() + ", can read: " + file.canRead(), Toast.LENGTH_LONG).show();
                Uri myUri = Uri.parse(songPath); // khởi tạo URI ở đây
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(MultimediaActivity.this, myUri);
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        stopMusic(mediaPlayer);
                    }
                });
            }
        });

        /** TODO: Phát nhạc từ nguồn streaming */
        // ⚠️ Phải thêm permission android.permission.INTERNET
        // & thêm android:usesCleartextTraffic="true" trong thẻ application
        btnPlayMusicFromStreaming = findViewById(R.id.btnPlayMusicFromStreaming);
        btnPlayMusicFromStreaming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://file-examples.com/storage/feaade38c1651bd01984236/2017/11/file_example_MP3_700KB.mp3"; // your URL here
                MediaPlayer mediaPlayer = new MediaPlayer();
//                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setAudioAttributes(
                        new AudioAttributes.Builder()
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .setUsage(AudioAttributes.USAGE_MEDIA)
                                .build()
                );
                try {
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepare(); // might take long! (for buffering, etc)
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        stopMusic(mediaPlayer);
                    }
                });
            }
        });

        /** TODO: FIX Phát nhạc trên nền */
        Intent serviceIntent = new Intent(MultimediaActivity.this, MusicService.class);
        startService(serviceIntent);
    }

    private void stopMusic(MediaPlayer mediaPlayer) {
        mediaPlayer.release();
        mediaPlayer = null;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.multimedia) {
            Intent intent = new Intent(MultimediaActivity.this, MultimediaActivity.class);
            startActivity(intent);
        } else if (id == R.id.location) {
            Intent intent = new Intent(MultimediaActivity.this, LocationActivity.class);
            startActivity(intent);
        } else if (id == R.id.ggmaps) {
            Intent intent = new Intent(MultimediaActivity.this, GgMapsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
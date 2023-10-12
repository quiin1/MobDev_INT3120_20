package com.example.slide13_multimedia_location;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.mozart);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer.start(); // Bắt đầu phát nhạc
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        mediaPlayer.stop(); // Dừng phát nhạc khi dịch vụ bị hủy
        mediaPlayer.release();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

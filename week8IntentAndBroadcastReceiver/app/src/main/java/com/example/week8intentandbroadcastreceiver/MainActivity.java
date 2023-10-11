package com.example.week8intentandbroadcastreceiver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private MyBroadcastReceiver broadcast;
    private boolean isReceiverRegistered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        broadcast = new MyBroadcastReceiver();
//        isReceiverRegistered = true;
//        // đăng ký lắng nghe action bật/tắt chế độ máy bay
//        IntentFilter filter = new IntentFilter("android.intent.action.AIRPLANE_MODE");
//        // truyền vào class MyBroadcastReceiver
//        // để thông báo với hệ thống là ỨD đang lắng nghe sự thay đổi của action mà ta vừa đăng ký
//        // và trả về kq qua hàm onReceive trong class broadcast
//        registerReceiver(broadcast, filter);
    }

    @Override
    /***
     * lưu ý: nếu sử dụng cách đăng ký bằng Java thì nhất định phải unregister
     * nếu ko sẽ bị lỗi kinh điển "Are you missing a call to unregisterReceiver()?"
     */
    protected void onStop() {
        super.onStop();
        if (isReceiverRegistered) {
            unregisterReceiver(broadcast);
            Log.d("Receiver", "Broadcast Receiver stop!!!");
        } else {
            Log.d("Receiver", "Broadcast Receiver is not available!");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.intent1) {
            Intent intent = new Intent(MainActivity.this, IntentMessageActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
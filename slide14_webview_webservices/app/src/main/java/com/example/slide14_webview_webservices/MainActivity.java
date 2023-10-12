package com.example.slide14_webview_webservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ForwardingListener;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** TODO: Add WebView */
        webView = (WebView) findViewById(R.id.webview);
        // load WebView;
        // setWebViewCLient() will replace the current handler that will receive various notifications and requests
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.google.com");
        // nếu WebView sử dụng JS -> setting enable JS
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    /** TODO: Quản lý lịch sử web (goBack) */
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
package com.example.slide14_webview_webservices;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {
    /** TODO: Navigation (user click link in WebView) */
    /**
     * Để chỉ tải một link nhất định, cần override shouldOverrideUrlLoading() của WebViewClient
     * @param view
     * @param url
     * @return
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (Uri.parse(url).getHost().equals("www.google.com")) {
            // Chỉ tải trang này trong WebView
            return false;
        }
        // Các link khác thì chạy Activity phù hợp để xử lý
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//        startActivity(intent);
        
        return true;
    }
}

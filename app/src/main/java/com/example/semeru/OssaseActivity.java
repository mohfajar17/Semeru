package com.example.semeru;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class OssaseActivity extends AppCompatActivity {

    private WebView webViewOssase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ossase);

        webViewOssase = (WebView) findViewById(R.id.webViewOssase);
        webViewOssase.getSettings().setJavaScriptEnabled(true);
        webViewOssase.setWebViewClient(new MyBrowser());
        webViewOssase.loadUrl("http://10.11.4.57/semeru/network-detail-ALL-underspec-network");
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.equals("http://10.11.4.57/semeru"))
                onBackPressed();
            else view.loadUrl(url);
            return true;
        }
    }
}

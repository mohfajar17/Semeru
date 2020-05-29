package com.example.semeru;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BroadbandActivity extends AppCompatActivity {

    private WebView webViewBroadband;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadband);

        webViewBroadband = (WebView) findViewById(R.id.webViewBroadband);
        webViewBroadband.getSettings().setJavaScriptEnabled(true);
        webViewBroadband.setWebViewClient(new MyBrowser());
        webViewBroadband.loadUrl("http://10.11.4.57/semeru/detail_ggn_broadband-ALL-alarm");
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
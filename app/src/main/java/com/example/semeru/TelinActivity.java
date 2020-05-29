package com.example.semeru;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.semeru.R;

public class TelinActivity extends AppCompatActivity {

    private WebView webViewTelin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telin);

        webViewTelin = (WebView) findViewById(R.id.webViewTelin);
        webViewTelin.getSettings().setJavaScriptEnabled(true);
        webViewTelin.setWebViewClient(new MyBrowser());
        webViewTelin.loadUrl("http://10.11.4.57/semeru/telin_network");
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

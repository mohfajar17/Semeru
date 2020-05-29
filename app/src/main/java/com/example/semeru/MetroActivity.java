package com.example.semeru;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.semeru.R;

public class MetroActivity extends AppCompatActivity {

    private WebView webViewMetro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metro);

        webViewMetro = (WebView) findViewById(R.id.webViewMetro);
        webViewMetro.getSettings().setJavaScriptEnabled(true);
        webViewMetro.setWebViewClient(new MyBrowser());
        webViewMetro.loadUrl("http://10.11.4.57/semeru/detail_ggn_metro-ALL-nok_alarm");
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.equals("http://10.11.4.57/semeru")){
                onBackPressed();
            } else if (url.equals("http://10.11.4.57/semeru/metro-export")){
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            } else view.loadUrl(url);
            return true;
        }
    }
}
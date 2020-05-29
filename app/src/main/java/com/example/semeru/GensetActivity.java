package com.example.semeru;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GensetActivity extends AppCompatActivity {

    private WebView webViewGenset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genset);

        webViewGenset = (WebView) findViewById(R.id.webViewGovernment);
        webViewGenset.getSettings().setJavaScriptEnabled(true);
        webViewGenset.setWebViewClient(new MyBrowser());
        webViewGenset.loadUrl("http://10.11.4.57/semeru/detail_ggn_osase-ALL-genset");
    }

    class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.equals("http://10.11.4.57/semeru"))
                onBackPressed();
            else if (url.toLowerCase().contains("http://10.96.196.211/semeru/ggn_osase")){
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            } else view.loadUrl(url);
            return true;
        }
    }
}

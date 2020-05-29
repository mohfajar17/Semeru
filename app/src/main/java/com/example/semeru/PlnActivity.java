package com.example.semeru;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PlnActivity extends AppCompatActivity {

    private WebView webViewPln;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pln);

        webViewPln = (WebView) findViewById(R.id.webViewPln);
        webViewPln.getSettings().setJavaScriptEnabled(true);
        webViewPln.setWebViewClient(new MyBrowser());
        webViewPln.loadUrl("http://10.11.4.57/semeru/detail_ggn_osase-ALL-pln");
    }

    private class MyBrowser extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.equals("http://10.11.4.57/semeru"))
                onBackPressed();
            else if (url.toLowerCase().contains("http://10.11.4.57/semeru/ggn_osase")){
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            } else view.loadUrl(url);
            return true;
        }
    }
}

package com.example.semeru;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class VvipActivity extends AppCompatActivity {

    private WebView webViewVvip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vvip);

        webViewVvip = (WebView) findViewById(R.id.webViewVvip);
        webViewVvip.getSettings().setJavaScriptEnabled(true);
        webViewVvip.setWebViewClient(new MyBrowser());
        webViewVvip.loadUrl("http://10.11.4.57/semeru/vvip");
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.equals("http://10.11.4.57/semeru")){
                onBackPressed();
            } else {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
            return true;
        }
    }
}

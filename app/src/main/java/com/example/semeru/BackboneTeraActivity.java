package com.example.semeru;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BackboneTeraActivity extends AppCompatActivity {

    private WebView webViewBackboneTera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backbone_tera);

        webViewBackboneTera = (WebView) findViewById(R.id.webViewBackboneTera);
        webViewBackboneTera.getSettings().setJavaScriptEnabled(true);
        webViewBackboneTera.setWebViewClient(new MyBrowser());
        webViewBackboneTera.loadUrl("http://10.11.4.57/semeru/ip_backcone_tera");
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.equals("http://10.11.4.57/semeru"))
                onBackPressed();
            else if (url.equals("http://10.11.4.57/symfony/ggn_backbone_tera")){
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            } else view.loadUrl(url);
            return true;
        }
    }
}

package com.example.semeru;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.semeru.R;

public class GlobalActivity extends AppCompatActivity {

    private WebView webViewGlobal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global);

        webViewGlobal = (WebView) findViewById(R.id.webViewGlobal);
        webViewGlobal.getSettings().setJavaScriptEnabled(true);
        webViewGlobal.setWebViewClient(new MyBrowser());
        webViewGlobal.loadUrl("http://10.11.4.57/semeru/network-detail-ALL-global-network");
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.equals("http://10.11.4.57/semeru"))
                onBackPressed();
            else if (url.toLowerCase().contains("https://nossa.telkom.co.id/maximo/ui/login?event=loadapp&value=incident&additionalevent=useqbe&additionaleventvalue=TICKETID=".toLowerCase())){
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
            else view.loadUrl(url);
            return true;
        }
    }
}

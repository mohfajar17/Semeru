package com.example.semeru;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.semeru.R;

public class GamasFeederActivity extends AppCompatActivity {

    private WebView webViewGamasFeeder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamas_feeder);

        webViewGamasFeeder = (WebView) findViewById(R.id.webViewGamasFeeder);
        webViewGamasFeeder.getSettings().setJavaScriptEnabled(true);
        webViewGamasFeeder.setWebViewClient(new MyBrowser());
        webViewGamasFeeder.loadUrl("http://10.11.4.57/semeru/nossa-network-witel-gmsfeeder");
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
            } else view.loadUrl(url);
            return true;
        }
    }
}

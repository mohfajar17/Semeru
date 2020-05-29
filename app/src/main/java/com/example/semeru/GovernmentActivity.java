package com.example.semeru;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GovernmentActivity extends AppCompatActivity {

    private WebView webViewGovernment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_government);

        webViewGovernment = (WebView) findViewById(R.id.webViewGovernment);
        webViewGovernment.getSettings().setJavaScriptEnabled(true);
        webViewGovernment.setWebViewClient(new MyBrowser());
        webViewGovernment.loadUrl("http://10.11.4.57/semeru/nossa-service-witel-dgs");
    }

    class MyBrowser extends WebViewClient {
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
package com.example.nguye.rssexpress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Newdeatils extends AppCompatActivity {
    WebView webView;
    WebSettings settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_newdeatils);

        webView= (WebView) findViewById(R.id.webview);

        Bundle bundle=getIntent().getExtras();
        settings=webView.getSettings();



        settings.setJavaScriptEnabled(true);
        webView.loadUrl(bundle.getString("Link"));
        webView.setWebViewClient(new WebViewClient());

    }
}

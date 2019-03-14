package com.example.android.newspoint;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

public class ArticleDetails extends AppCompatActivity {

    WebView articleWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);

        articleWeb = (WebView) findViewById(R.id.web_view);

        Intent intent = getIntent();
        String urlString = intent.getStringExtra("urlString");

        articleWeb.getSettings().setLoadsImagesAutomatically(true);
        articleWeb.getSettings().setJavaScriptEnabled(true);
        articleWeb.getSettings().setDomStorageEnabled(true);
        articleWeb.getSettings().setSupportZoom(true);
        articleWeb.getSettings().setBuiltInZoomControls(true);
        articleWeb.getSettings().setDisplayZoomControls(false);
        articleWeb.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        articleWeb.setWebViewClient(new WebViewClient());
        articleWeb.loadUrl(urlString);

    }

}

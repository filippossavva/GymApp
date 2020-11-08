package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SocialMediaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);

        WebView browser = findViewById(R.id.browser);
        Intent in = getIntent();
        String url = in.getStringExtra(FacilitiesClassesActivity.URL);

        browser.setWebViewClient(new WebViewClient());
        browser.loadUrl(url);
    }
}
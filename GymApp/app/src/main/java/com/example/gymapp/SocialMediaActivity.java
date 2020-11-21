package com.example.gymapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class SocialMediaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media);

        WebView browser = findViewById(R.id.browser);
        Intent in = getIntent();
        Bundle info = in.getExtras();

        String name = info.getString("name");
        String url = info.getString("url");
        getSupportActionBar().setTitle(name);
//        Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();

        browser.setWebViewClient(new WebViewClient());
        browser.loadUrl(url);
    }
}
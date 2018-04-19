package net.idevcorp.simpleandroidapp.ui.activities.questions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import net.idevcorp.simpleandroidapp.R;
import net.idevcorp.simpleandroidapp.util.SharedPreferencesManager;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        setTitle(SharedPreferencesManager.getEmail(this));

        WebView webViewQuestion = findViewById(R.id.webViewQuestion);
        webViewQuestion.getSettings().getJavaScriptEnabled();
        webViewQuestion.setWebViewClient(new WebViewClient());
        webViewQuestion.loadUrl(getIntent().getStringExtra("urlQuestion"));
    }
}

package com.example.espressopracticeapp;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WebPageActivity extends AppCompatActivity {

    private WebView webView;
    private TextView textWebPageInfo;
    private Button btnOpenWebPage;
    private Button btnBack; // 新增返回按鈕

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webpage);

        // 綁定 UI 元件
        webView = findViewById(R.id.webView);
        textWebPageInfo = findViewById(R.id.text_webpage_info);
        btnOpenWebPage = findViewById(R.id.btn_open_webpage);
        btnBack = findViewById(R.id.btn_back);

        // 啟用 JavaScript
        webView.getSettings().setJavaScriptEnabled(true);

        // 顯示內容但不跳出瀏覽器
        webView.setWebViewClient(new WebViewClient());

        // 為了取得網頁標題
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                textWebPageInfo.setText("網頁標題：" + title);
            }
        });

        // 開啟 Google 網頁
        btnOpenWebPage.setOnClickListener(v -> {
            webView.loadUrl("https://www.google.com.tw/");
        });

        // 返回主畫面
        btnBack.setOnClickListener(v -> {
            finish();
        });
    }
}

package com.example.espressopracticeapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WebPageActivity extends AppCompatActivity {

    private static final int REQUEST_WEB = 100;

    Button btnOpenWeb;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webpage);

        btnOpenWeb = findViewById(R.id.btn_open_web);
        textResult = findViewById(R.id.text_result);

        btnOpenWeb.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com.tw/"));
            // 使用 startActivityForResult，才能接收測試模擬的回應
            startActivityForResult(intent, REQUEST_WEB);
        });

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_WEB) {
            // 模擬回來後顯示一段訊息
            textResult.setText("模擬已完成，網頁應已開啟");
        }
    }
}

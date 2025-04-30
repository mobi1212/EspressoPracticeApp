package com.example.espressopracticeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DataRequestActivity extends AppCompatActivity {

    private static final int REQUEST_LOAD = 100;

    Button btnOpenWeb;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datarequest);

        btnOpenWeb = findViewById(R.id.btn_open_web);
        textResult = findViewById(R.id.text_result);

        btnOpenWeb.setOnClickListener(v -> {
            // 改為啟動 LoadActivity
            Intent intent = new Intent(DataRequestActivity.this, LoadActivity.class);
            intent.putExtra("caller", "WebPageActivity");
            startActivityForResult(intent, REQUEST_LOAD);
        });

        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOAD && resultCode == RESULT_OK && data != null) {
            String name = data.getStringExtra("user_name");
            int score = data.getIntExtra("credit_score", 0);
            textResult.setText("取得資料：\n" + name + "\n信用評分：" + score);
        }
    }
}

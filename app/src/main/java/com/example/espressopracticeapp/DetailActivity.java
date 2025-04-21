package com.example.espressopracticeapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class DetailActivity extends AppCompatActivity {

    TextView textDetail;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textDetail = findViewById(R.id.text_detail);
        btnBack = findViewById(R.id.btn_back);

        // 接收資料
        String userName = getIntent().getStringExtra("username");
        if (userName != null) {
            textDetail.setText("你選擇的是：" + userName);
        } else {
            textDetail.setText("未收到使用者資料");
        }

        btnBack.setOnClickListener(v -> finish());
    }
}

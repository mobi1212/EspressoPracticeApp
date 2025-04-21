package com.example.espressopracticeapp;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LoadActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView textUserName;
    private TextView textCreditScore;
    private Button btnStartLoad;

    private boolean userInfoLoaded = false;
    private boolean creditScoreLoaded = false;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        progressBar = findViewById(R.id.progress_bar);
        textUserName = findViewById(R.id.text_user_name);
        textCreditScore = findViewById(R.id.text_credit_score);
        btnStartLoad = findViewById(R.id.btn_start_load);

        btnStartLoad.setOnClickListener(v -> startLoading());
        findViewById(R.id.btn_back).setOnClickListener(v -> finish());

    }

    private void startLoading() {
        progressBar.setVisibility(View.VISIBLE);
        textUserName.setText("");
        textCreditScore.setText("");
        userInfoLoaded = false;
        creditScoreLoaded = false;

        // 模擬載入使用者基本資料（2 秒）
        handler.postDelayed(() -> {
            textUserName.setText("使用者名稱：阿偉");
            userInfoLoaded = true;
            checkAllTasksDone();
        }, 2000);

        // 模擬載入使用者信用評分（3 秒）
        handler.postDelayed(() -> {
            textCreditScore.setText("信用評分：900");
            creditScoreLoaded = true;
            checkAllTasksDone();
        }, 3000);
    }

    private void checkAllTasksDone() {
        if (userInfoLoaded && creditScoreLoaded) {
            progressBar.setVisibility(View.GONE);
        }
    }
}

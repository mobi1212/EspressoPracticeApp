package com.example.espressopracticeapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.test.espresso.idling.CountingIdlingResource;


public class LoadActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView textUserName;
    private TextView textCreditScore;
    private Button btnStartLoad;

    private boolean userInfoLoaded = false;
    private boolean creditScoreLoaded = false;

    private Handler handler = new Handler();

    private CountingIdlingResource idlingResource;

    public void setIdlingResource(CountingIdlingResource resource) {
        this.idlingResource = resource;
    }

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

        if (idlingResource != null) {
            idlingResource.increment(); // 使用者資訊
            idlingResource.increment(); // 信用評分
        }
        // 模擬載入使用者基本資料（2 秒）
        handler.postDelayed(() -> {
            textUserName.setText("使用者名稱：阿偉");
            userInfoLoaded = true;
            if (idlingResource != null) idlingResource.decrement();
            checkAllTasksDone();
        }, 2000);
        // 模擬載入使用者信用評分（3 秒）
        handler.postDelayed(() -> {
            textCreditScore.setText("信用評分：900");
            creditScoreLoaded = true;
            if (idlingResource != null) idlingResource.decrement();
            checkAllTasksDone();
        }, 3000);
    }

    private void checkAllTasksDone() {
        if (userInfoLoaded && creditScoreLoaded) {
            progressBar.setVisibility(View.GONE);

            String caller = getIntent().getStringExtra("caller");
            if ("WebPageActivity".equals(caller)) {
                Intent result = new Intent();
                result.putExtra("user_name", "阿偉");
                result.putExtra("credit_score", 900);
                setResult(RESULT_OK, result);
                finish(); // ✅ 只有 WebPageActivity 呼叫時才會結束
            }
        }
    }
}

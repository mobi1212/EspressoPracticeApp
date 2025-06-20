package com.example.espressopracticeapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.espressopracticeapp.LoginHandler;


public class LoginActivity extends AppCompatActivity {

    EditText editUsername, editPassword;
    Button btnLogin, btnBack;
    TextView textResult;
    LoginHandler loginHandler = null;

    public void setLoginHandler(LoginHandler handler) {
        this.loginHandler = handler;
    }
    public LoginHandler getLoginHandler() {
        return loginHandler;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUsername = findViewById(R.id.edit_username);
        editPassword = findViewById(R.id.edit_password);
        btnLogin = findViewById(R.id.btn_login);
        btnBack = findViewById(R.id.btn_back);
        textResult = findViewById(R.id.text_result);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = editUsername.getText().toString();
                String pass = editPassword.getText().toString();
                if (loginHandler != null) {
                    loginHandler.login(user, pass); // 改為呼叫注入的 loginHandler
                } else {
                    login(); // 沒有 handler 時使用預設邏輯
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 返回主畫面
                finish(); // 或用 startActivity(new Intent(...)) 也可以
            }
        });


    }
    public void login() {
        String user = editUsername.getText().toString();
        String pass = editPassword.getText().toString();
        if (user.equals("admin") && pass.equals("1234")) {
            textResult.setText("登入成功");
        } else {
            textResult.setText("帳號或密碼錯誤");
        }
    }
}

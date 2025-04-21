package com.example.espressopracticeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    Button btnLogin, btnList, btnCalculator, btnLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btn_login);
        btnList = findViewById(R.id.btn_list);
        btnCalculator = findViewById(R.id.btn_calculator);
        btnLoad = findViewById(R.id.btn_load);

        btnLogin.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, LoginActivity.class)));

        btnList.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, ListActivity.class)));

        btnCalculator.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, CalculatorActivity.class)));

        btnLoad.setOnClickListener(view ->
                startActivity(new Intent(MainActivity.this, LoadActivity.class)));
    }
}

package com.example.espressopracticeapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;



public class CalculatorActivity extends AppCompatActivity {

    EditText editNum1, editNum2;
    Button btnAdd, btnSubtract, btnMultiply, btnDivide, btnBack;
    TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        editNum1 = findViewById(R.id.edit_num1);
        editNum2 = findViewById(R.id.edit_num2);
        btnAdd = findViewById(R.id.btn_add);
        btnSubtract = findViewById(R.id.btn_subtract);
        btnMultiply = findViewById(R.id.btn_multiply);
        btnDivide = findViewById(R.id.btn_divide);
        btnBack = findViewById(R.id.btn_back);
        textResult = findViewById(R.id.text_result);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('+');
            }
        });

        btnSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('-');
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('*');
            }
        });

        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate('/');
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 返回主頁
            }
        });
    }

    private void calculate(char op) {
        String input1 = editNum1.getText().toString();
        String input2 = editNum2.getText().toString();

        if (input1.isEmpty() || input2.isEmpty()) {
            textResult.setText("請輸入兩個數字");
            return;
        }

        double num1 = Double.parseDouble(input1);
        double num2 = Double.parseDouble(input2);
        double result = 0;

        switch (op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
                    textResult.setText("除數不能為 0");
                    return;
                }
                result = num1 / num2;
                break;
        }

        textResult.setText("結果：" + result);
    }
}

package com.example.espressopracticeapp;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recycler_view);
        btnBack = findViewById(R.id.btn_back);

        // 建立假資料
        List<String> userList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            userList.add("使用者 " + i);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserAdapter adapter = new UserAdapter(ListActivity.this, userList); // 傳入 context
        recyclerView.setAdapter(adapter);

        btnBack.setOnClickListener(v -> finish());
    }
}

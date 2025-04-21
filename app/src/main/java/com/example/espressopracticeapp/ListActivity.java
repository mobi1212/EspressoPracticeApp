package com.example.espressopracticeapp;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button btnBack;
    UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recycler_view);
        btnBack = findViewById(R.id.btn_back);

        // 建立 Repository 與 Service
        UserRepository repository = new RealUserRepository(); // 可換成 Fake
        userService = new UserService(repository);

        List<String> userList = userService.loadUsers();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserAdapter adapter = new UserAdapter(this, userList); // 傳入 context
        recyclerView.setAdapter(adapter);

        btnBack.setOnClickListener(v -> finish());
    }
}

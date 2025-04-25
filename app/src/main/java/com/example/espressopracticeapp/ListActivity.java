package com.example.espressopracticeapp;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.espressopracticeapp.adapter.UserAdapter;
import com.example.espressopracticeapp.data.RealUserRepository;
import com.example.espressopracticeapp.data.UserRepository;
import com.example.espressopracticeapp.service.UserService;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button btnBack,btnReverse;
    UserService userService;

    boolean isReversed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recycler_view);
        btnBack = findViewById(R.id.btn_back);
        btnReverse = findViewById(R.id.btn_reverse);

        // 建立 Repository 與 Service
        UserRepository repository = new RealUserRepository(); // 可換成 Fake
        userService = new UserService(repository);

        List<String> userList = userService.loadUsers();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserAdapter adapter = new UserAdapter(this, userList); // 傳入 context
        recyclerView.setAdapter(adapter);

        btnBack.setOnClickListener(v -> finish());
        btnReverse.setOnClickListener(v -> {
            if (isReversed) {
                List<String> originalList = userService.resetToOriginalOrder();
                adapter.updateData(originalList);
                isReversed = false;
            }else{
                List<String> reversedList = userService.loadUsersReversed();
                adapter.updateData(reversedList);
                isReversed = true;
            }
        });
    }
}

package com.example.espressopracticeapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PickContactActivity extends AppCompatActivity {

    private static final int PICK_CONTACT_REQUEST = 1;
    Button btnPickContact;
    TextView textSelectedContact;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_contact);

        btnPickContact = findViewById(R.id.btn_pick_contact);
        textSelectedContact = findViewById(R.id.text_selected_contact);

        // 按鈕點擊後觸發聯絡人選擇
        btnPickContact.setOnClickListener(v -> {
            Intent pickContactIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
            startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
        });

        // 返回主畫面按鈕
        findViewById(R.id.btn_back).setOnClickListener(v -> finish());
    }

    // 當選擇聯絡人後，回傳結果
    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_CONTACT_REQUEST && resultCode == RESULT_OK) {
            Uri contactUri = data.getData();
            String contactName = getContactName(contactUri);
            textSelectedContact.setText("選擇的聯絡人是：" + contactName);
        }
    }

    @SuppressLint("Range")
    private String getContactName(Uri contactUri) {
        String[] projection = {ContactsContract.Contacts.DISPLAY_NAME};
        Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
        String contactName = null;
        if (cursor != null && cursor.moveToFirst()) {
            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            cursor.close();
        }
        return contactName;
    }
}

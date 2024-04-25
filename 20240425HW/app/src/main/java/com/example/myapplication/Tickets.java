package com.example.myapplication;




import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Tickets extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_info);

        // 獲取從 MainActivity 通過 Intent 傳遞的資訊
        String gender = getIntent().getStringExtra("gender");
        String ticketType = getIntent().getStringExtra("ticketType");
        int quantity = getIntent().getIntExtra("quantity", 0);
        int totalAmount = getIntent().getIntExtra("totalAmount", 0);

        // 顯示購票資訊
        TextView genderTextView = findViewById(R.id.genderTextView);
        TextView ticketTypeTextView = findViewById(R.id.ticketTypeTextView);
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        TextView totalAmountTextView = findViewById(R.id.totalAmountTextView);

        genderTextView.setText("性別: " + gender);
        ticketTypeTextView.setText("票種: " + ticketType);
        quantityTextView.setText("數量: " + quantity);
        totalAmountTextView.setText("總金額: " + totalAmount + " 元");
    }
}
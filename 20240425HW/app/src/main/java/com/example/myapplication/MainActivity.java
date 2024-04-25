package com.example.myapplication;

//A111221056

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadioGroup genderRadioGroup, ticketTypeRadioGroup;
    private EditText quantityEditText;
    private Button orderButton;
    private TextView resultTextView;
    private List<String> orderDetailsList;
    private int totalAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        genderRadioGroup = findViewById(R.id.genderRadioGroup);
        ticketTypeRadioGroup = findViewById(R.id.ticketTypeRadioGroup);
        quantityEditText = findViewById(R.id.quantityEditText);
        orderButton = findViewById(R.id.orderButton);
        resultTextView = findViewById(R.id.resultTextView);
        orderDetailsList = new ArrayList<>();

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOrder();
            }
        });
    }

    private void addOrder() {
        int quantity = Integer.parseInt(quantityEditText.getText().toString());

        RadioButton selectedGenderRadioButton = findViewById(genderRadioGroup.getCheckedRadioButtonId());
        String gender = selectedGenderRadioButton.getText().toString();

        RadioButton selectedTicketTypeRadioButton = findViewById(ticketTypeRadioGroup.getCheckedRadioButtonId());
        String ticketType = selectedTicketTypeRadioButton.getText().toString();

        int total = calculateTotal(ticketType, quantity);

        // 顯示購票資訊
        String orderDetails = "性別: " + gender + "\n" +
                "票種: " + ticketType + " x" + quantity + "\n" +
                "金額: " + total + " 元\n\n";
        resultTextView.setText(orderDetails);

        // 建立Intent並傳遞購票資訊到TicketInfoActivity
        Intent intent = new Intent(MainActivity.this, Tickets.class);
        intent.putExtra("gender", gender);
        intent.putExtra("ticketType", ticketType);
        intent.putExtra("quantity", quantity);
        intent.putExtra("totalAmount", total);
        startActivity(intent);
    }

    private int calculateTotal(String ticketType, int quantity) {
        int price;
        if (ticketType.contains("成人")) {
            price = 500;
        } else if (ticketType.contains("孩童")) {
            price = 250;
        } else { // 學生
            price = 400;
        }
        return price * quantity;
    }

    private void displayOrders() {
        StringBuilder result = new StringBuilder();
        for (String order : orderDetailsList) {
            result.append(order);
        }
        result.append("總金額: ").append(totalAmount).append(" 元");

        resultTextView.setText(result.toString());
    }

}
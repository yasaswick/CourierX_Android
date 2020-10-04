package com.courierx.courierx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.courierx.courierx.Models.CreditLog;
import com.courierx.courierx.Services.FirebaseRealtime;

public class AddPayment extends AppCompatActivity {


    TextView uid,name,balance;
    EditText amount;
    FirebaseRealtime firebaseRealtime;
    Button addBtn;
    CreditLog creditLog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment);
creditLog = new CreditLog();
        firebaseRealtime = new FirebaseRealtime();
        uid = findViewById(R.id.add_credit_userID);
        name = findViewById(R.id.add_credit_username);
        balance = findViewById(R.id.add_credit_balance);
        amount = findViewById(R.id.add_credit_amount);
        addBtn = findViewById(R.id.add_credit_button);

        Long userbalance = getIntent().getLongExtra("balance", 0);
        String username = getIntent().getStringExtra("userName");
        String userId = getIntent().getStringExtra("uid");

        name.setText(username);
        balance.setText(userbalance.toString());
        uid.setText(userId);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long finalBalance = Long.parseLong(amount.getText().toString());


                creditLog.setAmount(Long.parseLong(amount.getText().toString()));
                creditLog.setDate(System.currentTimeMillis());
                creditLog.setType("Test");
                firebaseRealtime.addCredit(finalBalance ,creditLog);
            }
        });






    }
}
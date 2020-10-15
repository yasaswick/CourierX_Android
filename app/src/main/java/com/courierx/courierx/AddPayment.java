package com.courierx.courierx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.courierx.courierx.Models.CreditLog;
import com.courierx.courierx.Services.FirebaseRealtime;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddPayment extends AppCompatActivity {


    TextView uid,name,balance;
    EditText amount;
    FirebaseRealtime firebaseRealtime;
    Button addBtn;
    CreditLog creditLog;
    RecyclerView creditLogRecycler;
    FirebaseRecyclerOptions<CreditLog> recyclerOptions;
    FirebaseRecyclerAdapter<CreditLog,CreditLogViewHolder> creditLogListAdapter;
    DatabaseReference databaseReference;

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
        creditLogRecycler = findViewById(R.id.credit_log_recycler);


        final Long userbalance = getIntent().getLongExtra("balance", 0);
        String username = getIntent().getStringExtra("userName");
        final String userId = getIntent().getStringExtra("uid");


        databaseReference = FirebaseDatabase.getInstance().getReference().child("user").child(userId).child("creditLog");
        name.setText(username);
        balance.setText(userbalance.toString());
        uid.setText(userId);

        recyclerOptions = new FirebaseRecyclerOptions.Builder<CreditLog>().setQuery(databaseReference,CreditLog.class).build();
        creditLogListAdapter = new FirebaseRecyclerAdapter<CreditLog, CreditLogViewHolder>(recyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull CreditLogViewHolder holder, int position, @NonNull CreditLog model) {
                holder.amount.setText(model.getAmount().toString());
                holder.type.setText(model.getType());
                holder.date.setText(model.getDate().toString());
            }

            @NonNull
            @Override
            public CreditLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            }
        };

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long newBalance = Long.parseLong(amount.getText().toString());
                Long finalBalance = newBalance + userbalance;
                creditLog.setAmount(newBalance);
                creditLog.setDate(System.currentTimeMillis());
                creditLog.setType("Test");
                firebaseRealtime.addCredit(userId ,creditLog, finalBalance);
            }
        });






    }
}
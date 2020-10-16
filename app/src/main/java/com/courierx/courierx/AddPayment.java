package com.courierx.courierx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.courierx.courierx.Admin.UserListViewHolder;
import com.courierx.courierx.Models.CreditLog;
import com.courierx.courierx.Services.FirebaseRealtime;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddPayment extends AppCompatActivity {


    TextView uid,name,balance;
    EditText amount;
    EditText type;
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
        amount = findViewById(R.id.add_credit_amount2);
        type = findViewById(R.id.add_credit_type);
        addBtn = findViewById(R.id.add_credit_button);
        creditLogRecycler = findViewById(R.id.credit_log_recycler);
        creditLogRecycler.hasFixedSize();
        creditLogRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

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
            protected void onBindViewHolder(@NonNull CreditLogViewHolder holder, final int position, @NonNull CreditLog model) {
                holder.amount.setText(model.getAmount().toString());
                holder.type.setText(model.getType());
                Date date = new Date(model.getDate());
                SimpleDateFormat sfd = new SimpleDateFormat("dd-MMM-yyyy HH:mm",
                        Locale.getDefault());
                String text = sfd.format(date);
                holder.date.setText(text);

                holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CreditLog creditLog = creditLogListAdapter.getItem(position);
                        databaseReference.child(creditLog.getCreditLogId()).removeValue();
                    }
                });


            }

            @NonNull
            @Override
            public CreditLogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View recyclerview = LayoutInflater.from(parent.getContext()).inflate(R.layout.credit_log_card_list , parent , false);
                return new CreditLogViewHolder(recyclerview);
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
                amount.setText("");
                creditLogListAdapter.notifyDataSetChanged();
            }
        });


        creditLogListAdapter.startListening();
        creditLogRecycler.setAdapter(creditLogListAdapter);

    }
}
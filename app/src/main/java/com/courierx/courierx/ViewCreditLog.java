package com.courierx.courierx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.courierx.courierx.Models.CreditLog;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewCreditLog extends AppCompatActivity {

    FirebaseRecyclerOptions<CreditLog> creditLogListOptions;
    FirebaseRecyclerAdapter<CreditLog , CreditLogListViewHolder> creditLogListAdapter;
    DatabaseReference ref;
    private RecyclerView creditLogListRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_credit_log);

        ref = FirebaseDatabase.getInstance().getReference().child("feedback");
        creditLogListRecyclerView = findViewById(R.id.credit_log_recycler_view);
        creditLogListRecyclerView.setHasFixedSize(true);
        creditLogListRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        creditLogListOptions = new FirebaseRecyclerOptions.Builder<CreditLog>().setQuery(ref , CreditLog.class).build();
        creditLogListAdapter = new FirebaseRecyclerAdapter<CreditLog, CreditLogListViewHolder>(creditLogListOptions) {
            @NonNull
            @Override
            public CreditLogListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View recyclerview = LayoutInflater.from(parent.getContext()).inflate(R.layout.credit_log_card_list , parent , false);
                return new CreditLogListViewHolder(recyclerview);
            }

            @Override
            protected void onBindViewHolder(@NonNull CreditLogListViewHolder holder, int position, @NonNull CreditLog model) {
                holder.type.setText(model.getType());
                holder.amount.setText(model.getAmount().toString());
                holder.datte.setText(model.getDate().toString());

            }
        };



        creditLogListAdapter.startListening();
        creditLogListRecyclerView.setAdapter(creditLogListAdapter);



    }
}
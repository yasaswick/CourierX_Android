package com.courierx.courierx.Admin;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.courierx.courierx.R;

public class UserListViewHolder extends RecyclerView.ViewHolder {

    TextView name , uid, balance;


    public UserListViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.user_card_name);
        uid = itemView.findViewById(R.id.user_card_uid);
        balance = itemView.findViewById(R.id.user_card_balance);


    }
}

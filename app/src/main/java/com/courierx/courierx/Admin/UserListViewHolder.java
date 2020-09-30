package com.courierx.courierx.Admin;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.courierx.courierx.R;

public class UserListViewHolder extends RecyclerView.ViewHolder {

    TextView uid;

    public UserListViewHolder(@NonNull View itemView) {
        super(itemView);

        uid = itemView.findViewById(R.id.card_user_id);


    }
}

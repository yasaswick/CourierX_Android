package com.courierx.courierx;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CreditLogListViewHolder extends RecyclerView.ViewHolder {

    public TextView datte, amount, type;

    public CreditLogListViewHolder(@NonNull View itemView) {
        super(itemView);
        datte = itemView.findViewById(R.id.lbl_date);
        amount = itemView.findViewById(R.id.lbl_amount);
        type = itemView.findViewById(R.id.lbl_type);
    }
}

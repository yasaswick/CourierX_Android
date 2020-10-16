package com.courierx.courierx;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CreditLogViewHolder extends RecyclerView.ViewHolder {

    TextView amount,date,type;
    ImageView deleteBtn;


    public CreditLogViewHolder(@NonNull View itemView) {
        super(itemView);
        amount = itemView.findViewById(R.id.credit_log_amount);
        date = itemView.findViewById(R.id.credit_log_date);
        type=itemView.findViewById(R.id.credit_log_type);
        deleteBtn = itemView.findViewById(R.id.credit_log_delete_btn);
    }
}

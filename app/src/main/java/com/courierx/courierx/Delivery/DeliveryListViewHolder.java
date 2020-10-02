package com.courierx.courierx.Delivery;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.courierx.courierx.R;

public class DeliveryListViewHolder extends RecyclerView.ViewHolder {
    TextView status , packageid, date;
    public DeliveryListViewHolder(@NonNull View itemView) {
        super(itemView);
        status = itemView.findViewById(R.id.delivery_package_status_card);
        packageid = itemView.findViewById(R.id.delivery_package_id_card);
        date = itemView.findViewById(R.id.package_date_card);
    }
}


package com.courierx.courierx.Delivery;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.courierx.courierx.R;

public class DeliveryLocationListViewHolder extends RecyclerView.ViewHolder {
    public TextView location;
    TextView packageid;
    public TextView time;
    public ImageButton delete;


    public DeliveryLocationListViewHolder(@NonNull View itemView) {
        super(itemView);
        location = itemView.findViewById(R.id.package_location_card);
        time = itemView.findViewById(R.id.package_time_card);
        delete = itemView.findViewById(R.id.locationListDeleteBtn);

    }
}

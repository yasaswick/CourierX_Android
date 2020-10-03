package com.courierx.courierx.Track;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.courierx.courierx.R;

import java.util.Objects;

public class UserLocationListViewHolder extends RecyclerView.ViewHolder {
    public TextView location;
    public TextView packageid;
    public TextView time;
    public UserLocationListViewHolder(@NonNull View itemView) {
        super(itemView);
        location = itemView.findViewById(R.id.package_location_card);
        time = itemView.findViewById(R.id.package_time_card);
    }
}
